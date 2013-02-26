package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.Return;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.VratReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;
import sk.wlio.sx2.unit.readers.mock.TestVyraz;

import static org.testng.AssertJUnit.*;

public class VratReaderTest extends AbstractReaderTest {

    @Test
    public void testVrat()  {
        new TestTemplate<Return>(sb, new VratReader()) {
            @Override
            public void nastavReader() {
                mr.slovo().setPosun( 4,0) ;
                mr.slovo().setVystup( new Slovo(null, "vrat"));
                mr.vyraz().setPosun(1, 0);
                mr.vyraz().setVystup(new TestVyraz());
            }
        }.run("vrat 4;", "slovo;vyraz;");
    }

    @Test
    public void testNeznameSlovo() {
        mr.slovo().setPosun( 4,0 );
        mr.slovo().setVystup( new Slovo(null, "trat"));

        TextContext tC = new TextContext("trat 4;  ");
        try {
            new VratReader().citaj(tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.CAKAL_VRAT, e.getTyp());
        }
    }
}
