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
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.IVisitor;

public class DeclarationVariable extends WordAbstract implements Statement {

    private DataType datovyTyp;
    private Word nazov;
    private Assignment assignment;
    private Comma comma;

    public DeclarationVariable(DataType datovyTyp, Word nazov, Comma comma) {
        super(datovyTyp.getPosition());
        this.nazov = nazov;
        this.datovyTyp = datovyTyp;
        this.comma = comma;
    }

    public DeclarationVariable(DataType datovyTyp, Word nazov, Assignment assignment) {
        super(datovyTyp.getPosition());
        this.nazov = nazov;
        this.assignment = assignment;
        this.datovyTyp = datovyTyp;
    }

    public DataType getDatovyTyp() {
        return datovyTyp;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Word getName() {
        return nazov;
    }

    public Assignment getAssignment() {
        return assignment;
    }

}