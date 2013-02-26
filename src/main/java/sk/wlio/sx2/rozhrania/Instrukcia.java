package sk.wlio.sx2.rozhrania;

import sk.wlio.sx2.visitors.DeklaracieVisitor;
import sk.wlio.sx2.visitors.IVisitor;

public interface Instrukcia extends ISlovo {

    void visit(IVisitor visitor);

}
