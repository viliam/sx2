package sk.wlio.sx2.integra.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.instrukcia.Priradenie;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.instrukcia.DeklaraciaPremennejReader;
import sk.wlio.sx2.rozhrania.TextReader;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class DeklaraciaPremennejReaderTest {
    @Test
    public void testCislo()  {
        DeklaraciaPremennej dekPremena = citajDekPremennej("  cislo a; ");
        assertNotNull( dekPremena);
        assertEquals( "a" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.CISLO , dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Pozicia(2,0) , dekPremena.getPozicia());
    }

    @Test
    public void testBool()  {
        DeklaraciaPremennej dekPremena = citajDekPremennej("  bool a; ");
        assertNotNull( dekPremena);
        assertEquals( "a" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.BOOL , dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Pozicia(2,0) , dekPremena.getPozicia());
    }

    @Test
    public void testPriradenie()  {
        DeklaraciaPremennej dekPremena = citajDekPremennej(" cislo v = 3;");
        assertNotNull( dekPremena);
        assertEquals( "v" , dekPremena.getNazov().toString());
        assertEquals(Enums.VyrazTyp.CISLO, dekPremena.getDatovyTyp().getTyp() );
        assertEquals(new Pozicia(1,0) , dekPremena.getPozicia());

        Priradenie p = dekPremena.getPriradenie();
        assertNotNull( p);
    }

    @Test
    public void testZlyDatovyTyp() {
        TextContext text = new TextContext("  nic a; ");
        TextReader<DeklaraciaPremennej> dpReader = new DeklaraciaPremennejReader();
        try {
            dpReader.citaj( text);
            fail();
        } catch (SxException e) {

        }
    }

//    @Test
//    public void testZlyNazovPremenej() {
//        TextContext text = new TextContext("  bool ahoj(); ");
//        TextReader<DeklaraciaPremennej> dpReader = new DeklaraciaPremennejReader();
//        try {
//            dpReader.citaj( text);
//            fail();
//        } catch (SxException e) {
//
//        }
//    }

    private DeklaraciaPremennej citajDekPremennej(String ts)  {
        TextContext text = new TextContext(ts);
        TextReader<DeklaraciaPremennej> dpReader = new DeklaraciaPremennejReader();
        DeklaraciaPremennej dekPremena= dpReader.citaj( text);
        return dekPremena;
    }

}
