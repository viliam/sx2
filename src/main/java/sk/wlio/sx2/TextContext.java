/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.wlio.sx2;

import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.SlovoReader;

public class TextContext {

    private final String[] riadky;
    private volatile Position inx = new Position(0,0);

    public TextContext( String slovo)   {
        this( slovo.split("\r\n"));
    }

    private TextContext( String[] riadky) {
        this.riadky = riadky;
    }

    public String getRiadok() {
        return riadky[inx.getY()];
    }

    /**
     * Metoda sa presunie kruzor na nasledovny znak, precita znak a
     * vrati kurzor na povodne miesto
     *
     * @return vrati nasledovny znak
     * @ - v pripade, ze
     */
    public char getNasledujuciZnak()  {
        Position zaciatok = new Position( inx);
        najdiNasledujuciZnak();
        skontrolujKoniec();

        int x = inx.getX(),
            y = inx.getY();

        char p=  riadky[y].charAt(x);
        inx = zaciatok;
        return p;
    }

    /**
     * Presunie kurzor na nasledujuci neprazdny znak
     *
     * @return Suradnice nasledujuceho neprazdneho znaku
     */
    public Position najdiNasledujuciZnak() {
        int x = inx.getX(), y = inx.getY();

        while ( y < riadky.length
            && ( x = najdiNasledujuciZnakVriadku(x, y)) >= riadky[y].length() ) {
            y++; x=0;
        }

        if (!presahujeHranice(x, y))  {
            return null;
        }
        return (inx = new Position(x, y ));
    }

    private int najdiNasledujuciZnakVriadku(int x, int y) {
        String riadok = riadky[y];
        while (x<riadok.length() && (riadok.charAt(x)==' ' || riadok.charAt(x)=='\n' ) ) x++;
        return x;
    }

    public Position getPozicia() {
        return new Position( inx);
    }

    public void pripocitajXPoziciu(int by) {
        this.inx = this.inx.addX(by);
    }

    public void setPozicia(Position inx) {
        this.inx = new Position(inx);
    }

    public String vratKoniecRiadka()   {
        skontrolujKoniec();

        int x = inx.getX(), y = inx.getY();
        return riadky[y].substring(x);
    }

    public boolean skontrolujKoniec()  {
        if (jeKoniec())
            throw SxException.create(SxExTyp.END_OF_FILE, this);
        return true;
    }

    private boolean presahujeHranice(int x, int y) {
        return !( y>=riadky.length || (y==riadky.length-1 && x>=riadky[ y].length() ) );
    }

    public boolean jeKoniec() {
        return (najdiNasledujuciZnak() == null);
    }

    public boolean jePrefixPismeno()  {
        char a = getNasledujuciZnak();
        return TextUtils.jePismeno(a);
    }

    public boolean jePrefixCislo()  {
        char a = getNasledujuciZnak();
        return TextUtils.jeCislo(a);
    }

    public boolean jePrefixOperator()  {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_COMPARATION.jePrefix(a) || SymbolsEnum.OP_ARITM.jePrefix(a) ||
               SymbolsEnum.OP_ASSIGNMENT.jePrefix(a) || SymbolsEnum.OP_BOOL.jePrefix(a);
    }

    public boolean jePrefixCiarka()  {
        char a = getNasledujuciZnak();
        return SymbolsEnum.COMMAS.jePrefix(a);
    }


    public boolean jePrefixOperatorAritm() {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_ARITM.jePrefix(a);
    }

    public boolean jePrefixOperatorBool() {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_BOOL.jePrefix(a);
    }

    public boolean jePrefixOperatorPorovnania() {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_COMPARATION.jePrefix(a);
    }

    public boolean jePrefixOperatorPriradenia() {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_ASSIGNMENT.jePrefix(a);
    }

    public boolean jePrefixZatvorkaOtovorena() {
        char a = getNasledujuciZnak();
        return SymbolEnum.PARENTHESIS_NORM_OPEN.jePrefix(a);
    }

    public boolean jePrefixZatvorkaZatvorena() {
        char a = getNasledujuciZnak();
        return SymbolEnum.PARENTHESIS_NORM_CLOSE.jePrefix(a);
    }

    public boolean jePrefixBodkoCiarka() {
        char a = getNasledujuciZnak();
        return SymbolEnum.SEMICOLON.jePrefix(a);
    }

    public Comma nacitajAkJeBodkoCiarka() {
        Comma bodkoCiarka = null;
        if (jePrefixBodkoCiarka() )
            bodkoCiarka = Readers.ciarka().citaj( this);
        return bodkoCiarka;
    }


    abstract class JePrefixTemplateMetod {
        public boolean jePrefix()  {
            Position zac = getPozicia();
            try {
                if (!jePrefixPismeno() ) {
                   return false;
                }

                Word word = Readers.slovo().citaj( TextContext.this);
                return  jePrefix(word);
            } finally {
                setPozicia( zac);
            }
        }

        protected abstract boolean jePrefix(Word word);
    }

    public boolean jePrefixDatovyTyp()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Word word) {
                return RezervedWordsEnum.DATA_TYPE.is(word.getObsah());
            }
        }.jePrefix();
    }

    public boolean jePrefixInstrukcia()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Word word) {
                return RezervedWordsEnum.INSTRUCTION_WORD.is(word.getObsah());
            }
        }.jePrefix();
    }

    public boolean jePrefixPremenna()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Word word) {

                return !RezervedWordsEnum.jeSlovo(word)
                        && (!presahujeHranice(inx.getX(), inx.getY()) || !jePrefixZatvorkaOtovorena());

            }
        }.jePrefix();
    }

    public boolean jePrefixPrikaz()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Word word) {
                return !RezervedWordsEnum.jeSlovo(word)
                        && presahujeHranice(inx.getX(), inx.getY())
                        && jePrefixZatvorkaOtovorena();

            }
        }.jePrefix();
    }

    abstract class JePrefixDeklaraciaTemplateMetod {
        public boolean jePrefix()  {
            Position position = getPozicia();

            try {
                if (jeKoniec() || !jePrefixPismeno() )
                   return false;

                Word word = Readers.slovo().citaj(TextContext.this );
                return RezervedWordsEnum.DATA_TYPE.is(word.getObsah()) && jePrefixNazov();
            } finally {
                setPozicia(position);
            }
        }

        protected abstract boolean jePrefixNazov();
    }

    public boolean jePrefixDeklaraciaPrikaz() {
        return new JePrefixDeklaraciaTemplateMetod() {
            @Override
            protected boolean jePrefixNazov() {
                return jePrefixPrikaz();
            }
        }.jePrefix();
    }

    public boolean jePrefixDeklaraciaPremennej() {
        return new JePrefixDeklaraciaTemplateMetod() {
            @Override
            protected boolean jePrefixNazov() {
                return jePrefixPremenna();
            }
        }.jePrefix();
    }

    public ReservedWordEnum vratPrefixZakazaneSlovo()  {
        najdiNasledujuciZnak();
        String s = SlovoReader.predcitajSlovo( vratKoniecRiadka(), 0);
        return ReservedWordEnum.makeSymbol(s);
    }

    public String getRiadok(int riadok) {
        return riadky[riadok];
    }

    @Override
    public String toString() {
        if (jeKoniec()) return "koniec";
        if (inx != null && riadky != null && inx.getY() < riadky.length)
            return inx.toString() + "  : " + riadky[inx.getY()].toString();
        return super.toString();
    }
}
