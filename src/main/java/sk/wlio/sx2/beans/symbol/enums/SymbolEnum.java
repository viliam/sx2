package sk.wlio.sx2.beans.symbol.enums;

public enum SymbolEnum {
    //ARITM
    PLUS("+"), MINUS("-"), KRAT ("*"),
    MODULO("%") ,ZVYSOK("/"),
    //BOOL
    AND("&"), OR("|"), AND_STRONG("&&"), OR_STRONG("||"),
    //POROVNANIE
    MENSI("<"), VACSI(">"), MENSI_ROVNY ("<="),
    VACSI_ROVNY(">=") ,ROVNY("=="), NEROVNY("!="),
    PRIRAD("="),

    ZATVORKA_NORM_OTOVRENA("("),
    ZATVORKA_NORM_ZATVORENA(")"),
    ZATVORKA_BLOK_OTVORENA("{"),
    ZATVORKA_BLOK_ZATVORENA("}"),

    CIARKA(","),
    BODKO_CIARKA(";"),
    BODKA(",");


    private final String symbol;

    SymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toString() {
        return symbol;
    }

    public static SymbolEnum makeSymbol(String s) {
        for ( SymbolEnum op : SymbolEnum.values()) {
            if ( op.toString().equals(s))
                return op;
        }
        return null;
    }

    public boolean jePrefix(char a) {
        return symbol.charAt(0) == a;
    }
}
