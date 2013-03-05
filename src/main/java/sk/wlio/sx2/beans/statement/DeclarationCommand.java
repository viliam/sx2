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

package sk.wlio.sx2.beans.statement;

import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.visitors.IVisitor;

public class DeclarationCommand extends WordAbstract {

    private DataType dataType;
    private Word nazov;
    private DeclarationParameter dekParam;
    private Block body;

    public DeclarationCommand(DataType dataType, Word nazov, DeclarationParameter dekParam, Block body) {
        super(dataType.getPosition());
        this.nazov = nazov;
        this.dekParam = dekParam;
        this.body = body;
        this.dataType = dataType;
    }

    public Word getName() {
        return nazov;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public void setNazov(Word nazov) {
        this.nazov = nazov;
    }

    public DeclarationParameter getDekParam() {
        return dekParam;
    }

    public void setDekParam(DeclarationParameter dekParam) {
        this.dekParam = dekParam;
    }

    public Block getTelo() {
        return body;
    }

    public void setTelo(Block body) {
        this.body = body;
    }

    public DataType getDatovyTyp() {
        return dataType;
    }

    public void setDatovyTyp(DataType dataType) {
        this.dataType = dataType;
    }
}
