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
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.visitors.IVisitor;

public class Return extends WordAbstract implements Statement {

    private Word zakazaneWord;
    private IExpression hodnota;
    private Comma comma;

    public Return(Word zakazaneWord, IExpression hodnota, Comma comma) {
        super(zakazaneWord.getPosition());
        this.hodnota = hodnota;
        this.zakazaneWord = zakazaneWord;
        this.comma = comma;
    }

    public IExpression getVyraz() {
        return hodnota;
    }

    public Word getZakazaneWord() {
        return zakazaneWord;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
