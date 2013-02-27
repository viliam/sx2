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

package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.Parametre;
import sk.wlio.sx2.beans.instrukcia.Prikaz;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class PrikazReader implements TextReader<Prikaz> {
    
    public Prikaz citaj(TextContext tC)  {
        Slovo nazovPrikaz = Readers.slovo().citaj( tC);
        Parametre parametre  = Readers.parametre().citaj(tC);
        return new Prikaz(nazovPrikaz, parametre);
    }
}
