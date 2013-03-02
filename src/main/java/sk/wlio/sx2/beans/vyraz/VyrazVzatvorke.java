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
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class VyrazVzatvorke implements IVyraz {

    private Bracket z1;
    private IVyraz v;
    private Bracket z2;

    public VyrazVzatvorke(Bracket z1, IVyraz v, Bracket z2) {
        this.z1 = z1;
        this.v = v;
        this.z2 = z2;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return v.getVyrazTyp();
    }

    public Position getPosition() {
        return z1.getPosition();
    }

    @Override
    public String toString() {
        return z1.toString() + v.toString() + z2.toString();
    }

    public Bracket getZ1() {
        return z1;
    }

    public IVyraz getV() {
        return v;
    }

    public Bracket getZ2() {
        return z2;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
