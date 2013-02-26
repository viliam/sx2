package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.instrukcia.Priradenie;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class DeklaraciaPremennejReader implements TextReader<DeklaraciaPremennej> {

    public DeklaraciaPremennej citaj(TextContext tC)  {
        DatovyTyp datovyTyp = Readers.datovyTyp().citaj(tC);
        Slovo nazov = Readers.slovo().citaj( tC);

        if ( tC.jePrefixOperatorPriradenia() ) {
            Operator op = Readers.opPriradenia().citaj(tC);
            IVyraz v = Readers.vyraz().citaj(tC);

            Premenna pre = new Premenna(nazov);
            pre.setVyrazTyp( datovyTyp.getVyrazTyp());
            Priradenie pri = new Priradenie( pre,op,v , tC.nacitajAkJeBodkoCiarka());
            return new DeklaraciaPremennej( datovyTyp, nazov , pri);
        }

        return new DeklaraciaPremennej( datovyTyp, nazov,tC.nacitajAkJeBodkoCiarka());
    }

}
