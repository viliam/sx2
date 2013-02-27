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

import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.IVisitor;

public class Block extends SlovoAbstract implements Instrukcia {

    private Instrukcia[] instrukcie;
    private Parenthesis z1;
    private Parenthesis z2;

    public Block(Instrukcia[] instrukcie, Parenthesis z1, Parenthesis z2) {
        super(z1.getPozicia());
        this.z1 = z1;
        this.z2 = z2;
        this.instrukcie = instrukcie;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Instrukcia[] getInstrukcie() {
        return instrukcie;
    }

    public Parenthesis getZ1() {
        return z1;
    }

    public Parenthesis getZ2() {
        return z2;
    }

}
