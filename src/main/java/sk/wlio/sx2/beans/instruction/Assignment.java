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
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Assignment extends SlovoAbstract implements Instrukcia {

    private Premenna premenna;
    private IVyraz vyraz;
    private Operator op;
    private Ciarka ciarka;

    public Assignment(Premenna premenna, Operator op, IVyraz vyraz, Ciarka ciarka) {
        super(premenna.getPozicia());
        this.premenna = premenna;
        this.op =op;
        this.vyraz = vyraz;
        this.ciarka = ciarka;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public IVyraz getVyraz() {
        return vyraz;
    }

    public Premenna getPremenna() {
        return premenna;
    }

    public Operator getOp() {
        return op;
    }

}
