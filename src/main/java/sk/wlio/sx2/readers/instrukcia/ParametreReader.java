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
import sk.wlio.sx2.beans.instruction.Parameters;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

import java.util.LinkedList;
import java.util.List;

public class ParametreReader implements TextReader<Parameters> {

    public Parameters citaj(TextContext tC)  {
        Parenthesis z1 = Readers.zatvorka().citaj( tC);
        if ( tC.jePrefixZatvorkaZatvorena() ) {
            Parenthesis z2 = Readers.zatvorka().citaj( tC);
            return new Parameters(z1,z2);
        }

        List<IVyraz> parametre = new LinkedList<IVyraz>();
        List<Comma> ciarky = new LinkedList<Comma>();
        do {   //citam obsah zatvorky , parametre
             parametre.add( Readers.vyraz().citaj( tC) );
//najskuor skusim naciatat ciarku
             if ( tC.jePrefixCiarka() ) {
                 Comma c = Readers.ciarka().citaj( tC);
                 ciarky.add(c);
             }  else{
                 //este skusim nacitat koniec zatvorky 
                 Parenthesis z2 = Readers.zatvorka().citaj(tC);
//TEST: je postacujuca ochhrana na koniec zatvorky?
                 return new Parameters( z1, z2, parametre , ciarky );
             }
        }  while ( true);     //bud docita parametre (t.j. koniec zatvorky), alebo hodi exception
    }
}
