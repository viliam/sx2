package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazBoolVzatvorkeReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class VyrazBoolVzatvorkeReaderTest extends AbstractReaderTest {

    @Test
    public void testVyraz()  {
        new TestTemplate<IVyraz>(sb, new VyrazBoolVzatvorkeReader() ) {
            @Override
            public void nastavReader() {
                mr.zatvorka().setPosun( 1, 0 ,  1, 0 );
                 mr.vrzBool().setPosun( 4, 0 );
            }
        }.run("(ahoj)", "zatvorka;vrzBool;zatvorka;");
    }
}
