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

package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.visitors.IVisitor;

public class VyrazZlozeny extends WordAbstract implements IExpression {

    protected IExpression v1;
    protected Operator op;
    protected IExpression v2;

    public VyrazZlozeny(IExpression v1, Operator op, IExpression v2) {
        super(v1.getPosition());
        this.v1 = v1;
        this.op = op;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return v1.toString() + op.toString() + v2.toString();
    }

    public Enums.ExpType getExpType() {
        return op.getVyrazTyp();
    }

    public IExpression getV1() {
        return v1;
    }

    public IExpression getV2() {
        return v2;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
