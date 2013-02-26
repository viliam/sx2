package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.IVisitor;

public class DeclarationVariable extends SlovoAbstract implements Instrukcia {

    private DataType datovyTyp;
    private Slovo nazov;
    private Assignment assignment;
    private Ciarka ciarka;

    public DeclarationVariable(DataType datovyTyp, Slovo nazov, Ciarka ciarka) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.datovyTyp = datovyTyp;
        this.ciarka = ciarka;
    }

    public DeclarationVariable(DataType datovyTyp, Slovo nazov, Assignment assignment) {
        super(datovyTyp.getPozicia());
        this.nazov = nazov;
        this.assignment = assignment;
        this.datovyTyp = datovyTyp;
    }

    public DataType getDatovyTyp() {
        return datovyTyp;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

    public Slovo getNazov() {
        return nazov;
    }

    public Assignment getAssignment() {
        return assignment;
    }

}
