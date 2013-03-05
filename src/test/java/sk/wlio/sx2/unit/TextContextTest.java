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

package sk.wlio.sx2.unit;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class TextContextTest {

    @Test
    public void testPosunNaNasledujuciZnak() {
        //test nasledujuceho znaku v tom istom riadku
        TextContext tc = new TextContext("  ko \r\n df  ");
        assertEquals( new Position(2,0), tc.findNextCharacter());
        //nasledujuci znak na dalsom riadku
        tc.setPosition(new Position(4, 0));
        assertEquals(new Position(1, 1), tc.findNextCharacter());
        tc.setPosition(new Position(4, 1));
        assertNull( tc.findNextCharacter());
    }

    @Test
    public void testGetNasledujuciZnak()  {
        //test nasledujuceho znaku v tom istom riadku
        TextContext tc = new TextContext("  ko df  ");
        assertEquals( 'k', tc.nextCharacter());
        assertEquals(new Position(0, 0), tc.getPosition());

        //chybovy scenar
        tc.setPosition(new Position(10, 0));
        try {
            tc.nextCharacter();
            fail();
        }   catch (SxException sx)  {
            assertEquals( SxExTyp.END_OF_FILE, sx.getType());
        }
    }

    @Test
    public void testVratPrfxZnak()  {
        TextContext tC = new TextContext("asdasd");
        assertTrue( tC.isPrefixLetter());
        tC = new TextContext(",");
        assertTrue( tC.isPrefixComma());
        tC = new TextContext("32");
        assertTrue( tC.isPrefixInt());
        tC = new TextContext("=");
        assertTrue( tC.isPrefixOperator());
    }

    @Test
    public void testVratPrfSlova()  {
        TextContext tC = new TextContext("asdasd");
        assertTrue( tC.isPrefixVariable());
        assertEquals( new Position(0,0), tC.getPosition());

        tC = new TextContext("int");
        assertTrue( tC.isPrefixDataType());

        tC = new TextContext("34");
        assertTrue( tC.isPrefixInt());

        tC = new TextContext("asdf()");
        assertTrue( tC.isPrefixCommand());

        tC = new TextContext("return ");
        assertTrue( tC.isPrefixStatement());

        tC = new TextContext("int p( ");
        assertTrue( tC.isPrefixDeclarationCommand());

        tC = new TextContext("bool er = ");
        assertTrue( tC.isPrefixDeclarationVariable());
    }

    @Test
    public void testPredcitajSlovo() {
        TextContext tC = new TextContext("asdf+");

        assertEquals( "asdf", Readers.slovo().read(tC).toString())
        ;
        assertEquals( new Position(4,0), tC.getPosition());

    }

    @Test
    public void testEndOfFile()  {
        TextContext tC = new TextContext(" a");
        tC.addXpostion(5);
        try {
            Readers.slovo().read(tC).toString();;
            fail();
        } catch (SxException ex) {
            assertEquals( SxExTyp.EMPTY_WORD, ex.getType());
        }
    }

    @Test
    public void testCakalPrazdneSlovo() {
        TextContext tC = new TextContext("  +a");
        try {
            Readers.slovo().read(tC).toString();;
            fail();
        } catch (SxException ex) {
            assertEquals( SxExTyp.EMPTY_WORD, ex.getType());
        }
    }

    @Test
    public void testOdkusniBug()   {
        TextContext tC = new TextContext("   asdf+");
        Readers.slovo().read(tC).toString();;
    }

    @Test
    public void testVratKoniecRiadka()  {
        TextContext tc = new TextContext(" adf 234 \r\n sdf");
        assertEquals("adf 234 ", tc.endOfLine());

        try {
            tc.setPosition(new Position(0, 3));
            tc.endOfLine();
        } catch (SxException ex) {
            assertEquals( SxExTyp.END_OF_FILE, ex.getType());
        }
    }
}
