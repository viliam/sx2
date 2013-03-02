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
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class DeclarationVariableReader implements TextReader<DeclarationVariable> {

    public DeclarationVariable citaj(TextContext tC)  {
        DataType dataType = Readers.datovyTyp().citaj(tC);
        Word name = Readers.slovo().citaj( tC);

        if ( tC.jePrefixOperatorPriradenia() ) {
            Operator op = Readers.opPriradenia().citaj(tC);
            IVyraz v = Readers.vyraz().citaj(tC);

            Variable pre = new Variable(name);
            pre.setVyrazTyp(dataType.getVyrazTyp());
            Assignment assignment = new Assignment( pre,op,v , tC.nacitajAkJeBodkoCiarka());
            return new DeclarationVariable( dataType, name , assignment);
        }

        return new DeclarationVariable( dataType, name,tC.nacitajAkJeBodkoCiarka());
    }

}
