package sk.wlio.sx2.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.TextReader;

public abstract class RezervovaneSlovaAbstractReader<E extends Slovo> implements TextReader<E> {

    public E citaj(TextContext tC)  {
        Slovo slovo = Readers.slovo().citaj( tC);
        String s = slovo.toString();

        if (!getZakazaneSlova().je(s))
           throw SxException.create(getSxExceptionTyp(), tC);

        return (E) slovo;
    }

    protected abstract SxExTyp getSxExceptionTyp();

    protected abstract RezervovaneSlovaEnum getZakazaneSlova();

}