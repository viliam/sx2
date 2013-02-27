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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaParameter;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.DeklaraciaParameterReader;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class DeklaraciaParameterReaderTest extends AbstractReaderTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeklaraciaParameter> tt =
            new TestTemplate<DeklaraciaParameter>(sb, new DeklaraciaParameterReader()) {
                @Override
                public void nastavReader() {
                    mr.zatvorka().setPosun(  1,0 ,  1,0 );
                    mr.dekPremennej().setPosun( 3,0 ,  2,0 );
                    mr.ciarka().setPosun( 1,0 );
                }
            };
        tt.run("( aa,bb)", "zatvorka;dekPremennej;ciarka;dekPremennej;zatvorka;");
    }

    @Test
    public void testEmpty()  {
        TestTemplate<DeklaraciaParameter> tt =
            new TestTemplate<DeklaraciaParameter>(sb, new DeklaraciaParameterReader()) {
                @Override
                public void nastavReader() {
                    mr.zatvorka().setPosun(  1,0 ,  1,0 );
                }
            };
        tt.run("()", "zatvorka;zatvorka;");
    }


    @Test
    public void testChyba() {
        try {
            mr.dekPremennej().setPosun( 2, 0 );
            mr.zatvorka().setPosun( 1,0 ,   1,0 );
            citajDekParameter("( aa,)");
            fail("Cakal chybu, ocakavany znak zatvorka alebo ciarka");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.CAKAL_ZATVORKU_ALEBO_CIARKU,  e.getTyp());
        }

    }

    private DeklaraciaParameter citajDekParameter(String ts)  {
        TextContext text = new TextContext(ts);
        TextReader<DeklaraciaParameter> dpReader = new DeklaraciaParameterReader();
        DeklaraciaParameter dekParameter= dpReader.citaj( text);
        return dekParameter;
    }

}
