package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class DeklaraciaPrikazReader implements TextReader<DeclarationCommand>  {

    public DeclarationCommand citaj(TextContext tC)  {
       DataType dataType = Readers.datovyTyp().citaj( tC);
       String sDatovyTyp = dataType.toString();
       if (!RezervedWordsEnum.DATA_TYPE.is(sDatovyTyp))
           throw SxException.create(SxExTyp.CAKAL_DEKLARACIU_PRIKAZU, tC);

       if ( !tC.jePrefixPrikaz())
          throw SxException.create(SxExTyp.ZLY_NAZOV_PRIKAZU, tC);

       Slovo nazov = Readers.slovo().citaj( tC);
       //odkusnem parametre
       DeclarationParameter dekParam = Readers.dekParameter().citaj(tC);
       Block block = Readers.blok().citaj( tC);

       return new DeclarationCommand(dataType, nazov, dekParam, block);
    }
}
