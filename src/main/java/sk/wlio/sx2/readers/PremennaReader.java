package sk.wlio.sx2.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.TextReader;

public class PremennaReader implements TextReader<Premenna> {

    public Premenna citaj(TextContext tC)  {
        Slovo obsah = Readers.slovo().citaj(tC);
        return new Premenna( obsah);
    }


}
