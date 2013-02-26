package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Assignment;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class PriradenieReader implements TextReader<Assignment> {

    public Assignment citaj(TextContext tC)  {
        Premenna prem = Readers.premena().citaj( tC);

        if ( !tC.jePrefixOperatorPriradenia() )
            throw SxException.create( SxExTyp.CAKAL_OPERATOR , tC);

        Operator op = Readers.opPriradenia().citaj(tC);

        IVyraz v = Readers.vyraz().citaj(tC);
        return new Assignment( prem, op, v, tC.nacitajAkJeBodkoCiarka());
    }

}
