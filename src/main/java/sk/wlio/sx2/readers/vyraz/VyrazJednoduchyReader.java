package sk.wlio.sx2.readers.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazJednoduchyReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        if ( tC.jePrefixCislo())
            return Readers.cislo().citaj(tC);

        if ( tC.jePrefixPremenna())
            return Readers.premena().citaj(tC);

        if (tC.jePrefixDatovyTyp())
            return Readers.datovyTyp().citaj(tC);

        if (tC.jePrefixPrikaz() )
                return Readers.prikaz().citaj(tC);


        throw SxException.create( SxExTyp.UNEXPECTED_PREFIX, tC );
    }
}
