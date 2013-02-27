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

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instrukcia.Parametre;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

import java.util.LinkedList;
import java.util.List;

public class ParametreReader implements TextReader<Parametre> {

    public Parametre citaj(TextContext tC)  {
        Zatvorka z1 = Readers.zatvorka().citaj( tC);
        if ( tC.jePrefixZatvorkaZatvorena() ) {
            Zatvorka z2 = Readers.zatvorka().citaj( tC);
            return new Parametre(z1,z2);                         
        }

        List<IVyraz> parametre = new LinkedList<IVyraz>();
        List<Ciarka> ciarky = new LinkedList<Ciarka>();
        do {   //citam obsah zatvorky , parametre
             parametre.add( Readers.vyraz().citaj( tC) );
//najskuor skusim naciatat ciarku
             if ( tC.jePrefixCiarka() ) {
                 Ciarka c = Readers.ciarka().citaj( tC);
                 ciarky.add(c);
             }  else{
                 //este skusim nacitat koniec zatvorky 
                 Zatvorka z2 = Readers.zatvorka().citaj(tC);
//TEST: je postacujuca ochhrana na koniec zatvorky?
                 return new Parametre( z1, z2, parametre , ciarky );
             }
        }  while ( true);     //bud docita parametre (t.j. koniec zatvorky), alebo hodi exception
    }
}
