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
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.ZatvorkaReader;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public abstract class VyrazVzatvorkeAbstractReader implements TextReader<IVyraz> {

    protected abstract TextReader<IVyraz> getVyrazReader();

    public IVyraz citaj(TextContext tC)  {
        TextReader<Zatvorka> zReader = new ZatvorkaReader();
        Zatvorka z1 = Readers.zatvorka().citaj(tC);
        IVyraz vyraz= getVyrazReader().citaj(tC);
        Zatvorka z2 = Readers.zatvorka().citaj(tC);
        return new VyrazVzatvorke(z1, vyraz, z2);
    }
}