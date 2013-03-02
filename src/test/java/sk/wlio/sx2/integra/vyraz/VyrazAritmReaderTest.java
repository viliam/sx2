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

package sk.wlio.sx2.integra.vyraz;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.beans.vyraz.VyrazAritm;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.expression.VyrazAritmReader;
import sk.wlio.sx2.rozhrania.ISlovo;

public class VyrazAritmReaderTest {


    @Test
    /*
    Tento test je urceny na testovanie stromovej struktury, ktora vznika rozlicne
    na zaklade toho ako je expression zatvorkami prioritizovany.
     */
    public void testZatvorkyVStrome()  {
        VyrazAritm vyraz = citajAritmVyraz(" 4 +( 5 -3) ");
        assertEquals( "4+(5-3)", vyraz.toString());
        assertTrue( vyraz.getV1() instanceof Int);
        assertTrue( vyraz.getV2() instanceof VyrazVzatvorke);
        VyrazVzatvorke vrzVzatvorke= (VyrazVzatvorke) vyraz.getV2();
        assertTrue( vrzVzatvorke.getV() instanceof VyrazAritm);

        VyrazAritm vyraz1 = citajAritmVyraz(" 4 +( 5 -3) + 3");
        assertEquals( "4+(5-3)+3", vyraz1.toString());
        assertTrue( vyraz1.getV1() instanceof Int);
        assertTrue( vyraz1.getV2() instanceof VyrazAritm);
        VyrazAritm podVyraz = (VyrazAritm) vyraz1.getV2();
        assertTrue( podVyraz.getV1() instanceof VyrazVzatvorke);
        assertTrue( podVyraz.getV2() instanceof Int);

        VyrazAritm vyraz2 = citajAritmVyraz(" (4 + 5) -3 ");
        assertEquals( "(4+5)-3", vyraz2.toString());
        assertTrue( vyraz2.getV1() instanceof VyrazVzatvorke);
        assertTrue( vyraz2.getV2() instanceof Int);
        VyrazVzatvorke podVyraz2 = (VyrazVzatvorke) vyraz2.getV1();
        assertTrue( ((VyrazAritm) podVyraz2.getV()).getV1() instanceof Int);
        assertTrue( ((VyrazAritm) podVyraz2.getV()).getV2() instanceof Int);

    }

    @Test
    public void testVyrazAritm()  {
        VyrazAritm vyraz = citajAritmVyraz(" 4 + 5 ");
        assertTrue( vyraz.getV1() instanceof Int);
        assertTrue( vyraz.getV2() instanceof Int);
        Int v1 = (Int) vyraz.getV1();
        Int v2 = (Int) vyraz.getV2();
        assertEquals( v1.getCislo().intValue() , 4 );
        assertEquals( v2.getCislo().intValue() , 5 );

        Operator oa = vyraz.getOp();
        assertEquals( oa.getSymbol(), "+");
    }

    @Test
    public void testVyrazFail() {
        try {
            citajAritmVyraz(" 4 + 5 + ");
            fail();
        } catch (SxException ex) {

        }
    }


    private VyrazAritm citajAritmVyraz(String tento)  {
        TextContext tC = new TextContext(tento);
        VyrazAritmReader vAritmReader = new VyrazAritmReader();
        ISlovo slovo = vAritmReader.citaj(tC);

        assertNotNull(slovo);
        assertTrue( "Slovo nie je instancia VyrazArtim. Slovo.class = " + slovo.getClass().getName()
                , slovo instanceof VyrazAritm);
        VyrazAritm vyraz = (VyrazAritm) slovo;
        assertEquals( Enums.VyrazTyp.CISLO, vyraz.getVyrazTyp());

        return vyraz;
    }
}
