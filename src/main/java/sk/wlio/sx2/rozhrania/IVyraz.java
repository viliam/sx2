package sk.wlio.sx2.rozhrania;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.visitors.IVisitor;

public interface IVyraz extends ISlovo {
    public Enums.VyrazTyp getVyrazTyp();
    public void visit(IVisitor iVisitor);
}
