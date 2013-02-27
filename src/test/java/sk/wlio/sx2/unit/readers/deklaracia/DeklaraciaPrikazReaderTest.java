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
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPrikaz;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.DeklaraciaPrikazReader;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.*;

public class DeklaraciaPrikazReaderTest extends AbstractReaderTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeklaraciaPrikaz> tt =
            new TestTemplate<DeklaraciaPrikaz>(sb, new DeklaraciaPrikazReader()) {
                @Override
                public void nastavReader() {
                    mr.dekParameter().setPosun( 2,0);
                    mr.datovyTyp().setPosun( 5,0);
                    mr.datovyTyp().setVystup( new DatovyTyp( null, "bool"));
                    mr.slovo().setPosun(6,0,  6,0 );
                    mr.slovo().setVystup( new Slovo(null, "aa"), new Slovo(null, "ahoj"));
                    mr.blok().setPosun( 11, 0 );
                }
            };
        tt.run("  bool ahoj() { vrat 3; } ", "datovyTyp;slovo;slovo;dekParameter;blok;");

        DeklaraciaPrikaz dp = tt.getVysledok();
        assertEquals("nazov prikazu", "ahoj", dp.getNazov().toString() );
    }

    @Test
    public void testNiejeDatovyTyp() {
        try {
            mr.datovyTyp().setPosun( 5,0 );
            mr.datovyTyp().setVystup( new DatovyTyp(null, "boool"));

            citajDekPrikaz("  boool ahoj() { vrat 3; } ");
            fail("Cakal chybu, zla deklaracia prikazu");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.CAKAL_DEKLARACIU_PRIKAZU,  e.getTyp());
        }
    }

    @Test
    public void testZlyNazovPrikazu() {
        try {
            mr.datovyTyp().setPosun( 5,0 );
            mr.datovyTyp().setVystup( new DatovyTyp(null, "bool"));

            mr.slovo().setPosun( 4,0 ,  4,0 );
            mr.slovo().setVystup( new Slovo(null, "3ahoj"));

            citajDekPrikaz("  bool 3ahoj() { vrat 3; } ");
            fail("Cakal chybu, zly nazov prikazu");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.ZLY_NAZOV_PRIKAZU,  e.getTyp());
        }
    }

    private DeklaraciaPrikaz citajDekPrikaz(String ts)  {
        TextContext text = new TextContext(ts);
        TextReader<DeklaraciaPrikaz> dpReader = new DeklaraciaPrikazReader();
        DeklaraciaPrikaz dekPrikaz= dpReader.citaj( text);
        assertNotNull("nenulovy prikaz", dekPrikaz);
        return dekPrikaz;
    }
}
