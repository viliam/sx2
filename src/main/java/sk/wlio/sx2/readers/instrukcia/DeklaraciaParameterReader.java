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

package sk.wlio.sx2.readers.instrukcia;


import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.DeclarationParameter;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

import java.util.ArrayList;
import java.util.List;

public class DeklaraciaParameterReader implements TextReader<DeclarationParameter> {

    public DeclarationParameter citaj(TextContext tC)  {
        TextReader<Zatvorka> zR = Readers.zatvorka();
        Zatvorka z1 = zR.citaj( tC);
        if ( tC.jePrefixZatvorkaZatvorena() ) {
            Zatvorka z2 = zR.citaj( tC);
            return new DeclarationParameter(z1,z2);
        }

        List<DeclarationVariable> deklaracie = new ArrayList<DeclarationVariable>();
        List<Ciarka> ciarky = new ArrayList<Ciarka>();
        do {
            //citam obsah zatvorky , parametre
            deklaracie.add( Readers.dekPremennej().citaj( tC) );
            //najskuor skusim naciatat ciarku
            if ( tC.getNasledujuciZnak()==',') {
                ciarky.add( Readers.ciarka().citaj(tC));
                continue;
            }
            if ( tC.jePrefixZatvorkaZatvorena() ) {
                Zatvorka z2 = zR.citaj( tC);
                return new DeclarationParameter( z1, z2, ciarky, deklaracie );
            }

            //nepodarilo sa => zle zadane parametre
            throw  SxException.create(SxExTyp.CAKAL_ZATVORKU_ALEBO_CIARKU, tC) ;
        }  while ( true);     //bud docita parametre (t.j. koniec zatvorky), alebo hodi exception

    }
}
