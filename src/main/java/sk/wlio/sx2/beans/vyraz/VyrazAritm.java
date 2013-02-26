package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

@Deprecated
public class VyrazAritm extends VyrazAbstract {

    public VyrazAritm(IVyraz v1, Operator op, IVyraz v2) {
        super(v1.getPozicia(), v1, op, v2);
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return Enums.VyrazTyp.CISLO;
    }

    public void visit(IVisitor iVisitor) {
        throw new IllegalStateException("Vyraz Artim is depredacated");
    }
}
