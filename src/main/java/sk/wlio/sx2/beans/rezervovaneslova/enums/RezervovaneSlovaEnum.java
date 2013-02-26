package sk.wlio.sx2.beans.rezervovaneslova.enums;

import sk.wlio.sx2.beans.Slovo;

import java.util.ArrayList;
import java.util.List;

public enum RezervovaneSlovaEnum {

    DATOVY_TYP(RezervovaneSlovoEnum.CISLO, RezervovaneSlovoEnum.BOOL),
    INSTRUKCIA_SLOVO(RezervovaneSlovoEnum.VRAT, RezervovaneSlovoEnum.AK),
    DATOVA_HODNOTA( RezervovaneSlovoEnum.NIC, RezervovaneSlovoEnum.ANO, RezervovaneSlovoEnum.NIE);

    private final RezervovaneSlovoEnum[] zakazSlova;

    RezervovaneSlovaEnum(RezervovaneSlovoEnum... zakazSlova) {
        this.zakazSlova = zakazSlova;
    }

    public boolean je(String s) {
        return je(zakazSlova, s);
    }

    public static boolean jeSlovo(Slovo s) {
        for (RezervovaneSlovaEnum za : RezervovaneSlovaEnum.values()) {
            if (za.je(s.getObsah())) return true;
        }
        return false;
    }

    public RezervovaneSlovoEnum vrat(String s) {
        return vrat(zakazSlova, s);
    }

    public String[] getSymbols() {
        List<String> li = new ArrayList<String>();
        for (RezervovaneSlovoEnum sy: zakazSlova) {
            li.add( sy.getSymbol());
        }
        return li.toArray( new String[ li.size()]);
    }

    private static boolean je(RezervovaneSlovoEnum[] anEnums, String s) {
        return vrat(anEnums,s) != null;
    }

    private static RezervovaneSlovoEnum vrat(RezervovaneSlovoEnum[] anEnums, String s) {
        for ( RezervovaneSlovoEnum symbol: anEnums) {
            if (symbol.getSymbol().equals(s))
                return symbol;
        }
        return null;
    }

}
