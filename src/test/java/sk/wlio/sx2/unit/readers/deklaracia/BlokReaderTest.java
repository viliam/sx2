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
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.instrukcia.Blok;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.instrukcia.BlokReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class BlokReaderTest extends AbstractReaderTest {

    @Test
    public void testBlok()  {
        TestTemplate<Blok> tt = new TestTemplate<Blok>(sb, new BlokReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setVystup( new Zatvorka(new Pozicia(0,0), SymbolEnum.ZATVORKA_NORM_OTOVRENA));
                mr.zatvorka().setPosun( 3,0 ,  2,0 );
                mr.instrukcia().setPosun(  7,0 ,  9,0 );
            }
        };
        tt.run( "  { a = 4; cislo b; } ",
                "zatvorka;instrukcia;instrukcia;zatvorka;");

        Blok blok = tt.getVysledok();
        Instrukcia[] inst = blok.getInstrukcie();
        assertEquals( inst.length, 2);
    }

}
