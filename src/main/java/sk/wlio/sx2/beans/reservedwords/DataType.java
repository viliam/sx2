package sk.wlio.sx2.beans.reservedwords;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

public class DataType extends Slovo implements IVyraz {

    Enums.VyrazTyp typ = Enums.VyrazTyp.NIC;

    public DataType(Slovo slovo) {
        super(slovo);
    }


    public DataType(Pozicia pozicia, String obsah) {
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
