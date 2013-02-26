package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.IVisitor;

public class Vrat extends SlovoAbstract implements Instrukcia{

    private Slovo zakazaneSlovo;
    private IVyraz hodnota;
    private Ciarka ciarka;

    public Vrat(Slovo zakazaneSlovo, IVyraz hodnota, Ciarka ciarka) {
        super(zakazaneSlovo.getPozicia());
        this.hodnota = hodnota;
        this.zakazaneSlovo = zakazaneSlovo;
        this.ciarka = ciarka;
    }

    public IVyraz getVyraz() {
        return hodnota;
    }

    public Slovo getZakazaneSlovo() {
        return zakazaneSlovo;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
