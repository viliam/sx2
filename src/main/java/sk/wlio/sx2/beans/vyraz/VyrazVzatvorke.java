package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class VyrazVzatvorke implements IVyraz {

    private Zatvorka z1;
    private IVyraz v;
    private Zatvorka z2;

    public VyrazVzatvorke(Zatvorka z1, IVyraz v, Zatvorka z2) {
        this.z1 = z1;
        this.v = v;
        this.z2 = z2;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return v.getVyrazTyp();
    }

    public Pozicia getPozicia() {
        return z1.getPozicia();
    }

    @Override
    public String toString() {
        return z1.toString() + v.toString() + z2.toString();
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public IVyraz getV() {
        return v;
    }

    public Zatvorka getZ2() {
        return z2;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
