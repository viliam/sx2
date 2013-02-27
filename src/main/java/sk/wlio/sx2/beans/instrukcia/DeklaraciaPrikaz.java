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

import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.visitors.IVisitor;

public class DeklaraciaPrikaz extends SlovoAbstract {

    private DatovyTyp datovyTyp;
    private Slovo nazov;
    private DeklaraciaParameter dekParam;
    private Blok telo;

    public DeklaraciaPrikaz(DatovyTyp datovyTyp, Slovo nazov, DeklaraciaParameter dekParam, Blok telo) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.dekParam = dekParam;
        this.telo = telo;
        this.datovyTyp = datovyTyp;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public void setNazov(Slovo nazov) {
        this.nazov = nazov;
    }

    public DeklaraciaParameter getDekParam() {
        return dekParam;
    }

    public void setDekParam(DeklaraciaParameter dekParam) {
        this.dekParam = dekParam;
    }

    public Blok getTelo() {
        return telo;
    }

    public void setTelo(Blok telo) {
        this.telo = telo;
    }

    public DatovyTyp getDatovyTyp() {
        return datovyTyp;
    }

    public void setDatovyTyp(DatovyTyp datovyTyp) {
        this.datovyTyp = datovyTyp;
    }
}
