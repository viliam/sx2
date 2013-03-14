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

package sk.wlio.sx2.unit.parsers.statement;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.*;
import sk.wlio.sx2.parsers.statement.CommandParser;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CommandParserTest extends AbstractParserTest {

    @Test
    public void testBasic()  {
        TestTemplate<Command> tt =new TestTemplate<Command>( sb, new CommandParser()) {
            @Override public void setUpParsers() {
                mr.word().setShift(4, 0);
                mr.word().setOutput(new Word(null, "ahoj"));
                mr.paramters().setShift(8, 0);
            }
        };
        tt.run("   ahoj(3 , p);  ","word;parameters;");
        Command command = tt.getVysledok();
        assertNotNull(command);
        assertEquals( "ahoj", command.getNazov().toString() );
    }

}
