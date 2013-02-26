package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;

public class OperatorAritmReader  extends SymbolAbstractReader<Operator> {

    @Override
    protected String[] getSymbols() {
        return SymbolsEnum.OP_ARITM.getSymbols();
    }

    @Override
    protected Operator create(Pozicia pozicia, SymbolEnum oEnum)  {
        return new Operator(pozicia,oEnum);
    }

    @Override
    protected SxExTyp getExceptionTyp() {
        return SxExTyp.CAKAL_OPERATOR;
    }
}
