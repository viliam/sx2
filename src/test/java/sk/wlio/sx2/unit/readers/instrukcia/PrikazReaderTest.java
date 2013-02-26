package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.readers.instrukcia.PrikazReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PrikazReaderTest extends AbstractReaderTest {

    @Test
    public void testPrikaz()  {
        TestTemplate<Command> tt =new TestTemplate<Command>( sb, new PrikazReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 4,0);
                mr.slovo().setVystup( new Slovo( null, "ahoj"));
                mr.parametre().setPosun( 8,0);
            }
        };
        tt.run("   ahoj(3 , p);  ","slovo;parametre;");
        Command command = tt.getVysledok();
        assertNotNull(command);
        assertEquals( "ahoj", command.getNazov().toString() );
    }

}
