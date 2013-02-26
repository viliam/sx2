package sk.wlio.sx2.unit.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.KontextovaKontrolaVisitor;
import sk.wlio.sx2.DummyFactory;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class KontextovaKontrolaVisitorTest {
    @Test
    public void testDekPremenna() {
        DeclarationVariable dekPremennej = new DeclarationVariable(
                new DatovyTyp( null, "bool"),
                new Slovo(null, "ahoj"), new Ciarka(null, null)
        );


        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
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
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Instrukcia[0], new Zatvorka(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.visit(dekPrikaz);

        DeclarationCommand dekPrikazPamet = visitor.getPrikaz("prikaz");
        assertNotNull( dekPrikazPamet);
        assertEquals( Enums.VyrazTyp.CISLO, dekPrikazPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( dekPrikaz.getNazov().getObsah(), dekPrikazPamet.getNazov().getObsah());
    }

    @Test
    public void testDeklaraciaParameter() {
        DatovyTyp datovyTyp = new DatovyTyp( new Slovo(null, "bool"));
        datovyTyp.setTyp( Enums.VyrazTyp.BOOL);
        Slovo nazov = new Slovo(null, "ahoj");
        DeclarationVariable d1 = new DeclarationVariable(datovyTyp, nazov, new Ciarka(null, null) );

        List<DeclarationVariable> liDekPremennaj = new ArrayList<DeclarationVariable>();
        liDekPremennaj.add( d1);
        DeclarationParameter dekParameter = new DeclarationParameter(null, null, null, liDekPremennaj);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.visit(dekParameter);

        DeclarationVariable dekPremennejPamet = visitor.getPremenna("ahoj");
        assertNotNull( dekPremennejPamet);
        assertEquals( Enums.VyrazTyp.BOOL, dekPremennejPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( d1.getNazov().getObsah(), dekPremennejPamet.getNazov().getObsah());
    }

    @Test
    public void testVisitPrikaz() {
        Slovo nazov = new Slovo(null, "prikaz");
        Parameters parameters = new Parameters(new Zatvorka(null, null), null);
        Command command = new Command(nazov, parameters);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        try {
            visitor.visit(command);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMY_PRIKAZ, ex.getTyp());
        }

        DatovyTyp datovyTyp = new DatovyTyp(new Slovo(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Instrukcia[] { new Return(new InstrukciaSlovo( new Pozicia(0,0), null), null, null) }
                             , new Zatvorka(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        visitor.pridajPrikaz(dekPrikaz);

        visitor.visit(command);
        assertEquals( command.getVyrazTyp(), dekPrikaz.getDatovyTyp().getVyrazTyp());
    }

    @Test
    public void testVisitPremenna() {
        Slovo nazov = new Slovo(null, "ahoj");
        Premenna premenna = new Premenna( nazov);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        try {
            visitor.visit( premenna);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMA_PREMENNA, ex.getTyp());
        }

        DatovyTyp datovyTyp = new DatovyTyp(new Slovo(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeclarationVariable dekPremennej =
                new DeclarationVariable(datovyTyp, nazov, new Ciarka(null, null));

        visitor.pridajPremennu( dekPremennej);

        visitor.visit( premenna);
        assertEquals( premenna.getVyrazTyp(), dekPremennej.getDatovyTyp().getVyrazTyp());
    }

   @Test
    public void testVisitVyrazZlozeny() {
        Cislo cislo = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.KRAT);
        Premenna premenna = DummyFactory.createPremenna("ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.CISLO);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.pridajPremennu( DummyFactory.createDeklaraciaPremennej("cislo", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyPorovnanie() {
        Cislo cislo = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.MENSI_ROVNY);
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.CISLO);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.pridajPremennu( DummyFactory.createDeklaraciaPremennej("cislo", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyChybny() {
        Cislo cislo = DummyFactory.createCislo(3 );
        Operator op = DummyFactory.createOperator(SymbolEnum.AND);
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        try {
            new KontextovaKontrolaVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getTyp(), SxExTyp.ZLY_DATOVY_TYP);
        }

        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        vyraz = new VyrazZlozeny(cislo, op, premenna);

        try {
            new KontextovaKontrolaVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getTyp(), SxExTyp.ZLY_DATOVY_TYP);
        }
    }

    @Test
    public void testPriradenie() {
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.CISLO);
        Cislo cislo = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(premenna, cislo);

        new KontextovaKontrolaVisitor( ).visit(assignment);
    }

    @Test
    public void testPriradenieZle() {
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        Cislo cislo = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(premenna, cislo);

        try {
            new KontextovaKontrolaVisitor( ).visit(assignment);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.ZLY_DATOVY_TYP, e.getTyp());
        }
    }

}
