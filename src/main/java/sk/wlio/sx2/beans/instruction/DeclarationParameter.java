package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class DeclarationParameter implements ISlovo {

    Zatvorka z1, z2;

    List<DeclarationVariable> liDekPremennej = new ArrayList<DeclarationVariable>();
    List<Ciarka> liCiarka = new ArrayList<Ciarka>();

    public DeclarationParameter(Zatvorka z1, Zatvorka z2) {
        this.z1 = z1;
        this.z2 = z2;
    }

    public DeclarationParameter(Zatvorka z1, Zatvorka z2, List<Ciarka> liCiarka, List<DeclarationVariable> liDekPremennej) {
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

    public List<DeclarationVariable> getLiDekPremennej() {
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
