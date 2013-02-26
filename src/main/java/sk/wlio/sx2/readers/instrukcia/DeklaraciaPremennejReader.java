package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class DeklaraciaPremennejReader implements TextReader<DeclarationVariable> {

    public DeclarationVariable citaj(TextContext tC)  {
        DataType datovyTyp = Readers.datovyTyp().citaj(tC);
        Slovo nazov = Readers.slovo().citaj( tC);

        if ( tC.jePrefixOperatorPriradenia() ) {
            Operator op = Readers.opPriradenia().citaj(tC);
            IVyraz v = Readers.vyraz().citaj(tC);

            Premenna pre = new Premenna(nazov);
            pre.setVyrazTyp( datovyTyp.getVyrazTyp());
            Assignment pri = new Assignment( pre,op,v , tC.nacitajAkJeBodkoCiarka());
            return new DeclarationVariable( datovyTyp, nazov , pri);
        }

        return new DeclarationVariable( datovyTyp, nazov,tC.nacitajAkJeBodkoCiarka());
    }

}
