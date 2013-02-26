package sk.wlio.sx2.unit.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instrukcia.Priradenie;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.visitors.TypVyrazuVisitor;
import sk.wlio.sx2.DummyFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

@Deprecated
public class TypVyrazuVisitorTest {

    @Test
    public void testVisitVyrazZlozeny() {
        Cislo cislo = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.KRAT);
        Premenna premenna = DummyFactory.createPremenna("ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.CISLO);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        new TypVyrazuVisitor().visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyPorovnanie() {
        Cislo cislo = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.MENSI_ROVNY);
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.CISLO);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        new TypVyrazuVisitor( ).visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyChybny() {
        Cislo cislo = DummyFactory.createCislo(3 );
        Operator op = DummyFactory.createOperator(SymbolEnum.AND);
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        VyrazZlozeny vyraz = new VyrazZlozeny(cislo, op, premenna);

        try {
            new TypVyrazuVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getTyp(), SxExTyp.ZLY_DATOVY_TYP);
        }

        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        vyraz = new VyrazZlozeny(cislo, op, premenna);

        try {
            new TypVyrazuVisitor( ).visit(vyraz);
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
        Priradenie priradenie = DummyFactory.createPriradenie(premenna, cislo);

        new TypVyrazuVisitor( ).visit(priradenie);
    }

    @Test
    public void testPriradenieZle() {
        Premenna premenna = DummyFactory.createPremenna( "ahoj");
        premenna.setVyrazTyp( Enums.VyrazTyp.BOOL);
        Cislo cislo = DummyFactory.createCislo(3);
        Priradenie priradenie = DummyFactory.createPriradenie(premenna, cislo);

        try {
            new TypVyrazuVisitor( ).visit(priradenie);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.ZLY_DATOVY_TYP, e.getTyp());
        }
    }
}
