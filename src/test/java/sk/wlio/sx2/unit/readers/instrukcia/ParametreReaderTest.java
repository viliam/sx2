package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.instruction.Parameters;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.instrukcia.ParametreReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

public class ParametreReaderTest extends AbstractReaderTest {

    @Test
    public void testPrazdny()  {
        new TestTemplate<Parameters>(sb, new ParametreReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0, 1, 0);
                mr.zatvorka().setVystup(new Zatvorka(null, SymbolEnum.ZATVORKA_NORM_OTOVRENA));
            }
        }.run(" ()", "zatvorka;zatvorka;");
    }

    @Test
    public void testJedenParameter()  {
        new TestTemplate<Parameters>(sb, new ParametreReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0, 1, 0);
                mr.zatvorka().setVystup(new Zatvorka(null, null));
                mr.vyraz().setPosun(2, 0);
            }
        }.run(" (ff)", "zatvorka;vyraz;zatvorka;");
    }

    @Test
    public void testViacParametrov()  {
        new TestTemplate<Parameters>(sb, new ParametreReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0,  1, 0);
                mr.zatvorka().setVystup( new Zatvorka(null, null));
                mr.vyraz().setPosun( 2, 0, 3, 0);
                mr.ciarka().setPosun( 1, 0);
            }
        }.run(" (ff,1+3) ", "zatvorka;vyraz;ciarka;vyraz;zatvorka;");
    }

}
