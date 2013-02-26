package sk.wlio.sx2.unit;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class TextContextTest {

    @Test
    public void testPosunNaNasledujuciZnak() {
        //test nasledujuceho znaku v tom istom riadku
        TextContext tc = new TextContext("  ko \r\n df  ");
        assertEquals( new Pozicia(2,0), tc.najdiNasledujuciZnak());
        //nasledujuci znak na dalsom riadku
        tc.setPozicia(new Pozicia(4, 0));
        assertEquals(new Pozicia(1, 1), tc.najdiNasledujuciZnak());
        tc.setPozicia(new Pozicia(4, 1));
        assertNull( tc.najdiNasledujuciZnak());
    }

    @Test
    public void testGetNasledujuciZnak()  {
        //test nasledujuceho znaku v tom istom riadku
        TextContext tc = new TextContext("  ko df  ");
        assertEquals( 'k', tc.getNasledujuciZnak());
        assertEquals(new Pozicia(0, 0), tc.getPozicia());

        //chybovy scenar
        tc.setPozicia(new Pozicia(10, 0));
        try {
            tc.getNasledujuciZnak();
            fail();
        }   catch (SxException sx)  {
            assertEquals( SxExTyp.END_OF_FILE, sx.getTyp());
        }
    }

    @Test
    public void testVratPrfxZnak()  {
        TextContext tC = new TextContext("asdasd");
        assertTrue( tC.jePrefixPismeno());
        tC = new TextContext(",");
        assertTrue( tC.jePrefixCiarka());
        tC = new TextContext("32");
        assertTrue( tC.jePrefixCislo());
        tC = new TextContext("=");
        assertTrue( tC.jePrefixOperator());
    }

    @Test
    public void testVratPrfSlova()  {
        TextContext tC = new TextContext("asdasd");
        assertTrue( tC.jePrefixPremenna());
        assertEquals( new Pozicia(0,0), tC.getPozicia());

        tC = new TextContext("cislo");
        assertTrue( tC.jePrefixDatovyTyp());

        tC = new TextContext("34");
        assertTrue( tC.jePrefixCislo());

        tC = new TextContext("asdf()");
        assertTrue( tC.jePrefixPrikaz());

        tC = new TextContext("vrat ");
        assertTrue( tC.jePrefixInstrukcia());

        tC = new TextContext("cislo p( ");
        assertTrue( tC.jePrefixDeklaraciaPrikaz());

        tC = new TextContext("bool er = ");
        assertTrue( tC.jePrefixDeklaraciaPremennej());
    }

    @Test
    public void testPredcitajSlovo() {
        TextContext tC = new TextContext("asdf+");

        assertEquals( "asdf", Readers.slovo().citaj( tC).toString())
        ;
        assertEquals( new Pozicia(4,0), tC.getPozicia());

    }

    @Test
    public void testEndOfFile()  {
        TextContext tC = new TextContext(" a");
        tC.pripocitajXPoziciu(5);
        try {
            Readers.slovo().citaj( tC).toString();;
            fail();
        } catch (SxException ex) {
            assertEquals( SxExTyp.PRAZDNE_SLOVO, ex.getTyp());
        }
    }

    @Test
    public void testCakalPrazdneSlovo() {
        TextContext tC = new TextContext("  +a");
        try {
            Readers.slovo().citaj( tC).toString();;
            fail();
        } catch (SxException ex) {
            assertEquals( SxExTyp.PRAZDNE_SLOVO, ex.getTyp());
        }
    }

    @Test
    public void testOdkusniBug()   {
        TextContext tC = new TextContext("   asdf+");
        Readers.slovo().citaj( tC).toString();;
    }

    @Test
    public void testVratKoniecRiadka()  {
        TextContext tc = new TextContext(" adf 234 \r\n sdf");
        assertEquals("adf 234 ", tc.vratKoniecRiadka());

        try {
            tc.setPozicia(new Pozicia(0, 3));
            tc.vratKoniecRiadka();
        } catch (SxException ex) {
            assertEquals( SxExTyp.END_OF_FILE, ex.getTyp());
        }
    }
}
