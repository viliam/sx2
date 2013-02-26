package sk.wlio.sx2.visitors;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instrukcia.Parametre;
import sk.wlio.sx2.beans.instrukcia.Prikaz;
import sk.wlio.sx2.beans.instrukcia.Priradenie;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.IVyraz;

@Deprecated
public class TypVyrazuVisitor extends VisitorAbstract {

//    protected final TextContext tC;

//    public TypVyrazuVisitor(TextContext tC) {
//        this.tC = tC;
//    }

    @Override
    public void visit(VyrazZlozeny vyrazZlozeny) {
        Enums.VyrazTyp ocakavanyTyp = vyrazZlozeny.getVyrazTyp();
        if ( Enums.VyrazTyp.POROVNANIE.equals(ocakavanyTyp))
            ocakavanyTyp = Enums.VyrazTyp.CISLO;

        IVyraz v1 = vyrazZlozeny.getV1();
        if ( !Enums.VyrazTyp.NEURCENY.equals(v1.getVyrazTyp()) && !v1.getVyrazTyp().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v1.getPozicia());

        IVyraz v2 = vyrazZlozeny.getV2();
        if ( !Enums.VyrazTyp.NEURCENY.equals(v2.getVyrazTyp()) && !v2.getVyrazTyp().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v2.getPozicia());

        v1.visit(this);
        v2.visit(this);
    }

    @Override
    public void visit(Priradenie priradenie) {
        Premenna premenna = priradenie.getPremenna();
        IVyraz vyraz = priradenie.getVyraz();
        if (premenna.getVyrazTyp() != vyraz.getVyrazTyp() )
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, vyraz.getPozicia());
    }


}
