package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.visitors.DeklaracieVisitor;
import sk.wlio.sx2.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class DeklaraciaParameter implements ISlovo {

    Zatvorka z1, z2;

    List<DeklaraciaPremennej> liDekPremennej = new ArrayList<DeklaraciaPremennej>();
    List<Ciarka> liCiarka = new ArrayList<Ciarka>();

    public DeklaraciaParameter(Zatvorka z1, Zatvorka z2) {
        this.z1 = z1;
        this.z2 = z2;
    }

    public DeklaraciaParameter(Zatvorka z1, Zatvorka z2, List<Ciarka> liCiarka, List<DeklaraciaPremennej> liDekPremennej) {
        this.liCiarka = liCiarka;
        this.liDekPremennej = liDekPremennej;
        this.z1 = z1;
        this.z2 = z2;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public List<Ciarka> getLiCiarka() {
        return liCiarka;
    }

    public List<DeklaraciaPremennej> getLiDekPremennej() {
        return liDekPremennej;
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public Zatvorka getZ2() {
        return z2;
    }

    public Pozicia getPozicia() {
        return z1.getPozicia();
    }

}
