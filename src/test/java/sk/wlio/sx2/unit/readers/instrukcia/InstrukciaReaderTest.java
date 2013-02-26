package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.readers.instrukcia.InstrukciaReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

public class InstrukciaReaderTest extends AbstractReaderTest {

    @Test
    public void testPriradenie()  {
        new TestTemplate<Instrukcia>(sb, new InstrukciaReader()) {
            @Override
            public void nastavReader() {
                mr.slovo().setPosun( 0,0,  0,0, 2,0);
                mr.slovo().setVystup( new Slovo(null, "v"), new Slovo(null, "v"), new Slovo(null, "v"));
                mr.priradenie().setPosun( 0, 5);
            }
        }.run(" v = 3;", "slovo;slovo;priradenie;");
    }

    @Test
    public void testDekPremennej()  {
        new TestTemplate<Instrukcia>(sb, new InstrukciaReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun(  5,0);
                mr.slovo().setVystup( new Slovo(null, "cislo"));
                mr.dekPremennej().setPosun( 0, 0);
            }
        }.run(" cislo v = 3;", "slovo;dekPremennej;");
    }

}
