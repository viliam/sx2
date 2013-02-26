package sk.wlio.sx2.unit.readers.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.instruction.Block;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.instrukcia.BlokReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class BlokReaderTest extends AbstractReaderTest {

    @Test
    public void testBlok()  {
        TestTemplate<Block> tt = new TestTemplate<Block>(sb, new BlokReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setVystup( new Zatvorka(new Pozicia(0,0), SymbolEnum.ZATVORKA_NORM_OTOVRENA));
                mr.zatvorka().setPosun( 3,0 ,  2,0 );
                mr.instrukcia().setPosun(  7,0 ,  9,0 );
            }
        };
        tt.run( "  { a = 4; cislo b; } ",
                "zatvorka;instruction;instruction;zatvorka;");

        Block block = tt.getVysledok();
        Instrukcia[] inst = block.getInstrukcie();
        assertEquals( inst.length, 2);
    }

}
