package sk.wlio.sx2.beans;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Premenna extends SlovoAbstract implements IVyraz {

    private Enums.VyrazTyp vyrazTyp;
    private Slovo nazov;

    public  Premenna(Slovo nazov) {
        super(nazov.getPozicia());
        this.nazov = nazov;
        vyrazTyp = Enums.VyrazTyp.NEURCENY;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return vyrazTyp;
    }

    public void setVyrazTyp(Enums.VyrazTyp vyrazTyp) {
        this.vyrazTyp = vyrazTyp;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }

}
