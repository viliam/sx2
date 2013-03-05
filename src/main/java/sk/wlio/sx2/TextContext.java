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
import sk.wlio.sx2.readers.symbol.WordReader;

public class TextContext {

    private final String[] lines;
    private volatile Position inx = new Position(0,0);

    public TextContext( String word)   {
        this( word.split("\r\n"));
    }

    private TextContext( String[] lines) {
        this.lines = lines;
    }

    public String getLine() {
        return lines[inx.getY()];
    }

    /**
     * Metoda sa presunie kruzor na nasledovny znak, precita znak a
     * vrati kurzor na povodne miesto
     *
     * @return vrati nasledovny znak
     * @ - v pripade, ze
     */
    public char nextCharacter()  {
        Position actual = new Position( inx);
        findNextCharacter();
        checkEndOfFile();

        int x = inx.getX(),
            y = inx.getY();

        char p =  lines[y].charAt(x);
        inx = actual;
        return p;
    }

    /**
     * Presunie kurzor na nasledujuci neprazdny znak
     *
     * @return Suradnice nasledujuceho neprazdneho znaku
     */
    public Position findNextCharacter() {
        int x = inx.getX(), y = inx.getY();

        while ( y < lines.length
            && ( x = findNextCharacterInLine(x, y)) >= lines[y].length() ) {
            y++; x=0;
        }

        if (!isOut(x, y))  {
            return null;
        }
        return (inx = new Position(x, y ));
    }

    private int findNextCharacterInLine(int x, int y) {
        String line = lines[y];
        while (x<line.length() && (line.charAt(x)==' ' || line.charAt(x)=='\n' ) ) x++;
        return x;
    }

    public Position getPosition() {
        return new Position( inx);
    }

    public void addXpostion(int by) {
        this.inx = this.inx.addX(by);
    }

    public void setPosition(Position inx) {
        this.inx = new Position(inx);
    }

    public String endOfLine()   {
        checkEndOfFile();

        int x = inx.getX(), y = inx.getY();
        return lines[y].substring(x);
    }

    public boolean checkEndOfFile()  {
        if (isEndOfFile())
            throw SxException.create(SxExTyp.END_OF_FILE, this);
        return true;
    }

    private boolean isOut(int x, int y) {
        return !( y>= lines.length || (y== lines.length-1 && x>= lines[ y].length() ) );
    }

    public boolean isEndOfFile() {
        return (findNextCharacter() == null);
    }

    public boolean isPrefixLetter()  {
        char a = nextCharacter();
        return TextUtils.isLetter(a);
    }

    public boolean isPrefixInt()  {
        char a = nextCharacter();
        return TextUtils.isInt(a);
    }

    public boolean isPrefixOperator()  {
        char a = nextCharacter();
        return SymbolsEnum.OP_COMPARISON.jePrefix(a) || SymbolsEnum.OP_ARITM.jePrefix(a) ||
               SymbolsEnum.OP_ASSIGNMENT.jePrefix(a) || SymbolsEnum.OP_BOOL.jePrefix(a);
    }

    public boolean isPrefixComma()  {
        char a = nextCharacter();
        return SymbolsEnum.COMMAS.jePrefix(a);
    }


    public boolean isPrefixOperatorExp() {
        char a = nextCharacter();
        return SymbolsEnum.OP_EXP.jePrefix(a);
    }

    public boolean isPrefixOperatorAssigment() {
        char a = nextCharacter();
        return SymbolsEnum.OP_ASSIGNMENT.jePrefix(a);
    }

    public boolean isPrefixBracketOpen() {
        char a = nextCharacter();
        return SymbolEnum.BRACKET_NORM_OPEN.jePrefix(a);
    }

    public boolean isPrefixBracketClosed() {
        char a = nextCharacter();
        return SymbolEnum.BRACKET_NORM_CLOSE.jePrefix(a);
    }

    public boolean isPrefixSemicolon() {
        char a = nextCharacter();
        return SymbolEnum.SEMICOLON.jePrefix(a);
    }

    public Comma readIfIsSemicolon() {
        Comma semicolon = null;
        if (isPrefixSemicolon() )
            semicolon = Readers.ciarka().read(this);
        return semicolon;
    }


    abstract class IsPrefixTemplateMetod {
        public boolean isPrefix()  {
            Position pos = getPosition();
            try {
                if (!isPrefixLetter() ) {
                   return false;
                }

                Word word = Readers.slovo().read(TextContext.this);
                return  isPrefix(word);
            } finally {
                setPosition(pos);
            }
        }

        protected abstract boolean isPrefix(Word word);
    }

    public boolean isPrefixDataType()  {
        return new IsPrefixTemplateMetod() {
            @Override
            protected boolean isPrefix(Word word) {
                return RezervedWordsEnum.DATA_TYPE.is(word.getContent());
            }
        }.isPrefix();
    }

    public boolean isPrefixStatement()  {
        return new IsPrefixTemplateMetod() {
            @Override
            protected boolean isPrefix(Word word) {
                return RezervedWordsEnum.INSTRUCTION_WORD.is(word.getContent());
            }
        }.isPrefix();
    }

    public boolean isPrefixVariable()  {
        return new IsPrefixTemplateMetod() {
            @Override
            protected boolean isPrefix(Word word) {

                return !RezervedWordsEnum.isWord(word)
                        && (!isOut(inx.getX(), inx.getY()) || !isPrefixBracketOpen());

            }
        }.isPrefix();
    }

    public boolean isPrefixCommand()  {
        return new IsPrefixTemplateMetod() {
            @Override
            protected boolean isPrefix(Word word) {
                return !RezervedWordsEnum.isWord(word)
                        && isOut(inx.getX(), inx.getY())
                        && isPrefixBracketOpen();

            }
        }.isPrefix();
    }

    abstract class IsPrefixDeclarationTemplateMetod {
        public boolean jePrefix()  {
            Position position = getPosition();

            try {
                if (isEndOfFile() || !isPrefixLetter() )
                   return false;

                Word word = Readers.slovo().read(TextContext.this);
                return RezervedWordsEnum.DATA_TYPE.is(word.getContent()) && isPrefixName();
            } finally {
                setPosition(position);
            }
        }

        protected abstract boolean isPrefixName();
    }

    public boolean isPrefixDeclarationCommand() {
        return new IsPrefixDeclarationTemplateMetod() {
            @Override
            protected boolean isPrefixName() {
                return isPrefixCommand();
            }
        }.jePrefix();
    }

    public boolean isPrefixDeclarationVariable() {
        return new IsPrefixDeclarationTemplateMetod() {
            @Override
            protected boolean isPrefixName() {
                return isPrefixVariable();
            }
        }.jePrefix();
    }

    public ReservedWordEnum vratPrefixZakazaneSlovo()  {
        findNextCharacter();
        String s = WordReader.lookAhead(endOfLine(), 0);
        return ReservedWordEnum.makeSymbol(s);
    }

    public String getLine(int line) {
        return lines[line];
    }

    @Override
    public String toString() {
        if (isEndOfFile()) return "eof";
        if (inx != null && lines != null && inx.getY() < lines.length)
            return inx.toString() + "  : " + lines[inx.getY()].toString();
        return super.toString();
    }
}
