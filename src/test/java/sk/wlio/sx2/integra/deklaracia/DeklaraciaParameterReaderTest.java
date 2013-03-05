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

package sk.wlio.sx2.integra.deklaracia;

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

public class DeklaraciaParameterReaderTest {

    @Test
    public void testEmpty()  {
        DeclarationParameter dp = citajDekParameter("()");
        List<Comma> ciarky = dp.getLiComma();
        assertEquals("pocet ciarok",0 , ciarky.size());
        List<DeclarationVariable> dekPremenne = dp.getLiDekPremennej();
        assertEquals("pocet decVariable", 0, dekPremenne.size() );
        assertNotNull("nenulova zatvorka1",  dp.getZ1());
        assertNotNull("nenulova zatvorka2", dp.getZ2());
    }

    @Test
    public void testBasic()  {
        DeclarationParameter dp = citajDekParameter("( bool v, int d)");
        List<Comma> ciarky = dp.getLiComma();
        assertEquals("count of comma",1 , ciarky.size());
        assertNotNull("not null comma", ciarky.get(0));
        List<DeclarationVariable> dekPremenne = dp.getLiDekPremennej();
        assertEquals("count of variable declaration", 2, dekPremenne.size() );
        assertNotNull("first not null comma",  dp.getZ1());
        assertNotNull("second not null comma", dp.getZ2());
    }

    @Test
    public void testChyba() {
        try {
            citajDekParameter("( aa,)");
            fail();
        } catch (SxException e) {
            assertEquals( "Exception type", SxExTyp.EXPECTED_DATA_TYPE,  e.getType());
        }

    }

    private DeclarationParameter citajDekParameter(String ts)  {
        TextContext text = new TextContext(ts);
        DeclarationParameter dekParameter= Readers.dekParameter().read(text);
        return dekParameter;
    }

}
