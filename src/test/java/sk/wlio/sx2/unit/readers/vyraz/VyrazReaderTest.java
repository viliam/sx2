package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.readers.vyraz.VyrazReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;
import sk.wlio.sx2.unit.readers.mock.TestVyraz;


public class VyrazReaderTest extends AbstractReaderTest {

    @Test
    public void testVzatvorke()  {
        new TestTemplate<IVyraz>(sb, new VyrazReader()) {
            @Override public void nastavReader() {
                mr.vrzVzatvorke().setPosun(9,0);
            }
        }.run("( nieco ) , ", "vrzZatvorka;");
    }

    @Test
    public void testJednoduchyVyraz()  {
        new TestTemplate<IVyraz>(sb, new VyrazReader()) {
            @Override
            public  void nastavReader() {
                mr.vrzJednduchy().setPosun( 5,0);
            }

        }.run("nazov ", "vrzJednoduchy;");
    }


    @Test
    public void testZlozeny()  {
        new TestTemplate<IVyraz>(sb, new VyrazReader()) {
            @Override public void nastavReader() {
                mr.vrzJednduchy().setPosun( 3,0);
                mr.vrzJednduchy().setVystup(new TestVyraz());
                mr.opPorovnanie().setPosun(2,0);
                mr.vyraz().setPosun(4,0);
            }

        }.run("ano > nie", "vrzJednoduchy;opPorovnania;vyraz;");
    }



}
