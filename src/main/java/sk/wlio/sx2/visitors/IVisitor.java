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

public interface IVisitor {
    void visit(VyrazZlozeny vyrazZlozeny);
    void visit(VyrazVzatvorke vyrazVzatvorke);

    void visit(Premenna premenna);
    void visit(Prikaz prikaz);
    void visit(Parametre parametre);

    void visit(Operator operator);
    void visit(DatovyTyp datovyTyp);

    void visit(DatovaHodnota datovaHodnota);
    void visit(Cislo cislo);

    void visit(Priradenie priradenie);
    void visit(Blok blok);
    void visit(Vrat vrat);

    void visit(DeklaraciaPremennej dekPremennej);
    void visit(DeklaraciaPrikaz dekPrikaz);
    void visit(DeklaraciaParameter dekParameter);
    void visit(Program program);

    public void visit(ISlovo slovo);
}

