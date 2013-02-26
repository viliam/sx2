package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class DeklaraciaPrikazReader implements TextReader<DeklaraciaPrikaz>  {

    public DeklaraciaPrikaz citaj(TextContext tC)  {
       DatovyTyp datovyTyp = Readers.datovyTyp().citaj( tC);
       String sDatovyTyp = datovyTyp.toString();
       if (!RezervovaneSlovaEnum.DATOVY_TYP.je( sDatovyTyp))
           throw SxException.create(SxExTyp.CAKAL_DEKLARACIU_PRIKAZU, tC);

       if ( !tC.jePrefixPrikaz())
          throw SxException.create(SxExTyp.ZLY_NAZOV_PRIKAZU, tC);

       Slovo nazov = Readers.slovo().citaj( tC);
       //odkusnem parametre
       DeklaraciaParameter dekParam = Readers.dekParameter().citaj(tC);
       Blok blok = Readers.blok().citaj( tC);

       return new DeklaraciaPrikaz(datovyTyp, nazov, dekParam, blok);
    }
}
