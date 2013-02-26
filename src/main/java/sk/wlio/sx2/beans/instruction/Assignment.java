package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class Assignment extends SlovoAbstract implements Instrukcia {

    private Premenna premenna;
    private IVyraz vyraz;
    private Operator op;
    private Ciarka ciarka;

    public Assignment(Premenna premenna, Operator op, IVyraz vyraz, Ciarka ciarka) {
        super(premenna.getPozicia());
        this.premenna = premenna;
        this.op =op;
        this.vyraz = vyraz;
        this.ciarka = ciarka;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public IVyraz getVyraz() {
        return vyraz;
    }

    public Premenna getPremenna() {
        return premenna;
    }

    public Operator getOp() {
        return op;
    }

}
