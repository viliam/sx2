package sk.wlio.sx2.visitors;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.DataValue;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.Instrukcia;

@Deprecated
public class VisitorAbstract implements IVisitor {

    public void visit(VyrazZlozeny vyrazZlozeny) {
        vyrazZlozeny.getV1().visit( this);
        vyrazZlozeny.getV2().visit( this);
    }

    public void visit(VyrazVzatvorke vyrazVzatvorke) {
        vyrazVzatvorke.getV().visit(this);
    }

    public void visit(Premenna premenna) {}

    public void visit(Command command) {
        command.getParameters().visit(this);
    }

    public void visit(Parameters parameters) {
        for (IVyraz vyraz : parameters.getParametre() )
            vyraz.visit( this);
    }

    public void visit(Operator operator) {}

    public void visit(DataType datovyTyp) {}
    public void visit(DataValue dataValue) {}

    public void visit(Cislo cislo) {}

    public void visit(Assignment assignment) {
        assignment.getVyraz().visit( this);
    }

    public void visit(Block block) {
        for (Instrukcia instrukcia : block.getInstrukcie())
            instrukcia.visit(this);
    }

    public void visit(Return aReturn) {
        aReturn.getVyraz().visit( this);
    }

    public void visit(DeclarationVariable dekPremennej) {
        if (dekPremennej.getAssignment() != null)
            dekPremennej.getAssignment().visit( this);
    }

    public void visit(DeclarationCommand dekPrikaz) {
        dekPrikaz.getDekParam().visit(this);
        dekPrikaz.getTelo().visit( this);
    }

    public void visit(DeclarationParameter dekParameter) {
        for (DeclarationVariable dekPremenna: dekParameter.getLiDekPremennej()) {
            dekPremenna.visit(this);
        }
    }
    public void visit(Program program) {
        //nacita deklaraciu vsetkych premmennych
        for (DeclarationVariable dekPremennej: program.getMapPremenna().values() ) {
            dekPremennej.visit(this);
        }

        //skontroluje deklaraciu prikazov, premennych
        for (DeclarationCommand dekPrikaz : program.getMapPrikaz().values())
            dekPrikaz.visit( this);
    }

    public void visit(ISlovo slovo) {
        throw new IllegalStateException("Nezadeklarovana visit metoda pre typ : " + slovo.getClass().getName());
    }
}
