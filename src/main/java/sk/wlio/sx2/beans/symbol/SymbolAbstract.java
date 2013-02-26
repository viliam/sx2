package sk.wlio.sx2.beans.symbol;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;

public abstract class SymbolAbstract extends SlovoAbstract {

    protected SymbolEnum symbol;

    public SymbolAbstract(Pozicia pozicia, SymbolEnum symbol) {
        super(pozicia);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return "" +symbol.getSymbol();
    }

    public String toString() {
        return getSymbol();
    }

}

