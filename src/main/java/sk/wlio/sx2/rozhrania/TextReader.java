package sk.wlio.sx2.rozhrania;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxException;

public interface TextReader<E extends ISlovo> {
                 
    public E citaj(TextContext tC) ;

}
