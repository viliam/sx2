package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.IVyraz;

public abstract class VyrazAbstract extends SlovoAbstract implements IVyraz {

    protected IVyraz v1;
    protected Operator op;
    protected IVyraz v2;

    protected VyrazAbstract(Pozicia pozicia, IVyraz v1, Operator op, IVyraz v2) {
        super(pozicia);
        this.v1 = v1;
        this.op = op;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return v1.toString() + op.toString() + v2.toString();
    }

    public IVyraz getV1() {
        return v1;
    }

    public Operator getOp() {
        return op;
    }

    public IVyraz getV2() {
        return v2;
    }
}
