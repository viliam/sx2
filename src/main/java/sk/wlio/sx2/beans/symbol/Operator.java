package sk.wlio.sx2.beans.symbol;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;

public final class Operator extends SymbolAbstract {

    public Operator(Pozicia pozicia, SymbolEnum symbol) {
        super(pozicia, symbol);
    }

    public Enums.VyrazTyp getVyrazTyp() {
        switch ( symbol) {
            case  PLUS : case MINUS: case KRAT:
            case MODULO : case ZVYSOK :
                return Enums.VyrazTyp.CISLO;

//    //BOOL
            case AND : case OR : case AND_STRONG: case OR_STRONG:
                return Enums.VyrazTyp.BOOL;

//    //POROVNANIE
            case MENSI : case VACSI : case MENSI_ROVNY :
            case VACSI_ROVNY : case ROVNY : case NEROVNY :
                return Enums.VyrazTyp.POROVNANIE;
//    PRIRAD("=")
            case PRIRAD :
                return Enums.VyrazTyp.NEURCENY;
        }
        return null;
    }
}
