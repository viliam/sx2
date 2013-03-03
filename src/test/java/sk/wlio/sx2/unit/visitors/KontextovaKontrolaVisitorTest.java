/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.wlio.sx2.unit.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.Statement;
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
                new DataType( null, "bool"),
                new Word(null, "ahoj"), new Comma(null, null)
        );


        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.visit(dekPremennej);

        String nazov = dekPremennej.getNazov().toString();
        assertEquals(dekPremennej.getDatovyTyp(),
                visitor.getPremenna(nazov).getDatovyTyp());
    }

    @Test
    public void testVisitDekPrikazu() {
        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        Word nazov = new Word(null, "prikaz");
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Statement[0], new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.visit(dekPrikaz);

        DeclarationCommand dekPrikazPamet = visitor.getPrikaz("prikaz");
        assertNotNull( dekPrikazPamet);
        assertEquals( Enums.ExpType.INT, dekPrikazPamet.getDatovyTyp().getExpType() );
        assertEquals( dekPrikaz.getNazov().getObsah(), dekPrikazPamet.getNazov().getObsah());
    }

    @Test
    public void testDeklaraciaParameter() {
        DataType datovyTyp = new DataType( new Word(null, "bool"));
        datovyTyp.setTyp( Enums.ExpType.BOOL);
        Word nazov = new Word(null, "ahoj");
        DeclarationVariable d1 = new DeclarationVariable(datovyTyp, nazov, new Comma(null, null) );

        List<DeclarationVariable> liDekPremennaj = new ArrayList<DeclarationVariable>();
        liDekPremennaj.add( d1);
        DeclarationParameter dekParameter = new DeclarationParameter(null, null, null, liDekPremennaj);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.visit(dekParameter);

        DeclarationVariable dekPremennejPamet = visitor.getPremenna("ahoj");
        assertNotNull( dekPremennejPamet);
        assertEquals( Enums.ExpType.BOOL, dekPremennejPamet.getDatovyTyp().getExpType() );
        assertEquals( d1.getNazov().getObsah(), dekPremennejPamet.getNazov().getObsah());
    }

    @Test
    public void testVisitPrikaz() {
        Word nazov = new Word(null, "prikaz");
        Parameters parameters = new Parameters(new Bracket(null, null), null);
        Command command = new Command(nazov, parameters);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        try {
            visitor.visit(command);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMY_PRIKAZ, ex.getTyp());
        }

        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Statement[] { new Return(new InstructionWord( new Position(0,0), null), null, null) }
                             , new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        visitor.pridajPrikaz(dekPrikaz);

        visitor.visit(command);
        assertEquals( command.getExpType(), dekPrikaz.getDatovyTyp().getExpType());
    }

    @Test
    public void testVisitPremenna() {
        Word nazov = new Word(null, "ahoj");
        Variable variable = new Variable( nazov);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        try {
            visitor.visit(variable);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMA_PREMENNA, ex.getTyp());
        }

        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        DeclarationVariable dekPremennej =
                new DeclarationVariable(datovyTyp, nazov, new Comma(null, null));

        visitor.pridajPremennu( dekPremennej);

        visitor.visit(variable);
        assertEquals( variable.getExpType(), dekPremennej.getDatovyTyp().getExpType());
    }

   @Test
    public void testVisitVyrazZlozeny() {
        Int anInt = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.TIMES);
        Variable variable = DummyFactory.createPremenna("ahoj");
        variable.setExpType(Enums.ExpType.INT);
        VyrazZlozeny vyraz = new VyrazZlozeny(anInt, op, variable);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.pridajPremennu( DummyFactory.createDeklaraciaPremennej("cislo", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyPorovnanie() {
        Int anInt = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.SMALLER_EQUAL);
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.INT);
        VyrazZlozeny vyraz = new VyrazZlozeny(anInt, op, variable);

        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
        visitor.pridajPremennu( DummyFactory.createDeklaraciaPremennej("cislo", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyChybny() {
        Int anInt = DummyFactory.createCislo(3 );
        Operator op = DummyFactory.createOperator(SymbolEnum.AND);
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        VyrazZlozeny vyraz = new VyrazZlozeny(anInt, op, variable);

        try {
            new KontextovaKontrolaVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getTyp(), SxExTyp.ZLY_DATOVY_TYP);
        }

        variable.setExpType(Enums.ExpType.BOOL);
        vyraz = new VyrazZlozeny(anInt, op, variable);

        try {
            new KontextovaKontrolaVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getTyp(), SxExTyp.ZLY_DATOVY_TYP);
        }
    }

    @Test
    public void testPriradenie() {
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Int anInt = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(variable, anInt);

        new KontextovaKontrolaVisitor( ).visit(assignment);
    }

    @Test
    public void testPriradenieZle() {
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        Int anInt = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(variable, anInt);

        try {
            new KontextovaKontrolaVisitor( ).visit(assignment);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.ZLY_DATOVY_TYP, e.getTyp());
        }
    }

}
