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

package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.IVisitor;

public class Assignment extends WordAbstract implements Statement {

    private Variable variable;
    private IExpression expression;
    private Operator op;
    private Comma comma;

    public Assignment(Variable variable, Operator op, IExpression expression, Comma comma) {
        super(variable.getPosition());
        this.variable = variable;
        this.op =op;
        this.expression = expression;
        this.comma = comma;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public IExpression getExpression() {
        return expression;
    }

    public Variable getVariable() {
        return variable;
    }

    public Operator getOp() {
        return op;
    }

}
