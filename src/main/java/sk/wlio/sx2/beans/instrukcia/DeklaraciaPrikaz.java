package sk.wlio.sx2.beans.instrukcia;

import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.visitors.IVisitor;

public class DeklaraciaPrikaz extends SlovoAbstract {

    private DatovyTyp datovyTyp;
    private Slovo nazov;
    private DeklaraciaParameter dekParam;
    private Blok telo;

    public DeklaraciaPrikaz(DatovyTyp datovyTyp, Slovo nazov, DeklaraciaParameter dekParam, Blok telo) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.dekParam = dekParam;
        this.telo = telo;
        this.datovyTyp = datovyTyp;
    }

    public Slovo getNazov() {
        return nazov;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public void setNazov(Slovo nazov) {
        this.nazov = nazov;
    }

    public DeklaraciaParameter getDekParam() {
        return dekParam;
    }

    public void setDekParam(DeklaraciaParameter dekParam) {
        this.dekParam = dekParam;
    }

    public Blok getTelo() {
        return telo;
    }

    public void setTelo(Blok telo) {
        this.telo = telo;
    }

    public DatovyTyp getDatovyTyp() {
        return datovyTyp;
    }

    public void setDatovyTyp(DatovyTyp datovyTyp) {
        this.datovyTyp = datovyTyp;
    }
}
