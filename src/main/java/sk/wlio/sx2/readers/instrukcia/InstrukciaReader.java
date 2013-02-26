package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.*;

public class InstrukciaReader implements TextReader<Instrukcia> {

    public Instrukcia citaj(TextContext tC)  {

        if (tC.jePrefixDatovyTyp())
            return Readers.dekPremennej().citaj(tC);

        if (tC.jePrefixPremenna())
            return Readers.priradenie().citaj(tC);

        if (tC.jePrefixInstrukcia() ) {
            switch ( tC.vratPrefixZakazaneSlovo()) {
                case RETURN:
                    return Readers.vrat().citaj(tC);
//                    case ZakazaneSlova.AK : {
//                        Podmienka pod = odkusniPodmienku();
//                        //pod.pridajBodkoCiarku(odkusniCiarku(';'));
//                        //kedze podmienka je viazana na blok
//                        return pod;
//                    }
               }
//                //3. treba dorobit (case, while, for,..)
            }

//            case Prefix.SLOVO_PRIKAZ: {
//                PrikazLink pri = odkusniPrikazLink();
//                pri.pridajBodkoCiarku(odkusniCiarku(';'));
//                return pri;
//            }


        throw SxException.create(SxExTyp.CAKAL_INSTRUKCIU,tC);
    }

}
