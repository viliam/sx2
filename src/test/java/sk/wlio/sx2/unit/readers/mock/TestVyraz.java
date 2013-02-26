package sk.wlio.sx2.unit.readers.mock;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class TestVyraz implements IVyraz {

    Enums.VyrazTyp vyrazTyp = Enums.VyrazTyp.CISLO;

    public TestVyraz() {}
    public TestVyraz(Enums.VyrazTyp vyrazTyp) {
        this.vyrazTyp = vyrazTyp;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return vyrazTyp;
    }

    public Pozicia getPozicia() {
        return null;
    }

    public void visit(IVisitor iVisitor) {
     //   iVisitor.visit(this);
    }
}
