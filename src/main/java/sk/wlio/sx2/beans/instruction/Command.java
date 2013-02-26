package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Command extends SlovoAbstract implements IVyraz{

    Slovo nazov;
    Parameters parameters;
    Enums.VyrazTyp vyrazTyp = Enums.VyrazTyp.NEURCENY;

    public Command(Slovo nazov, Parameters parameters) {
        super(nazov.getPozicia());
        this.nazov = nazov;
        this.parameters = parameters;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Parameters getParameters() {
        return parameters;
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
