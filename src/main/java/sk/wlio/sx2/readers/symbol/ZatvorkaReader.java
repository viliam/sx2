package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;

public class ZatvorkaReader extends SymbolAbstractReader<Zatvorka> {

    @Override
    protected String[] getSymbols() {
        return SymbolsEnum.ZATVORKY.getSymbols();
    }

    @Override
    protected Zatvorka create(Pozicia pozicia, SymbolEnum oEnum)  {
        if (oEnum == null)
            throw SxException.create(SxExTyp.CAKAL_ZATVORKU, pozicia);
        return new Zatvorka(pozicia, oEnum );
    }

    @Override
    protected SxExTyp getExceptionTyp() {
        return SxExTyp.CAKAL_ZATVORKU;
    }
}
