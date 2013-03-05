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

package sk.wlio.sx2.visitors;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.expression.BracketExpression;
import sk.wlio.sx2.beans.expression.Expression;
import sk.wlio.sx2.beans.statement.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.DataValue;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextAnalysisVisitor implements IVisitor {

    protected final Map<String, DeclarationVariable> mapDecVariable = new HashMap<String, DeclarationVariable>();
    protected final Map<String, DeclarationCommand> mapDecCommand = new HashMap<String, DeclarationCommand>();

    public ContextAnalysisVisitor() {    }

    public ContextAnalysisVisitor(Map<String, DeclarationVariable> mapDecVariable,
                                  Map<String, DeclarationCommand> mapDecCommand) {
        this.mapDecVariable.putAll(mapDecVariable);
        this.mapDecCommand.putAll(mapDecCommand);
    }

    public void addVariable(DeclarationVariable decVariable) {
       mapDecVariable.put(decVariable.getName().getContent(), decVariable);
    }

    public void addCommand(DeclarationCommand decCommand) {
       mapDecCommand.put(decCommand.getName().getContent(), decCommand);
    }

    public DeclarationVariable getVariable(String name) {
        return mapDecVariable.get(name);
    }

    public DeclarationCommand getCommand(String name) {
        return mapDecCommand.get( name);
    }

    public void visit(Program decProgram) {
        for (DeclarationVariable decVariable : decProgram.getMapVariable().values() ) {
            if ( mapDecVariable.containsKey(decVariable.getName().toString()))
                throw SxException.create(SxExTyp.DUPLICATED_VARIABLE, decVariable.getPosition());
            addVariable(decVariable);
        }

        for (DeclarationCommand decCommand : decProgram.getMapCommand().values() )  {
            if ( mapDecCommand.containsKey(decCommand.getName().toString()))
                throw SxException.create(SxExTyp.DUPLICATED_COMMAND, decCommand.getPosition());
            addCommand(decCommand);
        }

        for (DeclarationVariable dekPremennej : decProgram.getMapVariable().values() )
            dekPremennej.visit(this);

        for (DeclarationCommand dekPrikaz : decProgram.getMapCommand().values() )
            dekPrikaz.visit(this);

    }

    public void visit(DeclarationVariable decVariable) {
        this.addVariable(decVariable);

        Assignment assignment = decVariable.getAssignment();
        if (assignment != null)
            assignment.visit( this);
    }

    public void visit(DeclarationCommand decCommand) {
        //create new visitor (like clone of the main visitor) for local variables
        addCommand(decCommand);
        ContextAnalysisVisitor dekVisitor = new ContextAnalysisVisitor(mapDecVariable, mapDecCommand);

        decCommand.getDekParam().visit( dekVisitor);
        decCommand.getTelo().visit( dekVisitor);
    }

    public void visit(DeclarationParameter decParameter) {
        for (DeclarationVariable decVariable: decParameter.getLiDekPremennej()) {
            decVariable.visit(this);
        }
    }

    public void visit(Block block) {
        ContextAnalysisVisitor decVisitor = new ContextAnalysisVisitor(mapDecVariable, mapDecCommand);

        for (Statement statement : block.getInstrukcie())
            statement.visit(decVisitor);
    }

    public void visit(Command command) {
        //check if commad exists and add it to map
        DeclarationCommand decCommand = mapDecCommand.get( command.getNazov().getContent());
        if (decCommand == null)
            throw SxException.create(SxExTyp.UNKNOWN_COMMAND, command.getPosition());
        //set command type on the basis of declaration
        command.setExpType(decCommand.getDatovyTyp().getExpType());

        //check parameters count
        List<DeclarationVariable> liDecVariable = decCommand.getDekParam().getLiDekPremennej();
        List<IExpression> liParameter = command.getParameters().getParametre();
        if ( liDecVariable.size() != liParameter.size())
            throw SxException.create(SxExTyp.WRONG_PARAMETER_COUNT, command.getPosition());

        //check parameters type
        for (int i=0; i<liDecVariable.size(); i++)
            if ( liDecVariable.get(i).getDatovyTyp().getExpType() != liParameter.get(i).getExpType())
                throw SxException.create( SxExTyp.WRONG_DATA_TYPE, liParameter.get(i).getPosition());

        command.getParameters().visit(this);
    }

    public void visit(Parameters parameters) {
        for (IExpression expression : parameters.getParametre() )
            expression.visit( this);
    }

    public void visit(Variable variable) {
        //check if commad exists and add it to map
        DeclarationVariable decVariable = mapDecVariable.get( variable.getName().getContent());
        if (decVariable == null)
            throw SxException.create(SxExTyp.UNKNOWN_VARIABLE, variable.getPosition());
        //set command type on the basis of declaration
        variable.setExpType(decVariable.getDatovyTyp().getExpType());
    }

    public void visit(Assignment assignment) {
        IExpression expression = assignment.getExpression();
        expression.visit(this);

        Variable variable = assignment.getVariable();
        if (variable.getExpType() != expression.getExpType() )
            throw SxException.create(SxExTyp.WRONG_DATA_TYPE, expression.getPosition());
    }

    public void visit(Expression expression) {
        Enums.ExpType expectedType = expression.getExpType();
        if ( Enums.ExpType.COMPARISON.equals(expectedType))
            expectedType = Enums.ExpType.INT;

        IExpression v1 = expression.getV1();
        if ( !Enums.ExpType.UNKNOWN.equals(v1.getExpType()) && !v1.getExpType().equals( expectedType))
            throw SxException.create(SxExTyp.WRONG_DATA_TYPE, v1.getPosition());

        IExpression v2 = expression.getV2();
        if ( !Enums.ExpType.UNKNOWN.equals(v2.getExpType()) && !v2.getExpType().equals( expectedType))
            throw SxException.create(SxExTyp.WRONG_DATA_TYPE, v2.getPosition());

        v1.visit(this);
        v2.visit(this);
    }

    public void visit(BracketExpression bracketExpression) {
        bracketExpression.getV().visit(this);
    }

    public void visit(Return aReturn) {
        aReturn.getVyraz().visit( this);
    }

    public void visit(Condition condition) {
        //todo
    }

    public void visit(Operator operator) {}

    public void visit(DataType datovyTyp) {}
    public void visit(DataValue dataValue) {}

    public void visit(Int anInt) {}

    public void visit(IWord word) {
        throw new IllegalStateException("Nezadeklarovana visit metoda pre typ : " + word.getClass().getName());
    }

}
