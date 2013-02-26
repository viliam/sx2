package sk.wlio.sx2.unit.readers.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.DeclarationCommand;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class DeklaraciaTriedaReaderTest extends AbstractReaderTest {

    @Test
    public void testBasic()  {
        TestTemplate<Program> tt =
            new TestTemplate<Program>(sb, new ProgramReader()) {
                @Override
                public void nastavReader() {

                    mr.slovo().setPosun(5,0, 4,0,
                                        5,0, 4,0,
                                        6,0, 5,0);
                    mr.slovo().setVystup(new Slovo(null, "bool"), new Slovo(null, "ahoj"),
                            new Slovo(null, "bool"), new Slovo(null, "ahoj"),
                            new Slovo(null, "cislo"), new Slovo(null, "ahoj"));
                    mr.dekPrikaz().setVystup(new DeclarationCommand(new DataType(new Slovo( new Pozicia(3,3))),
                                                                  new Slovo(null, "ahoj"), null, null));
                    mr.dekPrikaz().setPosun( 25, 0 );
                    mr.dekPremennej().setVystup(new DeclarationVariable(
                            new DataType(new Slovo( new Pozicia(3,3))), new Slovo(null, "ahoj"), new Ciarka(null, SymbolEnum.BODKO_CIARKA)) );
                    mr.dekPremennej().setPosun( 12, 0 );
                }
            };
        tt.run(" bool ahoj() { vrat 3; } " +
               " cislo ahoj;", "slovo;slovo;slovo;slovo;dekPrikaz;slovo;slovo;dekPremennej;");
    }

}
