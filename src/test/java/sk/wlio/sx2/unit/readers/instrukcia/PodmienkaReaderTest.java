package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instrukcia.Podmienka;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.PodmienkaReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class PodmienkaReaderTest extends AbstractReaderTest {

    @Test
    public void testAk()  {
        new TestTemplate<Podmienka>(sb, new PodmienkaReader()) {
            @Override
            public void nastavReader() {
                mr.instrukciaSlovo().setPosun(  3,0 );
                mr.instrukciaSlovo().setVystup(new InstrukciaSlovo( null, "ak"));
                mr.zatvorka().setPosun(  1,0 ,  1,0 );
                mr.vrzBool().setPosun(5, 0);
                mr.instrukcia().setPosun(9,0);
            }
        }.run("ak ( b>32) nieco();", "instrukciaSlovo;zatvorka;vrzBool;zatvorka;instrukcia;");
    }

    @Test
    public void testNeznameSlovo() {
        mr.instrukciaSlovo().setPosun(4,0);
        mr.instrukciaSlovo().setVystup( new InstrukciaSlovo(null, "trat"));

        TextContext tC = new TextContext("trat 4;  ");
        try {
            new PodmienkaReader().citaj(tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.CAKAL_AK, e.getTyp());
        }
    }
}