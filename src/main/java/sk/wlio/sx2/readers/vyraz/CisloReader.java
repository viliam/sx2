package sk.wlio.sx2.readers.vyraz;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class CisloReader implements TextReader<Cislo> {

    public Cislo citaj(TextContext tC)  {
        Pozicia pozicia = tC.najdiNasledujuciZnak();
        String cislo = Readers.slovo().citaj( tC).toString();

        try {
            return new Cislo( Integer.valueOf(cislo ), pozicia);
        } catch (NumberFormatException e) {
            throw SxException.create( SxExTyp.CAKAL_CISLO, tC);
        }
    }
}
