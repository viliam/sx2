package sk.wlio.sx2.beans.instruction;
// Date: 3.1.2011      Time: 23:57:35

import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Parameters extends SlovoAbstract {

    private Zatvorka z1;
    private Zatvorka z2;
    private List<Ciarka> ciarky = new ArrayList<Ciarka>();

    private List<IVyraz> parametre = new ArrayList<IVyraz>();

    public Parameters(Zatvorka z1, Zatvorka z2) {
        super(z1.getPozicia());
        this.z1 = z1;
        this.z2 = z2;
    }

    public Parameters(Zatvorka z1, Zatvorka z2, List<IVyraz> parametre, List<Ciarka> ciarky) {
        super(z1.getPozicia());
        this.z1 = z1;
        this.z2 = z2;
        this.parametre = parametre;
        this.ciarky = ciarky;
    }

    public List<Ciarka> getCiarky() {
        return ciarky;
    }

    public List<IVyraz> getParametre() {
        return parametre;
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public Zatvorka getZ2() {
        return z2;
    }

    public void visit(IVisitor visitor) {
        visitor.visit( this);
    }
}
