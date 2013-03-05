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

package sk.wlio.sx2.unit.parsers.declaration;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.statement.Block;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.statement.BlockReader;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class BlokParserTest extends AbstractParserTest {

    @Test
    public void testBlok()  {
        TestTemplate<Block> tt = new TestTemplate<Block>(sb, new BlockReader()) {
            @Override public void setUpParsers() {
                mr.bracket().setOutput(new Bracket(new Position(0, 0), SymbolEnum.BRACKET_NORM_OPEN));
                mr.bracket().setShift(3, 0, 2, 0);
                mr.statement().setShift(7, 0, 9, 0);
            }
        };
        tt.run( "  { a = 4; int b; } ",
                "bracket;statement;statement;bracket;");

        Block block = tt.getVysledok();
        Statement[] inst = block.getInstrukcie();
        assertEquals( inst.length, 2);
    }

}
