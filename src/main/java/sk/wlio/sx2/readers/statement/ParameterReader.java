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
import sk.wlio.sx2.beans.statement.Parameters;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.interfaces.TextReader;

import java.util.LinkedList;
import java.util.List;

public class ParameterReader implements TextReader<Parameters> {

    public Parameters read(TextContext tC)  {
        Bracket z1 = Readers.bracket().read(tC);
        if ( tC.isPrefixBracketClosed() ) {
            Bracket z2 = Readers.bracket().read(tC);
            return new Parameters(z1,z2);
        }

        List<IExpression> listParameter = new LinkedList<IExpression>();
        List<Comma> listComman = new LinkedList<Comma>();
        do {
             listParameter.add(Readers.expression().read(tC));

             if ( tC.isPrefixComma() ) {
                 Comma c = Readers.comma().read(tC);
                 listComman.add(c);
             }  else{
                 Bracket z2 = Readers.bracket().read(tC);
                 return new Parameters( z1, z2, listParameter , listComman );
             }
        }  while ( true);
    }
}
