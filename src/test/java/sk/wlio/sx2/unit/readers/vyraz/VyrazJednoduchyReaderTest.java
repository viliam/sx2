package sk.wlio.sx2.unit.readers.vyraz;


import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.vyraz.VyrazJednoduchyReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class VyrazJednoduchyReaderTest extends AbstractReaderTest {

    @Test
    public void testCislo()  {
        new TestTemplate<IVyraz>(sb, new VyrazJednoduchyReader()) {
            @Override
            public void nastavReader() {
                mr.cislo().setPosun( 1,0 );
            }
        }.run("4", "cislo;");
    }

    @Test
    public void testPremenna()  {
        new TestTemplate<IVyraz>(sb, new VyrazJednoduchyReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 0,0 );
                mr.slovo().setVystup( new Slovo(null, "asdfds"));
                mr.premenna().setPosun(  6,0) ;
            }
        }.run( "asdfds", "slovo;premenna;");
    }

    @Test
    public void testPrikaz()  {
        new TestTemplate<IVyraz>(sb, new VyrazJednoduchyReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 6,0 ,  6,0 ,
                                     6,0 ,  0,0 );
                Slovo slovo = new Slovo(null, "asdfds");
                mr.slovo().setVystup( slovo, slovo, slovo);
                mr.prikaz().setPosun(  6,0 );
            }
        }.run( "asdfds(aa", "slovo;slovo;slovo;prikaz;");
    }

    @Test
    public void testUnknowExpresion() {
        TextContext tC = new TextContext( "+asdfds");
        try {
            new VyrazJednoduchyReader().citaj( tC);
            fail();
        } catch (SxException e) {
            assertEquals( SxExTyp.UNEXPECTED_PREFIX, e.getTyp());
        }

    }
}
