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

package sk.wlio.sx2.readers.instruction;


import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.DeclarationParameter;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

import java.util.ArrayList;
import java.util.List;

public class DeclarationParameterReader implements TextReader<DeclarationParameter> {

    public DeclarationParameter citaj(TextContext tC)  {
        TextReader<Parenthesis> zR = Readers.zatvorka();
        Parenthesis z1 = zR.citaj( tC);
        if ( tC.jePrefixZatvorkaZatvorena() ) {
            Parenthesis z2 = zR.citaj( tC);
            return new DeclarationParameter(z1,z2);
        }

        List<DeclarationVariable> declarations = new ArrayList<DeclarationVariable>();
        List<Comma> commas = new ArrayList<Comma>();
        do {
            //parameters are reading
            declarations.add(Readers.dekPremennej().citaj(tC));
            //at first, I try to read comma
            if ( tC.getNasledujuciZnak()==',') {
                commas.add(Readers.ciarka().citaj(tC));
                continue;
            }
            if ( tC.jePrefixZatvorkaZatvorena() ) {
                Parenthesis z2 = zR.citaj( tC);
                return new DeclarationParameter( z1, z2, commas, declarations );
            }

            //doesn't work => error in parameters reading
            throw  SxException.create(SxExTyp.CAKAL_ZATVORKU_ALEBO_CIARKU, tC) ;
        }  while ( true);

    }
}
