package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.readers.PremennaReader;
import sk.wlio.sx2.rozhrania.ISlovo;

import static org.testng.AssertJUnit.*;

public class PremennaReaderTest {
    
    @Test
     /*
     poznamka nie je
      */
    public void testZakladnePremenna()  {
        Premenna premenna = citajPremenna(" c= 4;");
        assertEquals(  "c", premenna.getNazov().toString());
    }
    
    private Premenna citajPremenna(String tento)  {
        TextContext tC = new TextContext(tento);
        PremennaReader vPremennaReader = new PremennaReader();
        ISlovo slovo = vPremennaReader.citaj(tC);

        assertNotNull(slovo);
        assertTrue( "Slovo nie je instancia VyrazArtim. Slovo.class = " + slovo.getClass().getName()
                , slovo instanceof Premenna);
        
        return (Premenna) slovo;
    }
    
}
