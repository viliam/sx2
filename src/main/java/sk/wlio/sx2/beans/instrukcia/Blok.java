package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.DeklaracieVisitor;
import sk.wlio.sx2.visitors.IVisitor;

public class Blok extends SlovoAbstract implements Instrukcia {

    private Instrukcia[] instrukcie;
    private Zatvorka z1;
    private Zatvorka z2;

    public Blok(Instrukcia[] instrukcie , Zatvorka z1, Zatvorka z2) {
        super(z1.getPozicia());
        this.z1 = z1;
        this.z2 = z2;
        this.instrukcie = instrukcie;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Instrukcia[] getInstrukcie() {
        return instrukcie;
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public Zatvorka getZ2() {
        return z2;
    }

}
