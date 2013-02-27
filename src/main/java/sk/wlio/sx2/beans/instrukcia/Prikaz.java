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

package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Prikaz extends SlovoAbstract implements IVyraz{

    Slovo nazov;
    Parametre parametre;
    Enums.VyrazTyp vyrazTyp = Enums.VyrazTyp.NEURCENY;

    public Prikaz(Slovo nazov, Parametre parametre) {
        super(nazov.getPozicia());
        this.nazov = nazov;
        this.parametre =parametre;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Parametre getParametre() {
        return parametre;
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
