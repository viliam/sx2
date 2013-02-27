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

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;

public class ZatvorkaReader extends SymbolAbstractReader<Zatvorka> {

    @Override
    protected String[] getSymbols() {
        return SymbolsEnum.ZATVORKY.getSymbols();
    }

    @Override
    protected Zatvorka create(Pozicia pozicia, SymbolEnum oEnum)  {
        if (oEnum == null)
            throw SxException.create(SxExTyp.CAKAL_ZATVORKU, pozicia);
        return new Zatvorka(pozicia, oEnum );
    }

    @Override
    protected SxExTyp getExceptionTyp() {
        return SxExTyp.CAKAL_ZATVORKU;
    }
}
