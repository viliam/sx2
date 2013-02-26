package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.readers.instrukcia.PrikazReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PrikazReaderTest extends AbstractReaderTest {

    @Test
    public void testPrikaz()  {
        TestTemplate<Prikaz> tt =new TestTemplate<Prikaz>( sb, new PrikazReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 4,0);
                mr.slovo().setVystup( new Slovo( null, "ahoj"));
                mr.parametre().setPosun( 8,0);
            }
        };
        tt.run("   ahoj(3 , p);  ","slovo;parametre;");
        Prikaz prikaz = tt.getVysledok();
        assertNotNull(prikaz);
        assertEquals( "ahoj", prikaz.getNazov().toString() );
    }

}
