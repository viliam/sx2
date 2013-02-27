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

package sk.wlio.sx2.beans.reservedwords;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class DataType extends Slovo implements IVyraz {

    Enums.VyrazTyp typ = Enums.VyrazTyp.NIC;

    public DataType(Slovo slovo) {
        super(slovo);
    }


    public DataType(Pozicia pozicia, String obsah) {
        super(pozicia, obsah);
    }

    public Enums.VyrazTyp getTyp() {
        return typ;
    }

    public void setTyp(Enums.VyrazTyp typ) {
        this.typ = typ;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return typ;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
