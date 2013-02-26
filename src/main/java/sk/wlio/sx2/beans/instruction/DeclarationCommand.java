package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.visitors.IVisitor;

public class DeclarationCommand extends SlovoAbstract {

    private DataType datovyTyp;
    private Slovo nazov;
    private DeclarationParameter dekParam;
    private Block telo;

    public DeclarationCommand(DataType datovyTyp, Slovo nazov, DeclarationParameter dekParam, Block telo) {
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

    public DeclarationParameter getDekParam() {
        return dekParam;
    }

    public void setDekParam(DeclarationParameter dekParam) {
        this.dekParam = dekParam;
    }

    public Block getTelo() {
        return telo;
    }

    public void setTelo(Block telo) {
        this.telo = telo;
    }

    public DataType getDatovyTyp() {
        return datovyTyp;
    }

    public void setDatovyTyp(DataType datovyTyp) {
        this.datovyTyp = datovyTyp;
    }
}
