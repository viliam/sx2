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
import sk.wlio.sx2.readers.statement.DeclarationParameterReader;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class DeklaraciaParameterParserTest extends AbstractParserTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeclarationParameter> tt =
            new TestTemplate<DeclarationParameter>(sb, new DeclarationParameterReader()) {
                @Override
                public void setUpParsers() {
                    mr.bracket().setShift(1, 0, 1, 0);
                    mr.decVariable().setShift(3, 0, 2, 0);
                    mr.comma().setPosun( 1,0 );
                }
            };
        tt.run("( aa,bb)", "bracket;decVariable;comma;decVariable;bracket;");
    }

    @Test
    public void testEmpty()  {
        TestTemplate<DeclarationParameter> tt =
            new TestTemplate<DeclarationParameter>(sb, new DeclarationParameterReader()) {
                @Override
                public void setUpParsers() {
                    mr.bracket().setShift(1, 0, 1, 0);
                }
            };
        tt.run("()", "bracket;bracket;");
    }


    @Test
    public void testChyba() {
        try {
            mr.decVariable().setPosun( 2, 0 );
            mr.bracket().setShift(1, 0, 1, 0);
            citajDekParameter("( aa,)");
            fail("Cakal chybu, ocakavany znak bracket alebo comma");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.EXPECTED_BRACKET_OR_COMMA,  e.getType());
        }

    }

    private DeclarationParameter citajDekParameter(String ts)  {
        TextContext text = new TextContext(ts);
        SxParser<DeclarationParameter> dpReader = new DeclarationParameterReader();
        DeclarationParameter dekParameter= dpReader.read(text);
        return dekParameter;
    }

}
