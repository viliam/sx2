package sk.wlio.sx2.beans.rezervovaneslova.enums;

public enum RezervovaneSlovoEnum {

    CISLO("cislo") ,
    BOOL("bool"),
    VRAT("vrat"),
    AK("ak"),
    NIC("nic"),
    ANO("ano"),
    NIE("nie");

    private final String symbol;

    RezervovaneSlovoEnum(String symbol) {
        this.symbol = symbol;
    }

    public boolean je(String s) {
        return symbol.equals(s);
    }

    public String getSymbol() {
        return symbol;
    }

    public static RezervovaneSlovoEnum makeSymbol(String sZakaz) {
        for ( RezervovaneSlovoEnum zakaz : RezervovaneSlovoEnum.values()) {
            if ( zakaz.toString().equals(sZakaz))
                return zakaz;
        }
        return null;
    }

    public String toString() {
        return symbol;
    }
    
    public static boolean jeZakazaneSlovo(String zakazaneSlovo) {
        for ( RezervovaneSlovoEnum zs : RezervovaneSlovoEnum.values())
            if (zs.getSymbol().equals( zakazaneSlovo) )
                return true;
        return false;
    }

    public static String[] getZakazaneSlova() {
        RezervovaneSlovoEnum[] zs = RezervovaneSlovoEnum.values();
        String[] strs = new String[ zs.length];
        for (int i=0; i <zs.length; i++) {
            strs[i] = zs[i].getSymbol();
        }
        return  strs;
    }

    public boolean jePrefix(char a) {
        throw new IllegalStateException("Nema zmysel zistovat prefix slova");
    }
}
