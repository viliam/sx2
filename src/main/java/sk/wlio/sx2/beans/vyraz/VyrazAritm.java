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
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

@Deprecated
public class VyrazAritm extends VyrazAbstract {

    public VyrazAritm(IVyraz v1, Operator op, IVyraz v2) {
        super(v1.getPozicia(), v1, op, v2);
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return Enums.VyrazTyp.CISLO;
    }

    public void visit(IVisitor iVisitor) {
        throw new IllegalStateException("Vyraz Artim is depredacated");
    }
}
