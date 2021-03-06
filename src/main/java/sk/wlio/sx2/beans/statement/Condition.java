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

package sk.wlio.sx2.beans.statement;

import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.IVisitor;

public class Condition extends WordAbstract implements Statement {

    private final StatementWord ak;
    private final Bracket z1, z2;
    private final IExpression vrzBool;
    private final Statement statement;

    public Condition(StatementWord ak, IExpression vrzBool, Statement statement, Bracket z1, Bracket z2) {
        super(ak.getPosition());
        this.ak = ak;
        this.vrzBool = vrzBool;
        this.statement = statement;
        this.z1 = z1;
        this.z2 = z2;
    }

    public StatementWord getAk() {
        return ak;
    }

    public Statement getStatement() {
        return statement;
    }

    public Bracket getZ1() {
        return z1;
    }

    public Bracket getZ2() {
        return z2;
    }
    public IExpression getVrzBool() {
        return vrzBool;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
