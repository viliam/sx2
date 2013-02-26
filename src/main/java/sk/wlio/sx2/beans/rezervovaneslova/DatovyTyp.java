package sk.wlio.sx2.beans.rezervovaneslova;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class DatovyTyp extends Slovo implements IVyraz {

    Enums.VyrazTyp typ = Enums.VyrazTyp.NIC;

    public DatovyTyp(Slovo slovo) {
        super(slovo);
    }


    public DatovyTyp(Pozicia pozicia, String obsah) {
        super(pozicia, obsah);
    }

    public Enums.VyrazTyp getTyp() {
        return typ;
    }

    public void setTyp(Enums.VyrazTyp typ) {
        this.typ = typ;
    }

    public Enums.VyrazTyp getVyrazTyp() {
        return typ;
    }

    public void visit(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
