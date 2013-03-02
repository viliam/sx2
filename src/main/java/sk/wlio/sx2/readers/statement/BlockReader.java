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

package sk.wlio.sx2.readers.statement;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Block;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;

import java.util.LinkedList;
import java.util.List;

public class BlockReader implements TextReader<Block> {

    public Block citaj(TextContext tC)  {
        TextReader<Bracket> zR = Readers.zatvorka();
        Bracket z1 = zR.citaj(tC);

        //for each lines
        List<Instrukcia> linesList = new LinkedList<Instrukcia>();
        TextReader<Instrukcia> iR = Readers.instrukcia();

        while ( tC.getNasledujuciZnak()!='}') {
            linesList.add(iR.citaj(tC));
        }

        Bracket z2 = zR.citaj(tC);
        //prehodim LinkedList na pole
        Instrukcia[] inf= linesList.toArray(new Instrukcia[linesList.size()]);

        return new Block( inf,  z1,z2);
    }
}
