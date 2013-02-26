package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class VyrazZlozeny extends SlovoAbstract implements IVyraz {

    protected IVyraz v1;
    protected Operator op;
    protected IVyraz v2;

    public VyrazZlozeny(IVyraz v1, Operator op, IVyraz v2) {
        super(v1.getPozicia());
        this.v1 = v1;
        this.op = op;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return v1.toString() + op.toString() + v2.toString();
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return op.getVyrazTyp();
    }

    public IVyraz getV1() {
        return v1;
    }

    public IVyraz getV2() {
        return v2;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
