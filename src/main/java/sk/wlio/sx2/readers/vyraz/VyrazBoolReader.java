package sk.wlio.sx2.readers.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.vyraz.VyrazAritm;
import sk.wlio.sx2.beans.vyraz.VyrazBool;
import sk.wlio.sx2.beans.vyraz.VyrazPorovnanie;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazBoolReader extends VyrazAbstractReader implements TextReader<IVyraz> {


    @Override
    protected void skontrolujTyp(Enums.VyrazTyp vyrazTyp, TextContext tC) {
        if (! Enums.VyrazTyp.BOOL.equals( vyrazTyp) &&
            ! Enums.VyrazTyp.NEURCENY.equals( vyrazTyp)  )
            throw SxException.create(SxExTyp.CAKAL_BOOL,tC);
    }

    @Override
    protected IVyraz citajZlozenyVyraz(TextContext tC, IVyraz o1) {
        if ( tC.jePrefixOperatorBool() )  {
            Operator op = Readers.opBool().citaj( tC);
            IVyraz v2 = citaj(tC);
            return new VyrazBool( o1,op,v2);
        }
        return null;
    }

    @Override
    protected IVyraz citajPrvyVyraz(TextContext tC) {
        if ( tC.jePrefixZatvorkaOtovorena() )  {
            return Readers.vrzVzatvorke().citaj(tC);
        }

        //nacitam jednoduchy vyraz
        IVyraz v = Readers.vrzJednduchy().citaj(tC);

        //nasledujuci operator rozhodne
        if ( tC.jePrefixOperatorAritm() ) {
            //TODO: netreba skontrolovat typ prveho vyrazu?
            Operator op = Readers.opAritm().citaj( tC);
            v = new VyrazAritm(v, op, Readers.vrzAritm().citaj( tC));

        }
        boolean jePorovnanie = !tC.jeKoniec() && tC.jePrefixOperatorPorovnania();

        //ak nie je typ cislo, co moze byt napr. premenna, na zaklade nasledujuceho
        // operatoru sa vieme rozhodnut
        if (jePorovnanie)
            return new VyrazPorovnanie(v,
                    Readers.opPorovnanie().citaj(tC),
                    Readers.vrzAritm().citaj(tC));

        if (v.getVyrazTyp() == Enums.VyrazTyp.CISLO)
            throw SxException.create(SxExTyp.CAKAL_OPERATOR_POROVNANIA,tC);

        return v;
    }

//    public IVyraz citaj(TextContext tC)  {
//        IVyraz v1;
//
//        if ( SymbolEnum.jeZatvorkaOtovorena( tC.getNasledujuciZnak()) )  {    //ak zacina zatvorkou, -> rekurzivne nacita obsah zatvorky
//            //FIXME: moze sa zacinat aj artimetickym vyrazom
//            v1 = Readers.vrzBoolVzatvorke().citaj(tC);
//        }  else {
//            //nacitam aritmeticky vyraz  //fixme: moze byt aj premenna alebo prikaz
//            IVyraz v = Readers.vrzAritm().citaj(tC);
//            //ak je typ cislo, musi ist o porovnanie
//            SymbolReader<Operator> oPorovnanieReader = Readers.opPorovnanie();
//            boolean jePorovnanie = !tC.jeKoniec() && oPorovnanieReader.jePrfx( tC.vratKoniecRiadka());
//
//            //ak nie je typ cislo, co moze byt napr. premenna, na zaklade nasledujuceho
//            // operatoru sa vieme rozhodnut
//            if (jePorovnanie)
//                v1 = new VyrazPorovnanie(v,
//                        oPorovnanieReader.citaj(tC),
//                        Readers.vrzAritm().citaj(tC));
//            else {
//                if (v.getVyrazTyp() == Enums.VyrazTyp.CISLO)
//                    throw SxException.create(SxExTyp.CAKAL_OPERATOR_POROVNANIA,tC);
//
//                v1 = v;
//            }
//        }

//        if (tC.jeKoniec() )
//            return v1;

//        SymbolReader<Operator> oReader = Readers.opBool();
//        if (oReader.jePrfx( tC.vratKoniecRiadka()))  {
//            Operator op = oReader.citaj( tC);
//            IVyraz v2 = citaj(tC);
//            return new VyrazBool( v1,op,v2);
//        }
//
//        if ( jeKorektnyKoniecVyrazu( tC.getNasledujuciZnak()) )
//            return v1;
//
//        throw SxException.create(SxExTyp.CAKAL_OPERATOR, "Bool" ,tC);
//    }

}
