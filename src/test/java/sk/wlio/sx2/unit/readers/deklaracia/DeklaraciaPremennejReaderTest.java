package sk.wlio.sx2.unit.readers.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.readers.instrukcia.DeklaraciaPremennejReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class DeklaraciaPremennejReaderTest extends AbstractReaderTest {

    @Test
    public void test()  {
        new TestTemplate<DeclarationVariable>(sb, new DeklaraciaPremennejReader()) {
            @Override public void nastavReader() {

                mr.datovyTyp().setVystup( new DatovyTyp(null, "cislo"));
                mr.datovyTyp().setPosun( 5,0 );
                mr.slovo().setPosun(  0,0 ,  1,0);
                mr.slovo().setVystup( new Slovo(null, "a"));
            }
        }.run("cislo a;", "datovyTyp;slovo;");
    }

}

