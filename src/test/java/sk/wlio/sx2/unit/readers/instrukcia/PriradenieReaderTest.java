package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.PriradenieReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.*;

public class PriradenieReaderTest extends AbstractReaderTest {

    @Test
    public void testPriradenie()  {
        new TestTemplate<Assignment>(sb, new PriradenieReader()) {
            @Override
            public void nastavReader() {
                mr.premenna().setPosun( 1, 0 );
                mr.premenna().setVystup( new Premenna( new Slovo( new Pozicia(1,1)) ) );
                mr.opPriradenia().setPosun( 1, 0 );
                mr.vyraz().setPosun( 1, 0 );
                mr.ciarka().setPosun(1,0);
            }
        }.run("c=4;", "premenna;opPriradenia;vyraz;ciarka;");
    }

    @Test
    public void testFail()  {
        try {
            mr.premenna().setPosun( 1, 0 );
            mr.premenna().setVystup( new Premenna( new Slovo( new Pozicia(1,1)) ) );

            new PriradenieReader().citaj( new TextContext("c + 4;"));

            fail();
        } catch (SxException ex) {

        }
    }

}
