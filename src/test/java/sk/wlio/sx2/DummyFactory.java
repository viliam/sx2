package sk.wlio.sx2;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.instrukcia.Priradenie;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.rozhrania.IVyraz;

public class DummyFactory {

    public static Cislo createCislo(int cislo) {
        return new Cislo(cislo, null);
    }

    public static Operator createOperator(SymbolEnum sEnum) {
        return new Operator(null, sEnum);
    }

    public static Premenna createPremenna(String nazov) {
        return new Premenna( new Slovo(new Pozicia(0,0), nazov));
    }

    public static Priradenie createPriradenie(Premenna premenna, IVyraz vyraz) {
        return new Priradenie(premenna, createOperator(SymbolEnum.PRIRAD), vyraz, null);
    }

    public static DeklaraciaPremennej createDeklaraciaPremennej(String typ, String nazov) {
        return new DeklaraciaPremennej(
                new DatovyTyp( null, typ),
                new Slovo(null, nazov), new Ciarka(null, null)
        );
    }


}
