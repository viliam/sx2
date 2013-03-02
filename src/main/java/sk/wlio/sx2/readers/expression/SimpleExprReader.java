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

package sk.wlio.sx2.readers.expression;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class SimpleExprReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        if ( tC.jePrefixCislo())
            return Readers.cislo().citaj(tC);

        if ( tC.jePrefixPremenna())
            return Readers.premena().citaj(tC);

        if (tC.jePrefixDatovyTyp())
            return Readers.datovyTyp().citaj(tC);

        if (tC.jePrefixPrikaz() )
                return Readers.prikaz().citaj(tC);


        throw SxException.create( SxExTyp.UNEXPECTED_PREFIX, tC );
    }
}
