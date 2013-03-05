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
import sk.wlio.sx2.beans.statement.Block;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.interfaces.TextReader;

import java.util.LinkedList;
import java.util.List;

public class BlockReader implements TextReader<Block> {

    public Block read(TextContext tC)  {
        TextReader<Bracket> zR = Readers.bracket();
        Bracket z1 = zR.read(tC);

        //for each lines
        List<Statement> linesList = new LinkedList<Statement>();
        TextReader<Statement> iR = Readers.statment();

        while ( tC.nextCharacter()!='}') {
            linesList.add(iR.read(tC));
        }

        Bracket z2 = zR.read(tC);
        //prehodim LinkedList na pole
        Statement[] inf= linesList.toArray(new Statement[linesList.size()]);

        return new Block( inf,  z1,z2);
    }
}
