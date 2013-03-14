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

package sk.wlio.sx2.parsers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.SxParser;

public abstract class ReserverdWordAbstractReader<E extends Word> implements SxParser<E> {

    public E read(TextContext tC)  {
        Word word = Readers.word().read(tC);
        String s = word.toString();

        if (!getZakazaneSlova().is(s))
           throw SxException.create(getSxExceptionTyp(), tC);

        return (E) word;
    }

    protected abstract SxExTyp getSxExceptionTyp();

    protected abstract RezervedWordsEnum getZakazaneSlova();

}