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
import sk.wlio.sx2.beans.expression.Expression;
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.statement.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.ContextAnalysisVisitor;
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


        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(dekPremennej);

        String nazov = dekPremennej.getName().toString();
        assertEquals(dekPremennej.getDatovyTyp(),
                visitor.getVariable(nazov).getDatovyTyp());
    }

    @Test
    public void testVisitDekPrikazu() {
        DataType datovyTyp = new DataType(new Word(null, "integer"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        Word nazov = new Word(null, "command");
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Statement[0], new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(dekPrikaz);

        DeclarationCommand dekPrikazPamet = visitor.getCommand("command");
        assertNotNull( dekPrikazPamet);
        assertEquals( Enums.ExpType.INT, dekPrikazPamet.getDatovyTyp().getExpType() );
        assertEquals( dekPrikaz.getName().getContent(), dekPrikazPamet.getName().getContent());
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

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(dekParameter);

        DeclarationVariable dekPremennejPamet = visitor.getVariable("ahoj");
        assertNotNull( dekPremennejPamet);
        assertEquals( Enums.ExpType.BOOL, dekPremennejPamet.getDatovyTyp().getExpType() );
        assertEquals( d1.getName().getContent(), dekPremennejPamet.getName().getContent());
    }

    @Test
    public void testVisitPrikaz() {
        Word nazov = new Word(null, "command");
        Parameters parameters = new Parameters(new Bracket(null, null), null);
        Command command = new Command(nazov, parameters);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        try {
            visitor.visit(command);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.UNKNOWN_COMMAND, ex.getType());
        }

        DataType datovyTyp = new DataType(new Word(null, "integer"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Statement[] { new Return(new StatementWord( new Position(0,0), null), null, null) }
                             , new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        visitor.addCommand(dekPrikaz);

        visitor.visit(command);
        assertEquals( command.getExpType(), dekPrikaz.getDatovyTyp().getExpType());
    }

    @Test
    public void testVisitPremenna() {
        Word nazov = new Word(null, "ahoj");
        Variable variable = new Variable( nazov);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        try {
            visitor.visit(variable);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.UNKNOWN_VARIABLE, ex.getType());
        }

        DataType datovyTyp = new DataType(new Word(null, "integer"));
        datovyTyp.setTyp( Enums.ExpType.INT);
        DeclarationVariable dekPremennej =
                new DeclarationVariable(datovyTyp, nazov, new Comma(null, null));

        visitor.addVariable(dekPremennej);

        visitor.visit(variable);
        assertEquals( variable.getExpType(), dekPremennej.getDatovyTyp().getExpType());
    }

   @Test
    public void testVisitVyrazZlozeny() {
        Int anInt = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.TIMES);
        Variable variable = DummyFactory.createPremenna("ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Expression vyraz = new Expression(anInt, op, variable);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.addVariable(DummyFactory.createDeklaraciaPremennej("integer", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyPorovnanie() {
        Int anInt = DummyFactory.createCislo(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.SMALLER_EQUAL);
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Expression vyraz = new Expression(anInt, op, variable);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.addVariable(DummyFactory.createDeklaraciaPremennej("integer", "ahoj"));
        visitor.visit(vyraz);
    }

    @Test
    public void testVisitVyrazZlozenyChybny() {
        Int anInt = DummyFactory.createCislo(3 );
        Operator op = DummyFactory.createOperator(SymbolEnum.AND);
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        Expression vyraz = new Expression(anInt, op, variable);

        try {
            new ContextAnalysisVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getType(), SxExTyp.WRONG_DATA_TYPE);
        }

        variable.setExpType(Enums.ExpType.BOOL);
        vyraz = new Expression(anInt, op, variable);

        try {
            new ContextAnalysisVisitor( ).visit(vyraz);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getType(), SxExTyp.WRONG_DATA_TYPE);
        }
    }

    @Test
    public void testPriradenie() {
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Int anInt = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(variable, anInt);

        new ContextAnalysisVisitor( ).visit(assignment);
    }

    @Test
    public void testPriradenieZle() {
        Variable variable = DummyFactory.createPremenna( "ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        Int anInt = DummyFactory.createCislo(3);
        Assignment assignment = DummyFactory.createPriradenie(variable, anInt);

        try {
            new ContextAnalysisVisitor( ).visit(assignment);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.WRONG_DATA_TYPE, e.getType());
        }
    }

}
