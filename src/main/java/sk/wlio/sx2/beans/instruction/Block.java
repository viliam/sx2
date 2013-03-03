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
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.IVisitor;

public class Block extends WordAbstract implements Statement {

    private Statement[] instrukcie;
    private Bracket z1;
    private Bracket z2;

    public Block(Statement[] instrukcie, Bracket z1, Bracket z2) {
        super(z1.getPosition());
        this.z1 = z1;
        this.z2 = z2;
        this.instrukcie = instrukcie;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Statement[] getInstrukcie() {
        return instrukcie;
    }

    public Bracket getZ1() {
        return z1;
    }

    public Bracket getZ2() {
        return z2;
    }

}
