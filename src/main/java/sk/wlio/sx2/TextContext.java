package sk.wlio.sx2;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.SlovoReader;

public class TextContext {

    private final String[] riadky;
    private volatile Pozicia inx = new Pozicia(0,0);

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
        Pozicia zaciatok = new Pozicia( inx);
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
    public Pozicia najdiNasledujuciZnak() {
        int x = inx.getX(), y = inx.getY();

        while ( y < riadky.length
            && ( x = najdiNasledujuciZnakVriadku(x, y)) >= riadky[y].length() ) {
            y++; x=0;
        }

        if (!presahujeHranice(x, y))  {
            return null;
        }
        return (inx = new Pozicia(x, y ));
    }

    private int najdiNasledujuciZnakVriadku(int x, int y) {
        String riadok = riadky[y];
        while (x<riadok.length() && (riadok.charAt(x)==' ' || riadok.charAt(x)=='\n' ) ) x++;
        return x;
    }

    public Pozicia getPozicia() {
        return new Pozicia( inx);
    }

    public void pripocitajXPoziciu(int by) {
        this.inx = this.inx.addX(by);
    }

    public void setPozicia(Pozicia inx) {
        this.inx = new Pozicia(inx);
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
        return SymbolsEnum.OP_POROVNANIE.jePrefix(a) || SymbolsEnum.OP_ARITM.jePrefix(a) ||
               SymbolsEnum.OP_PRIRADENIE.jePrefix(a) || SymbolsEnum.OP_BOOL.jePrefix(a);
    }

    public boolean jePrefixCiarka()  {
        char a = getNasledujuciZnak();
        return SymbolsEnum.CIARKY.jePrefix(a);
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
        return SymbolsEnum.OP_POROVNANIE.jePrefix(a);
    }

    public boolean jePrefixOperatorPriradenia() {
        char a = getNasledujuciZnak();
        return SymbolsEnum.OP_PRIRADENIE.jePrefix(a);
    }

    public boolean jePrefixZatvorkaOtovorena() {
        char a = getNasledujuciZnak();
        return SymbolEnum.ZATVORKA_NORM_OTOVRENA.jePrefix(a);
    }

    public boolean jePrefixZatvorkaZatvorena() {
        char a = getNasledujuciZnak();
        return SymbolEnum.ZATVORKA_NORM_ZATVORENA.jePrefix(a);
    }

    public boolean jePrefixBodkoCiarka() {
        char a = getNasledujuciZnak();
        return SymbolEnum.BODKO_CIARKA.jePrefix(a);
    }

    public Ciarka nacitajAkJeBodkoCiarka() {
        Ciarka bodkoCiarka = null;
        if (jePrefixBodkoCiarka() )
            bodkoCiarka = Readers.ciarka().citaj( this);
        return bodkoCiarka;
    }


    abstract class JePrefixTemplateMetod {
        public boolean jePrefix()  {
            Pozicia zac = getPozicia();
            try {
                if (!jePrefixPismeno() ) {
                   return false;
                }

                Slovo slovo = Readers.slovo().citaj( TextContext.this);
                return  jePrefix(slovo);
            } finally {
                setPozicia( zac);
            }
        }

        protected abstract boolean jePrefix(Slovo slovo);
    }

    public boolean jePrefixDatovyTyp()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Slovo slovo) {
                return RezervovaneSlovaEnum.DATOVY_TYP.je(slovo.getObsah());
            }
        }.jePrefix();
    }

    public boolean jePrefixInstrukcia()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Slovo slovo) {
                return RezervovaneSlovaEnum.INSTRUKCIA_SLOVO.je(slovo.getObsah());
            }
        }.jePrefix();
    }

    public boolean jePrefixPremenna()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Slovo slovo) {

                return !RezervovaneSlovaEnum.jeSlovo(slovo)
                        && (!presahujeHranice(inx.getX(), inx.getY()) || !jePrefixZatvorkaOtovorena());

            }
        }.jePrefix();
    }

    public boolean jePrefixPrikaz()  {
        return new JePrefixTemplateMetod() {
            @Override
            protected boolean jePrefix(Slovo slovo) {
                return !RezervovaneSlovaEnum.jeSlovo(slovo)
                        && presahujeHranice(inx.getX(), inx.getY())
                        && jePrefixZatvorkaOtovorena();

            }
        }.jePrefix();
    }

    abstract class JePrefixDeklaraciaTemplateMetod {
        public boolean jePrefix()  {
            Pozicia pozicia = getPozicia();

            try {
                if (jeKoniec() || !jePrefixPismeno() )
                   return false;

                Slovo slovo = Readers.slovo().citaj(TextContext.this );
                return RezervovaneSlovaEnum.DATOVY_TYP.je(slovo.getObsah()) && jePrefixNazov();
            } finally {
                setPozicia(pozicia);
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

    public RezervovaneSlovoEnum vratPrefixZakazaneSlovo()  {
        najdiNasledujuciZnak();
        String s = SlovoReader.predcitajSlovo( vratKoniecRiadka(), 0);
        return RezervovaneSlovoEnum.makeSymbol(s);
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
