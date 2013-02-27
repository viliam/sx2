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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class CisloReaderTest {

    @Test
    public void testReadCislo()  {
        final int testCislo = 123;
        TextContext text = new TextContext("  " + testCislo);
        Int anInt = Readers.cislo().citaj( text);
        assertEquals( "Pozicia kontrola ", new Position(2,0), anInt.getPosition() );
        assertEquals( "Cislo kontrola ", testCislo, anInt.getCislo().intValue() );
    }

    @Test
    public void testReadCisloFail()  {
        final String testCislo = "123d3";
        TextContext text = new TextContext("  " + testCislo);
        try {
            Readers.cislo().citaj( text);
            fail();
        } catch (SxException e) {
             assertEquals( SxExTyp.CAKAL_CISLO, e.getTyp());
        }
    }

}
