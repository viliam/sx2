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

import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.IVisitor;

public class Vrat extends SlovoAbstract implements Instrukcia{

    private Slovo zakazaneSlovo;
    private IVyraz hodnota;
    private Ciarka ciarka;

    public Vrat(Slovo zakazaneSlovo, IVyraz hodnota, Ciarka ciarka) {
        super(zakazaneSlovo.getPozicia());
        this.hodnota = hodnota;
        this.zakazaneSlovo = zakazaneSlovo;
        this.ciarka = ciarka;
    }

    public IVyraz getVyraz() {
        return hodnota;
    }

    public Slovo getZakazaneSlovo() {
        return zakazaneSlovo;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
