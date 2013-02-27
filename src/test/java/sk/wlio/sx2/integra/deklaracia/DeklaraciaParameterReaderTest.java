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
import sk.wlio.sx2.beans.instrukcia.DeklaraciaParameter;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.symbol.Ciarka;
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
        DeklaraciaParameter dp = citajDekParameter("()");
        List<Ciarka> ciarky = dp.getLiCiarka();
        assertEquals("pocet ciarok",0 , ciarky.size());
        List<DeklaraciaPremennej> dekPremenne = dp.getLiDekPremennej();
        assertEquals("pocet dekPremennej", 0, dekPremenne.size() );
        assertNotNull("nenulova zatvorka1",  dp.getZ1());
        assertNotNull("nenulova zatvorka2", dp.getZ2());
    }

    @Test
    public void testBasic()  {
        DeklaraciaParameter dp = citajDekParameter("( bool v, cislo d)");
        List<Ciarka> ciarky = dp.getLiCiarka();
        assertEquals("pocet ciarok",1 , ciarky.size());
        assertNotNull("nenulova ciarka", ciarky.get(0));
        List<DeklaraciaPremennej> dekPremenne = dp.getLiDekPremennej();
        assertEquals("pocet dekPremennej", 2, dekPremenne.size() );
        assertNotNull("nenulova zatvorka1",  dp.getZ1());
        assertNotNull("nenulova zatvorka2", dp.getZ2());
    }

    @Test
    public void testChyba() {
        try {
            citajDekParameter("( aa,)");
            fail();
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.CAKAL_DATOVY_TYP,  e.getTyp());
        }

    }

    private DeklaraciaParameter citajDekParameter(String ts)  {
        TextContext text = new TextContext(ts);
        DeklaraciaParameter dekParameter= Readers.dekParameter().citaj( text);
        return dekParameter;
    }

}
