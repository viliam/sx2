package sk.wlio.sx2.integra.znak;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class CiarkaReaderTest {

    @Test
     /*
     poznamka nie je
      */
     public void testZakladneCiarka()  {
        TextContext tC= new TextContext("  ;");
        Ciarka ciarka = Readers.ciarka().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Pozicia(2,0), ciarka.getPozicia() );
        assertEquals( "Cislo kontrola ", ";", ciarka.getSymbol() );
        assertEquals( "Posunuty inx", new Pozicia(3,0), tC.getPozicia() );
    }

    @Test
    public void testNecakalCiarku() {
        TextContext tC = new TextContext(" k");
        try {
            Readers.ciarka().citaj( tC);
            fail();
        } catch (SxException e) {
            assertEquals( e.getTyp(), SxExTyp.CAKAL_CIARKU);
        }
    }

}
