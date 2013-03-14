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

package sk.wlio.sx2.parsers.expression;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.parsers.Readers;
import sk.wlio.sx2.interfaces.SxParser;

public class IntReader implements SxParser<Int> {

    public Int read(TextContext tC)  {
        Position position = tC.findNextCharacter();
        String aInt = Readers.word().read(tC).toString();

        try {
            return new Int( Integer.valueOf(aInt ), position);
        } catch (NumberFormatException e) {
            throw SxException.create( SxExTyp.EXPECTED_INT, tC);
        }
    }
}
