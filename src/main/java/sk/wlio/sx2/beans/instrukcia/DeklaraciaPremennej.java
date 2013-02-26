package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.IVisitor;

public class DeklaraciaPremennej extends SlovoAbstract implements Instrukcia {

    private DatovyTyp datovyTyp;
    private Slovo nazov;
    private Priradenie priradenie;
    private Ciarka ciarka;

    public  DeklaraciaPremennej(DatovyTyp datovyTyp, Slovo nazov, Ciarka ciarka ) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.datovyTyp = datovyTyp;
        this.ciarka = ciarka;
    }

    public  DeklaraciaPremennej( DatovyTyp datovyTyp, Slovo nazov, Priradenie priradenie) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.priradenie = priradenie;
        this.datovyTyp = datovyTyp;
    }

    public DatovyTyp getDatovyTyp() {
        return datovyTyp;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Priradenie getPriradenie() {
        return priradenie;
    }

}
