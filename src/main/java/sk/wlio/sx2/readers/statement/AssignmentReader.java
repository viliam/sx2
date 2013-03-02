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
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class AssignmentReader implements TextReader<Assignment> {

    public Assignment citaj(TextContext tC)  {
        Variable variable = Readers.premena().citaj( tC);

        if ( !tC.jePrefixOperatorPriradenia() )
            throw SxException.create( SxExTyp.CAKAL_OPERATOR , tC);

        Operator op = Readers.opPriradenia().citaj(tC);

        IVyraz v = Readers.vyraz().citaj(tC);
        return new Assignment( variable, op, v, tC.nacitajAkJeBodkoCiarka());
    }

}
