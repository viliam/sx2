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

package sk.wlio.sx2.unit.readers.znak;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class ZatvorkaReaderTest {

    @Test
    public void testZatvorka()  {
        TextContext tC= new TextContext("  ( ");
        Zatvorka zatvorka = Readers.zatvorka().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Pozicia(2,0), zatvorka.getPozicia() );
        assertEquals( "Cislo kontrola ", "(", zatvorka.getSymbol() );
        assertEquals( "Posunuty inx", new Pozicia(3,0), tC.getPozicia() );
    }

    @Test
    public void testNecakalZatvorku() {
        TextContext tC = new TextContext(" k");
        try {
            Readers.zatvorka().citaj( tC);
            fail();
        } catch (SxException e) {
            assertEquals( SxExTyp.CAKAL_ZATVORKU, e.getTyp() );
        }
    }


}
