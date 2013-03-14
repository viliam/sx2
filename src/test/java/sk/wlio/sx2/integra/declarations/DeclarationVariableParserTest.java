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

package sk.wlio.sx2.integra.declarations;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.statement.Assignment;
import sk.wlio.sx2.beans.statement.DeclarationVariable;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.parsers.statement.DeclarationVariableParser;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class DeclarationVariableParserTest {
    @Test
    public void testInt()  {
        DeclarationVariable decVariable = readDecVariable("  int a; ");
        assertNotNull( decVariable);
        assertEquals("a", decVariable.getName().toString());
        assertEquals(Enums.ExpType.INT, decVariable.getDataType().getTyp() );
        assertEquals(new Position(2, 0), decVariable.getPosition());
    }

    @Test
    public void testBool()  {
        DeclarationVariable decVariable = readDecVariable("  bool a; ");
        assertNotNull( decVariable);
        assertEquals("a", decVariable.getName().toString());
        assertEquals(Enums.ExpType.BOOL , decVariable.getDataType().getTyp() );
        assertEquals(new Position(2, 0), decVariable.getPosition());
    }

    @Test
    public void testAssignment()  {
        DeclarationVariable decVariable = readDecVariable(" int v = 3;");
        assertNotNull( decVariable);
        assertEquals("v", decVariable.getName().toString());
        assertEquals(Enums.ExpType.INT, decVariable.getDataType().getTyp() );
        assertEquals(new Position(1, 0), decVariable.getPosition());

        Assignment p = decVariable.getAssignment();
        assertNotNull( p);
    }

    @Test
    public void testFail() {
        TextContext text = new TextContext("  void a; ");
        SxParser<DeclarationVariable> dpReader = new DeclarationVariableParser();
        try {
            dpReader.read(text);
            fail();
        } catch (SxException e) {

        }
    }

    private DeclarationVariable readDecVariable(String ts)  {
        TextContext text = new TextContext(ts);
        SxParser<DeclarationVariable> dpReader = new DeclarationVariableParser();
        DeclarationVariable dekPremena= dpReader.read(text);
        return dekPremena;
    }

}
