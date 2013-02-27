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

package sk.wlio.sx2.beans;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Premenna extends SlovoAbstract implements IVyraz {

    private Enums.VyrazTyp vyrazTyp;
    private Slovo nazov;

    public  Premenna(Slovo nazov) {
        super(nazov.getPozicia());
        this.nazov = nazov;
        vyrazTyp = Enums.VyrazTyp.NEURCENY;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return vyrazTyp;
    }

    public void setVyrazTyp(Enums.VyrazTyp vyrazTyp) {
        this.vyrazTyp = vyrazTyp;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }

}
