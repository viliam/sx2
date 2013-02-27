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

package sk.wlio.sx2.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.TextReader;

public abstract class RezervovaneSlovaAbstractReader<E extends Slovo> implements TextReader<E> {

    public E citaj(TextContext tC)  {
        Slovo slovo = Readers.slovo().citaj( tC);
        String s = slovo.toString();

        if (!getZakazaneSlova().je(s))
           throw SxException.create(getSxExceptionTyp(), tC);

        return (E) slovo;
    }

    protected abstract SxExTyp getSxExceptionTyp();

    protected abstract RezervovaneSlovaEnum getZakazaneSlova();

}