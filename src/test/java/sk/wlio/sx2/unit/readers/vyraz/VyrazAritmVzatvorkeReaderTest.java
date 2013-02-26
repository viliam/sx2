package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazAritmVzatvorkeReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;


public class VyrazAritmVzatvorkeReaderTest extends AbstractReaderTest {

    @Test
    public void testAritmVzatvorke()  {
        new TestTemplate<IVyraz>(sb, new VyrazAritmVzatvorkeReader()) {
            @Override
            public void nastavReader() {
                mr.zatvorka().setPosun( 1, 0 ,  1, 0);
                mr.vrzAritm().setPosun( 4, 0 );
            }
        }.run("(ahoj)", "zatvorka;vrzAritm;zatvorka;");
    }

}
