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

package sk.wlio.sx2.readers.vyraz.zatvorka;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazVzatvorkeReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        Parenthesis z1 = Readers.zatvorka().citaj(tC);
        IVyraz vyraz= Readers.vyraz().citaj(tC);
        Parenthesis z2 = Readers.zatvorka().citaj(tC);
        return new VyrazVzatvorke(z1, vyraz, z2);
    }

}
