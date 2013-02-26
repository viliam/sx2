package sk.wlio.sx2.unit.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.DeklaracieVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertEquals;

@Deprecated
public class DeklaracieVisitorTest {

    @Test
    public void testDekPremenna() {
        DeklaraciaPremennej dekPremennej = new DeklaraciaPremennej(
                new DatovyTyp( null, "bool"),
                new Slovo(null, "ahoj"), new Ciarka(null, null)
        );


        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        visitor.visit(dekPremennej);

        String nazov = dekPremennej.getNazov().toString();
        assertEquals(dekPremennej.getDatovyTyp(),
                visitor.getPremenna(nazov).getDatovyTyp());
    }

    @Test
    public void testVisitDekPrikazu() {
        DatovyTyp datovyTyp = new DatovyTyp(new Slovo(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        Slovo nazov = new Slovo(null, "prikaz");
        DeklaraciaParameter dekParameter =new DeklaraciaParameter(null, null);
        Blok telo = new Blok(new Instrukcia[0], new Zatvorka(null, null), null);
        DeklaraciaPrikaz dekPrikaz =
                new DeklaraciaPrikaz(datovyTyp, nazov, dekParameter, telo);

        DeklaracieVisitor visitor = new DeklaracieVisitor();
        visitor.visit(dekPrikaz);

        DeklaraciaPrikaz dekPrikazPamet = visitor.getPrikaz("prikaz");
        assertNotNull( dekPrikazPamet);
        assertEquals( Enums.VyrazTyp.CISLO, dekPrikazPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( dekPrikaz.getNazov().getObsah(), dekPrikazPamet.getNazov().getObsah());
    }

    @Test
    public void testDeklaraciaParameter() {
        DatovyTyp datovyTyp = new DatovyTyp( new Slovo(null, "bool"));
        datovyTyp.setTyp( Enums.VyrazTyp.BOOL);
        Slovo nazov = new Slovo(null, "ahoj");
        DeklaraciaPremennej d1 = new DeklaraciaPremennej(datovyTyp, nazov, new Ciarka(null, null) );

        List<DeklaraciaPremennej> liDekPremennaj = new ArrayList<DeklaraciaPremennej>();
        liDekPremennaj.add( d1);
        DeklaraciaParameter dekParameter = new DeklaraciaParameter(null, null, null, liDekPremennaj);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        visitor.visit(dekParameter);

        DeklaraciaPremennej dekPremennejPamet = visitor.getPremenna("ahoj");
        assertNotNull( dekPremennejPamet);
        assertEquals( Enums.VyrazTyp.BOOL, dekPremennejPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( d1.getNazov().getObsah(), dekPremennejPamet.getNazov().getObsah());
    }

    @Test
    public void testVisitPrikaz() {
        Slovo nazov = new Slovo(null, "prikaz");
        Parametre parametre = new Parametre(new Zatvorka(null, null), null);
        Prikaz prikaz = new Prikaz(nazov, parametre);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        try {
            visitor.visit( prikaz);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMY_PRIKAZ, ex.getTyp());
        }

        DatovyTyp datovyTyp = new DatovyTyp(new Slovo(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeklaraciaParameter dekParameter =new DeklaraciaParameter(null, null);
        Blok telo = new Blok(new Instrukcia[] { new Vrat(new InstrukciaSlovo( new Pozicia(0,0), null), null, null) }
                             , new Zatvorka(null, null), null);
        DeklaraciaPrikaz dekPrikaz =
                new DeklaraciaPrikaz(datovyTyp, nazov, dekParameter, telo);

        visitor.pridajPrikaz(dekPrikaz);

        visitor.visit( prikaz);
        assertEquals( prikaz.getVyrazTyp(), dekPrikaz.getDatovyTyp().getVyrazTyp());
    }

    @Test
    public void testVisitPremenna() {
        Slovo nazov = new Slovo(null, "ahoj");
        Premenna premenna = new Premenna( nazov);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        try {
            visitor.visit( premenna);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMA_PREMENNA, ex.getTyp());
        }

        DatovyTyp datovyTyp = new DatovyTyp(new Slovo(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeklaraciaPremennej dekPremennej =
                new DeklaraciaPremennej(datovyTyp, nazov, new Ciarka(null, null));

        visitor.pridajPremennu( dekPremennej);

        visitor.visit( premenna);
        assertEquals( premenna.getVyrazTyp(), dekPremennej.getDatovyTyp().getVyrazTyp());
    }


}
