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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.statement.DeclarationParameter;
import sk.wlio.sx2.beans.statement.DeclarationVariable;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class DeclarationParametersReaderTest {

    @Test
    public void testEmpty()  {
        DeclarationParameter dp = readDecParameters("()");
        List<Comma> ciarky = dp.getLiComma();
        assertEquals("comma count",0 , ciarky.size());
        List<DeclarationVariable> decVariable = dp.getLiDecVariable();
        assertEquals("decVariable count", 0, decVariable.size());
        assertNotNull("not null bracket1",  dp.getB1());
        assertNotNull("not null bracket2", dp.getB2());
    }

    @Test
    public void testBasic()  {
        DeclarationParameter dp = readDecParameters("( bool v, int d)");
        List<Comma> commas = dp.getLiComma();
        assertEquals("count of comma", 1, commas.size());
        assertNotNull("not null comma", commas.get(0));
        List<DeclarationVariable> decVariables = dp.getLiDecVariable();
        assertEquals("count of declaration variable", 2, decVariables.size());
        assertNotNull("first not null comma",  dp.getB1());
        assertNotNull("second not null comma", dp.getB2());
    }

    @Test
    public void testFail() {
        try {
            readDecParameters("( aa,)");
            fail();
        } catch (SxException e) {
            assertEquals( "Exception type", SxExTyp.EXPECTED_DATA_TYPE,  e.getType());
        }

    }

    private DeclarationParameter readDecParameters(String ts)  {
        TextContext text = new TextContext(ts);
        return Readers.decParameters().read(text);
    }

}
