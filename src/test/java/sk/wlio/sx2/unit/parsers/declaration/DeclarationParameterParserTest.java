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
import sk.wlio.sx2.beans.statement.DeclarationParameter;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.parsers.statement.DeclarationParameterParser;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class DeclarationParameterParserTest extends AbstractParserTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeclarationParameter> tt =
            new TestTemplate<DeclarationParameter>(sb, new DeclarationParameterParser()) {
                @Override
                public void setUpParsers() {
                    mr.bracket().setShift(1, 0, 1, 0);
                    mr.decVariable().setShift(3, 0, 2, 0);
                    mr.comma().setShift(1, 0);
                }
            };
        tt.run("( aa,bb)", "bracket;decVariable;comma;decVariable;bracket;");
    }

    @Test
    public void testEmpty()  {
        TestTemplate<DeclarationParameter> tt =
            new TestTemplate<DeclarationParameter>(sb, new DeclarationParameterParser()) {
                @Override
                public void setUpParsers() {
                    mr.bracket().setShift(1, 0, 1, 0);
                }
            };
        tt.run("()", "bracket;bracket;");
    }


    @Test
    public void testFail() {
        try {
            mr.decVariable().setShift(2, 0);
            mr.bracket().setShift(1, 0, 1, 0);
            readDecParameter("( aa,)");
            fail();
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.EXPECTED_BRACKET_OR_COMMA,  e.getType());
        }

    }

    private DeclarationParameter readDecParameter(String ts)  {
        //todo: make generic factory method to create TextContext and Parsers
        TextContext text = new TextContext(ts);
        SxParser<DeclarationParameter> dpReader = new DeclarationParameterParser();
        return dpReader.read(text);
    }

}
