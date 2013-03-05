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

import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class DeclarationParameter implements IWord {

    Bracket b1, b2;

    List<DeclarationVariable> liDecVariable = new ArrayList<DeclarationVariable>();
    List<Comma> liComma = new ArrayList<Comma>();

    public DeclarationParameter(Bracket b1, Bracket b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    public DeclarationParameter(Bracket b1, Bracket b2, List<Comma> liComma, List<DeclarationVariable> liDecVariable) {
        this.liComma = liComma;
        this.liDecVariable = liDecVariable;
        this.b1 = b1;
        this.b2 = b2;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public List<Comma> getLiComma() {
        return liComma;
    }

    public List<DeclarationVariable> getLiDecVariable() {
        return liDecVariable;
    }

    public Bracket getB1() {
        return b1;
    }

    public Bracket getB2() {
        return b2;
    }

    public Position getPosition() {
        return b1.getPosition();
    }

}
