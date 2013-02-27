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
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class VyrazVzatvorke implements IVyraz {

    private Zatvorka z1;
    private IVyraz v;
    private Zatvorka z2;

    public VyrazVzatvorke(Zatvorka z1, IVyraz v, Zatvorka z2) {
        this.z1 = z1;
        this.v = v;
        this.z2 = z2;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return v.getVyrazTyp();
    }

    public Pozicia getPozicia() {
        return z1.getPozicia();
    }

    @Override
    public String toString() {
        return z1.toString() + v.toString() + z2.toString();
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public IVyraz getV() {
        return v;
    }

    public Zatvorka getZ2() {
        return z2;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
