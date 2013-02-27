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
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Cislo implements IVyraz {

    final private Integer cislo;
    final private Pozicia pozicia;


    public Cislo(Integer cislo, Pozicia pozicia) {
        this.pozicia = pozicia;
        this.cislo = cislo;
    }

    @Override
    public String toString() {
        return cislo.toString();
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return Enums.VyrazTyp.CISLO;
    }

    public Integer getCislo() {
        return cislo;
    }

    public Pozicia getPozicia() {
        return pozicia;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
