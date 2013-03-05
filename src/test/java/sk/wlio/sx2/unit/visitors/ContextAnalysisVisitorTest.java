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

public class ContextAnalysisVisitorTest {

    @Test
    public void testDecVariable() {
        DeclarationVariable decVariable = new DeclarationVariable(
                new DataType( null, "bool"),
                new Word(null, "ahoj"), new Comma(null, null)
        );


        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(decVariable);

        String name = decVariable.getName().toString();
        assertEquals(decVariable.getDataType(),
                visitor.getVariable(name).getDataType());
    }

    @Test
    public void testDecCommand() {
        DataType dataType = new DataType(new Word(null, "integer"));
        dataType.setTyp(Enums.ExpType.INT);
        Word name = new Word(null, "command");
        DeclarationParameter decParameter =new DeclarationParameter(null, null);
        Block body = new Block(new Statement[0], new Bracket(null, null), null);
        DeclarationCommand decCommand =
                new DeclarationCommand(dataType, name, decParameter, body);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(decCommand);

        DeclarationCommand decCommandPamet = visitor.getCommand("command");
        assertNotNull( decCommandPamet);
        assertEquals( Enums.ExpType.INT, decCommandPamet.getDatovyTyp().getExpType() );
        assertEquals( decCommand.getName().getContent(), decCommandPamet.getName().getContent());
    }

    @Test
    public void testDecParameters() {
        DataType dataType = new DataType( new Word(null, "bool"));
        dataType.setTyp( Enums.ExpType.BOOL);
        Word name = new Word(null, "ahoj");
        DeclarationVariable d1 = new DeclarationVariable(dataType, name, new Comma(null, null) );

        List<DeclarationVariable> liDecVariable = new ArrayList<DeclarationVariable>();
        liDecVariable.add(d1);
        DeclarationParameter decParameter= new DeclarationParameter(null, null, null, liDecVariable);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.visit(decParameter);

        DeclarationVariable decVariable= visitor.getVariable("ahoj");
        assertNotNull( decVariable);
        assertEquals(Enums.ExpType.BOOL, decVariable.getDataType().getExpType());
        assertEquals(d1.getName().getContent(), decVariable.getName().getContent());
    }

    @Test
    public void testVisitCommand() {
        Word name = new Word(null, "command");
        Parameters parameters = new Parameters(new Bracket(null, null), null);
        Command command = new Command(name, parameters);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        try {
            visitor.visit(command);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.UNKNOWN_COMMAND, ex.getType());
        }

        DataType dataType = new DataType(new Word(null, "integer"));
        dataType.setTyp( Enums.ExpType.INT);
        DeclarationParameter decParameter =new DeclarationParameter(null, null);
        Block body = new Block(new Statement[] { new Return(new StatementWord( new Position(0,0), null), null, null) }
                             , new Bracket(null, null), null);
        DeclarationCommand decCommand =
                new DeclarationCommand(dataType, name, decParameter, body);

        visitor.addCommand(decCommand);

        visitor.visit(command);
        assertEquals( command.getExpType(), decCommand.getDatovyTyp().getExpType());
    }

    @Test
    public void testVisitVariable() {
        Word name = new Word(null, "ahoj");
        Variable variable = new Variable( name);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        try {
            visitor.visit(variable);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.UNKNOWN_VARIABLE, ex.getType());
        }

        DataType dataType = new DataType(new Word(null, "integer"));
        dataType.setTyp( Enums.ExpType.INT);
        DeclarationVariable decVariable =
                new DeclarationVariable(dataType, name, new Comma(null, null));

        visitor.addVariable(decVariable);

        visitor.visit(variable);
        assertEquals( variable.getExpType(), decVariable.getDataType().getExpType());
    }

   @Test
    public void testVisitExpression() {
        Int anInt = DummyFactory.createInt(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.TIMES);
        Variable variable = DummyFactory.createVariable("ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Expression expr = new Expression(anInt, op, variable);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.addVariable(DummyFactory.createDecVariable("integer", "ahoj"));
        visitor.visit(expr);
    }

    @Test
    public void testVisitExprBracket() {
        Int anInt = DummyFactory.createInt(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.SMALLER_EQUAL);
        Variable variable = DummyFactory.createVariable("ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Expression expr = new Expression(anInt, op, variable);

        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
        visitor.addVariable(DummyFactory.createDecVariable("integer", "ahoj"));
        visitor.visit(expr);
    }

    @Test
    public void testVisitExpressionFail() {
        Int anInt = DummyFactory.createInt(3);
        Operator op = DummyFactory.createOperator(SymbolEnum.AND);
        Variable variable = DummyFactory.createVariable("ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        Expression expr = new Expression(anInt, op, variable);

        try {
            new ContextAnalysisVisitor( ).visit(expr);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getType(), SxExTyp.WRONG_DATA_TYPE);
        }

        variable.setExpType(Enums.ExpType.BOOL);
        expr = new Expression(anInt, op, variable);

        try {
            new ContextAnalysisVisitor( ).visit(expr);
            fail();
        } catch (SxException ex) {
            assertEquals( ex.getType(), SxExTyp.WRONG_DATA_TYPE);
        }
    }

    @Test
    public void testAssignment() {
        Variable variable = DummyFactory.createVariable("ahoj");
        variable.setExpType(Enums.ExpType.INT);
        Int anInt = DummyFactory.createInt(3);
        Assignment assignment = DummyFactory.createAssignment(variable, anInt);

        new ContextAnalysisVisitor( ).visit(assignment);
    }

    @Test
    public void testAssignmentFail() {
        Variable variable = DummyFactory.createVariable("ahoj");
        variable.setExpType(Enums.ExpType.BOOL);
        Int anInt = DummyFactory.createInt(3);
        Assignment assignment = DummyFactory.createAssignment(variable, anInt);

        try {
            new ContextAnalysisVisitor( ).visit(assignment);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.WRONG_DATA_TYPE, e.getType());
        }
    }

}
