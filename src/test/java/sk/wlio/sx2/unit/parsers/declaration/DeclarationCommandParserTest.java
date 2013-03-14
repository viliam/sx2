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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.DeclarationCommand;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.parsers.statement.DeclarationCommandParser;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.*;

public class DeclarationCommandParserTest extends AbstractParserTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeclarationCommand> tt =
            new TestTemplate<DeclarationCommand>(sb, new DeclarationCommandParser()) {
                @Override
                public void setUpParsers() {
                    mr.decParamters().setShift(2, 0);
                    mr.dataType().setShift(5, 0);
                    mr.dataType().setOutput(new DataType(null, "bool"));
                    mr.word().setShift(6, 0, 6, 0);
                    mr.word().setOutput(new Word(null, "aa"), new Word(null, "ahoj"));
                    mr.block().setShift(11, 0);
                }
            };
        tt.run("  bool ahoj() { return 3; } ", "dataType;word;word;decParameter;block;");

        DeclarationCommand dp = tt.getVysledok();
        assertEquals("nazov prikazu", "ahoj", dp.getName().toString() );
    }

    @Test
    public void testDataTypeFail() {
        try {
            mr.dataType().setShift(5, 0);
            mr.dataType().setOutput(new DataType(null, "boool"));

            readDecCommand("  boool ahoj() { return 3; } ");
            fail("Cakal chybu, zla declarations prikazu");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.EXPECTED_COMMAND_DECLARATION,  e.getType());
        }
    }

    @Test
    public void testCommandNameFail() {
        try {
            mr.dataType().setShift(5, 0);
            mr.dataType().setOutput(new DataType(null, "bool"));

            mr.word().setShift(4, 0, 4, 0);
            mr.word().setOutput(new Word(null, "3ahoj"));

            readDecCommand("  bool 3ahoj() { aReturn 3; } ");
            fail();
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.WRONG_COMMAND_NAME,  e.getType());
        }
    }

    private DeclarationCommand readDecCommand(String ts)  {
        TextContext text = new TextContext(ts);
        SxParser<DeclarationCommand> dpReader = new DeclarationCommandParser();
        DeclarationCommand decCommand= dpReader.read(text);
        assertNotNull("nenulovy command", decCommand);
        return decCommand;
    }
}
