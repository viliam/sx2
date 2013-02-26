package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class CisloReaderTest {

    @Test
    public void testReadCislo()  {
        final int testCislo = 123;
        TextContext text = new TextContext("  " + testCislo);
        Cislo cislo= Readers.cislo().citaj( text);
        assertEquals( "Pozicia kontrola ", new Pozicia(2,0), cislo.getPozicia() );
        assertEquals( "Cislo kontrola ", testCislo, cislo.getCislo().intValue() );
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
