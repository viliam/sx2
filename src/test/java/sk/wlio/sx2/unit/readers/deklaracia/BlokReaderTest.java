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

package sk.wlio.sx2.unit.readers.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.instruction.Block;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.statement.BlockReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class BlokReaderTest extends AbstractReaderTest {

    @Test
    public void testBlok()  {
        TestTemplate<Block> tt = new TestTemplate<Block>(sb, new BlockReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setVystup( new Bracket(new Position(0,0), SymbolEnum.BRACKET_NORM_OPEN));
                mr.zatvorka().setPosun( 3,0 ,  2,0 );
                mr.instrukcia().setPosun(  7,0 ,  9,0 );
            }
        };
        tt.run( "  { a = 4; cislo b; } ",
                "bracket;statement;statement;bracket;");

        Block block = tt.getVysledok();
        Statement[] inst = block.getInstrukcie();
        assertEquals( inst.length, 2);
    }

}
