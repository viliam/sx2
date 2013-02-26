package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Prikaz extends SlovoAbstract implements IVyraz{

    Slovo nazov;
    Parametre parametre;
    Enums.VyrazTyp vyrazTyp = Enums.VyrazTyp.NEURCENY;

    public Prikaz(Slovo nazov, Parametre parametre) {
        super(nazov.getPozicia());
        this.nazov = nazov;
        this.parametre =parametre;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Parametre getParametre() {
        return parametre;
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
