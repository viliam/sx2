package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Parameters;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

import java.util.LinkedList;
import java.util.List;

public class ParametreReader implements TextReader<Parameters> {

    public Parameters citaj(TextContext tC)  {
        Zatvorka z1 = Readers.zatvorka().citaj( tC);
        if ( tC.jePrefixZatvorkaZatvorena() ) {
            Zatvorka z2 = Readers.zatvorka().citaj( tC);
            return new Parameters(z1,z2);
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
                 return new Parameters( z1, z2, parametre , ciarky );
             }
        }  while ( true);     //bud docita parametre (t.j. koniec zatvorky), alebo hodi exception
    }
}
