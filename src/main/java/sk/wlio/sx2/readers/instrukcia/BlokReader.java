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
import sk.wlio.sx2.beans.instruction.Block;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;

import java.util.LinkedList;
import java.util.List;

public class BlokReader implements TextReader<Block> {

    public Block citaj(TextContext tC)  {
        TextReader<Zatvorka> zR = Readers.zatvorka();
        Zatvorka z1 = zR.citaj(tC);

        //pre kazdy riadok
        List<Instrukcia> riadkyList = new LinkedList<Instrukcia>();
        TextReader<Instrukcia> iR = Readers.instrukcia();

        while ( tC.getNasledujuciZnak()!='}') {
            riadkyList.add( iR.citaj( tC));
        }

        Zatvorka z2 = zR.citaj(tC);
        //prehodim LinkedList na pole
        Instrukcia[] inf= riadkyList.toArray(new Instrukcia[riadkyList.size()]);

        return new Block( inf,  z1,z2);
    }
}
