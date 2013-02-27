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

package sk.wlio.sx2.readers.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.VyrazAritm;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazAritmReader extends VyrazAbstractReader implements TextReader<IVyraz> {

    @Override
    protected IVyraz citajPrvyVyraz(TextContext tC) {
        if ( tC.jePrefixZatvorkaOtovorena() )  {
            return Readers.vrzAritmVzatvorke().citaj(tC);
        }

        return Readers.vrzJednduchy().citaj(tC);
    }

    @Override
    protected void skontrolujTyp(Enums.VyrazTyp vyrazTyp, TextContext tC) {
        if (vyrazTyp != Enums.VyrazTyp.CISLO &&
            vyrazTyp != Enums.VyrazTyp.NEURCENY )
            throw SxException.create(SxExTyp.CAKAL_CISLO, tC);
    }

    @Override
    protected IVyraz citajZlozenyVyraz(TextContext tC, IVyraz o1)  {
        if ( tC.jePrefixOperatorAritm()  )   {
            Operator op = Readers.opAritm().citaj(tC);
            IVyraz o2  = citaj(tC);
            return new VyrazAritm(  o1, op, o2);
        }
        return null;
    }

}
