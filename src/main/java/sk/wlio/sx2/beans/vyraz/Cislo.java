package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Cislo implements IVyraz {

    final private Integer cislo;
    final private Pozicia pozicia;


    public Cislo(Integer cislo, Pozicia pozicia) {
        this.pozicia = pozicia;
        this.cislo = cislo;
    }

    @Override
    public String toString() {
        return cislo.toString();
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return Enums.VyrazTyp.CISLO;
    }

    public Integer getCislo() {
        return cislo;
    }

    public Pozicia getPozicia() {
        return pozicia;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
