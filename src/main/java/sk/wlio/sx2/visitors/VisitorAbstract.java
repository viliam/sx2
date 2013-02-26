package sk.wlio.sx2.visitors;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.beans.rezervovaneslova.DatovaHodnota;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
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

    public void visit(Prikaz prikaz) {
        prikaz.getParametre().visit(this);
    }

    public void visit(Parametre parametre) {
        for (IVyraz vyraz : parametre.getParametre() )
            vyraz.visit( this);
    }

    public void visit(Operator operator) {}

    public void visit(DatovyTyp datovyTyp) {}
    public void visit(DatovaHodnota datovaHodnota) {}

    public void visit(Cislo cislo) {}

    public void visit(Priradenie priradenie) {
        priradenie.getVyraz().visit( this);
    }

    public void visit(Blok blok) {
        for (Instrukcia instrukcia : blok.getInstrukcie())
            instrukcia.visit(this);
    }

    public void visit(Vrat vrat) {
        vrat.getVyraz().visit( this);
    }

    public void visit(DeklaraciaPremennej dekPremennej) {
        if (dekPremennej.getPriradenie() != null)
            dekPremennej.getPriradenie().visit( this);
    }

    public void visit(DeklaraciaPrikaz dekPrikaz) {
        dekPrikaz.getDekParam().visit(this);
        dekPrikaz.getTelo().visit( this);
    }

    public void visit(DeklaraciaParameter dekParameter) {
        for (DeklaraciaPremennej dekPremenna: dekParameter.getLiDekPremennej()) {
            dekPremenna.visit(this);
        }
    }
    public void visit(Program program) {
        //nacita deklaraciu vsetkych premmennych
        for (DeklaraciaPremennej dekPremennej: program.getMapPremenna().values() ) {
            dekPremennej.visit(this);
        }

        //skontroluje deklaraciu prikazov, premennych
        for (DeklaraciaPrikaz dekPrikaz : program.getMapPrikaz().values())
            dekPrikaz.visit( this);
    }

    public void visit(ISlovo slovo) {
        throw new IllegalStateException("Nezadeklarovana visit metoda pre typ : " + slovo.getClass().getName());
    }
}
