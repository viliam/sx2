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
import sk.wlio.sx2.beans.statement.DeclarationParameter;
import sk.wlio.sx2.beans.statement.DeclarationVariable;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.interfaces.TextReader;

import java.util.ArrayList;
import java.util.List;

public class DeclarationParameterReader implements TextReader<DeclarationParameter> {

    public DeclarationParameter read(TextContext tC)  {
        TextReader<Bracket> zR = Readers.zatvorka();
        Bracket z1 = zR.read(tC);
        if ( tC.isPrefixBracketClosed() ) {
            Bracket z2 = zR.read(tC);
            return new DeclarationParameter(z1,z2);
        }

        List<DeclarationVariable> declarations = new ArrayList<DeclarationVariable>();
        List<Comma> commas = new ArrayList<Comma>();
        do {
            //parameters are reading
            declarations.add(Readers.dekPremennej().read(tC));
            //at first, I try to read comma
            if ( tC.nextCharacter()==',') {
                commas.add(Readers.ciarka().read(tC));
                continue;
            }
            if ( tC.isPrefixBracketClosed() ) {
                Bracket z2 = zR.read(tC);
                return new DeclarationParameter( z1, z2, commas, declarations );
            }

            //doesn't work => error in parameters reading
            throw  SxException.create(SxExTyp.EXPECTED_BRACKET_OR_COMMA, tC) ;
        }  while ( true);

    }
}
