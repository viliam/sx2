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

import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class DeclarationParameter implements ISlovo {

    Bracket z1, z2;

    List<DeclarationVariable> liDekPremennej = new ArrayList<DeclarationVariable>();
    List<Comma> liComma = new ArrayList<Comma>();

    public DeclarationParameter(Bracket z1, Bracket z2) {
        this.z1 = z1;
        this.z2 = z2;
    }

    public DeclarationParameter(Bracket z1, Bracket z2, List<Comma> liComma, List<DeclarationVariable> liDekPremennej) {
        this.liComma = liComma;
        this.liDekPremennej = liDekPremennej;
        this.z1 = z1;
        this.z2 = z2;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public List<Comma> getLiComma() {
        return liComma;
    }

    public List<DeclarationVariable> getLiDekPremennej() {
        return liDekPremennej;
    }

    public Bracket getZ1() {
        return z1;
    }

    public Bracket getZ2() {
        return z2;
    }

    public Position getPosition() {
        return z1.getPosition();
    }

}
