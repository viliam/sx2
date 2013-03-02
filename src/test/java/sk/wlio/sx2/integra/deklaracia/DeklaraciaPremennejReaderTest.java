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
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.statement.DeclarationVariableReader;
import sk.wlio.sx2.rozhrania.TextReader;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class DeklaraciaPremennejReaderTest {
    @Test
    public void testCislo()  {
        DeclarationVariable dekPremena = citajDekPremennej("  cislo a; ");
        assertNotNull( dekPremena);
        assertEquals( "a" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.CISLO , dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Position(2,0) , dekPremena.getPosition());
    }

    @Test
    public void testBool()  {
        DeclarationVariable dekPremena = citajDekPremennej("  bool a; ");
        assertNotNull( dekPremena);
        assertEquals( "a" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.BOOL , dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Position(2,0) , dekPremena.getPosition());
    }

    @Test
    public void testPriradenie()  {
        DeclarationVariable dekPremena = citajDekPremennej(" cislo v = 3;");
        assertNotNull( dekPremena);
        assertEquals( "v" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.CISLO, dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Position(1,0) , dekPremena.getPosition());

        Assignment p = dekPremena.getAssignment();
        assertNotNull( p);
    }

    @Test
    public void testZlyDatovyTyp() {
        TextContext text = new TextContext("  nic a; ");
        TextReader<DeclarationVariable> dpReader = new DeclarationVariableReader();
        try {
            dpReader.citaj( text);
            fail();
        } catch (SxException e) {

        }
    }

//    @Test
//    public void testZlyNazovPremenej() {
//        TextContext text = new TextContext("  bool ahoj(); ");
//        TextReader<DeklaraciaPremennej> dpReader = new DeklaraciaPremennejReader();
//        try {
//            dpReader.citaj( text);
//            fail();
//        } catch (SxException e) {
//
//        }
//    }

    private DeclarationVariable citajDekPremennej(String ts)  {
        TextContext text = new TextContext(ts);
        TextReader<DeclarationVariable> dpReader = new DeclarationVariableReader();
        DeclarationVariable dekPremena= dpReader.citaj( text);
        return dekPremena;
    }

}
