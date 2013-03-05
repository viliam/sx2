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

package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.TextUtils;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.TextReader;

public class WordReader implements TextReader<Word> {

    public Word read(TextContext tC)  {
        tC.findNextCharacter();
        Position inx= tC.getPosition();
        int beginX =inx.getX();

        String line = tC.getLine();
        String word = lookAhead(line, beginX);
        if ("".equals(word) )
            throw SxException.create(SxExTyp.PRAZDNE_SLOVO, tC);

        int endX = beginX + word.length();
        tC.setPosition(new Position(endX, inx.getY()));
        return new Word(inx, word);
    }

    public static String lookAhead(String line, int x) {
        int konX = findEndOfWord(line, x);
        if ( x >= konX) {
            return "";
        }
        return line.substring(x, konX);
    }

    private static int findEndOfWord(String line, int x)  {
        if (x>=line.length() ) return x;
        int endX = x;
         //kym sa nerovnas zakazanym znakom ani operatorom, ani koncu riadka, tak v pohode
        do {
            char p = line.charAt( endX);
            if (!TextUtils.isLetter(p) && !TextUtils.isInt(p))
                return endX;

            endX++;
        } while ( endX<line.length() );

        return endX;
    }

}
