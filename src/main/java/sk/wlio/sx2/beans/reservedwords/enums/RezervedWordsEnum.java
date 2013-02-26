package sk.wlio.sx2.beans.reservedwords.enums;

import sk.wlio.sx2.beans.Slovo;

import java.util.ArrayList;
import java.util.List;

public enum RezervedWordsEnum {

    DATA_TYPE(ReservedWordEnum.INT, ReservedWordEnum.BOOL),
    INSTRUCTION_WORD(ReservedWordEnum.RETURN, ReservedWordEnum.IF),
    DATA_VALUE( ReservedWordEnum.VOID, ReservedWordEnum.TRUE, ReservedWordEnum.FALSE);

    private final ReservedWordEnum[] zakazSlova;

    RezervedWordsEnum(ReservedWordEnum... zakazSlova) {
        this.zakazSlova = zakazSlova;
    }

    public boolean is(String s) {
        return is(zakazSlova, s);
    }

    public static boolean jeSlovo(Slovo s) {
        for (RezervedWordsEnum za : RezervedWordsEnum.values()) {
            if (za.is(s.getObsah())) return true;
        }
        return false;
    }

    public ReservedWordEnum vrat(String s) {
        return returns(zakazSlova, s);
    }

    public String[] getSymbols() {
        List<String> li = new ArrayList<String>();
        for (ReservedWordEnum sy: zakazSlova) {
            li.add( sy.getSymbol());
        }
        return li.toArray( new String[ li.size()]);
    }

    private static boolean is(ReservedWordEnum[] anEnums, String s) {
        return returns(anEnums, s) != null;
    }

    private static ReservedWordEnum returns(ReservedWordEnum[] anEnums, String s) {
        for ( ReservedWordEnum symbol: anEnums) {
            if (symbol.getSymbol().equals(s))
                return symbol;
        }
        return null;
    }

}
