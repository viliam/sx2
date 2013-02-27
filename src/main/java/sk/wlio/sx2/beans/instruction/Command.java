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

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Command extends WordAbstract implements IVyraz{

    Word nazov;
    Parameters parameters;
    Enums.VyrazTyp vyrazTyp = Enums.VyrazTyp.NEURCENY;

    public Command(Word nazov, Parameters parameters) {
        super(nazov.getPosition());
        this.nazov = nazov;
        this.parameters = parameters;
    }

    public Word getNazov() {
        return nazov;
    }

    public Parameters getParameters() {
        return parameters;
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
