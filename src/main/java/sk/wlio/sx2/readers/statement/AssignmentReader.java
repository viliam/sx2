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

package sk.wlio.sx2.readers.statement;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.statement.Assignment;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.interfaces.TextReader;

public class AssignmentReader implements TextReader<Assignment> {

    public Assignment read(TextContext tC)  {
        Variable variable = Readers.variable().read(tC);

        if ( !tC.isPrefixOperatorAssigment() )
            throw SxException.create( SxExTyp.EXPECTED_OPERATOR, tC);

        Operator op = Readers.opAssigment().read(tC);

        IExpression v = Readers.expression().read(tC);
        return new Assignment( variable, op, v, tC.readIfIsSemicolon());
    }

}
