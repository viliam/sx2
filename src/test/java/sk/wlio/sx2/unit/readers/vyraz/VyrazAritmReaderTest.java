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

package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.vyraz.VyrazAritmReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;
import sk.wlio.sx2.unit.readers.mock.TestVyraz;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class VyrazAritmReaderTest extends AbstractReaderTest {

    @Test
    public void testPrvyVyrazZatvorkaKoniec()  {
        new TestTemplate<IVyraz>(sb, new VyrazAritmReader()) {
            @Override
            public void nastavReader() {
                mr.vrzAritmVzatvorke().setPosun( 6, 0);
                mr.vrzAritmVzatvorke().setVystup(new TestVyraz());
            }
        }.run("(ahoj)", "vrzAritmVzatvorke;");
    }

    @Test
    public void testPrvyVyrazJednoduchyKoniec()  {
        new TestTemplate<IVyraz>(sb, new VyrazAritmReader()) {
            @Override
            public void nastavReader() {
                mr.vrzJednduchy().setPosun( 4,0);
                mr.vrzJednduchy().setVystup( new TestVyraz());
            }
        }.run("ahoj", "vrzJednoduchy;");
    }


    @Test
    public void testVyraz()  {
        new TestTemplate<IVyraz>(sb, new VyrazAritmReader()) {
            @Override
            public void nastavReader() {
                mr.vrzJednduchy().setPosun( 4,0, 4,0);
                mr.vrzJednduchy().setVystup( new TestVyraz(), new TestVyraz());
                mr.opAritm().setPosun( 1, 0);
            }
        }.run("ahoj+ahoj", "vrzJednoduchy;opAritm;vrzJednoduchy;");
    }

    @Test
    public void testPrvyVyrazNeciselny() {
        mr.vrzJednduchy().setPosun( 4,0);
        mr.vrzJednduchy().setVystup( new TestVyraz(Enums.VyrazTyp.BOOL));
        TextContext tC = new TextContext("ahoj");
        try {
            new VyrazAritmReader().citaj(tC);
            fail();
        } catch (SxException e) {
            assertEquals("vrzJednoduchy;", sb.toString());
        }
    }

    @Test
    public void testNespravyOperator() {
        mr.vrzJednduchy().setPosun( 4,0);
        mr.vrzJednduchy().setVystup(new TestVyraz());
        TextContext tC = new TextContext("ahoj#");
        try {
            new VyrazAritmReader().citaj(tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.CAKAL_OPERATOR, e.getTyp());
        }

    }

}
