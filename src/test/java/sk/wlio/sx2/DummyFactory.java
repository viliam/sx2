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

package sk.wlio.sx2;

import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.Assignment;
import sk.wlio.sx2.beans.statement.DeclarationVariable;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.interfaces.IExpression;

public class DummyFactory {

    public static Int createCislo(int cislo) {
        return new Int(cislo, null);
    }

    public static Operator createOperator(SymbolEnum sEnum) {
        return new Operator(null, sEnum);
    }

    public static Variable createPremenna(String nazov) {
        return new Variable( new Word(new Position(0,0), nazov));
    }

    public static Assignment createPriradenie(Variable variable, IExpression expression) {
        return new Assignment(variable, createOperator(SymbolEnum.ASSIGN), expression, null);
    }

    public static DeclarationVariable createDeklaraciaPremennej(String typ, String nazov) {
        return new DeclarationVariable(
                new DataType( null, typ),
                new Word(null, nazov), new Comma(null, null)
        );
    }


}
