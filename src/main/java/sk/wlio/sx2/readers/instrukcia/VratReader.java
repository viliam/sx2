package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.Return;
import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class VratReader implements TextReader<Return> {



    public Return citaj(TextContext tC)  {
        Slovo zakazSlovo= Readers.slovo().citaj( tC);
        if ( !ReservedWordEnum.RETURN.je( zakazSlovo.toString())  )
            throw SxException.create( SxExTyp.CAKAL_VRAT, tC);

        return new Return( zakazSlovo, Readers.vyraz().citaj(tC), tC.nacitajAkJeBodkoCiarka());
    }
}
