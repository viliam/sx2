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

package sk.wlio.sx2.visitors;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.DataValue;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.rozhrania.ISlovo;

public interface IVisitor {
    void visit(VyrazZlozeny vyrazZlozeny);
    void visit(VyrazVzatvorke vyrazVzatvorke);

    void visit(Premenna premenna);
    void visit(Command command);
    void visit(Parameters parameters);

    void visit(Operator operator);
    void visit(DataType datovyTyp);

    void visit(DataValue dataValue);
    void visit(Cislo cislo);

    void visit(Assignment assignment);
    void visit(Block block);
    void visit(Return aReturn);

    void visit(DeclarationVariable dekPremennej);
    void visit(DeclarationCommand dekPrikaz);
    void visit(DeclarationParameter dekParameter);
    void visit(Program program);

    public void visit(ISlovo slovo);
}

