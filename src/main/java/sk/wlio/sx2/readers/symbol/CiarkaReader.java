package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;

public class CiarkaReader extends SymbolAbstractReader<Ciarka> {

    @Override
    protected String[] getSymbols() {
        return SymbolsEnum.CIARKY.getSymbols();
    }

    @Override
    protected Ciarka create(Pozicia pozicia, SymbolEnum oEnum)  {
        return new Ciarka( pozicia, oEnum);
    }

    @Override
    protected SxExTyp getExceptionTyp() {
        return SxExTyp.CAKAL_CIARKU;
    }
}
