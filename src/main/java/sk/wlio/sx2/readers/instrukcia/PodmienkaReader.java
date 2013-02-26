package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Condition;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class PodmienkaReader implements TextReader<Condition> {

    public Condition citaj(TextContext tC)        {
        InstrukciaSlovo akSlovo = Readers.instrukciaSlovo().citaj(tC);
        if ( !RezervovaneSlovoEnum.AK.je( akSlovo.toString())  )
            throw SxException.create( SxExTyp.CAKAL_AK, tC);


        Zatvorka z1 = Readers.zatvorka().citaj(tC);
        IVyraz vrzBool = Readers.vrzBool().citaj(tC);
        Zatvorka z2 = Readers.zatvorka().citaj(tC);
        Instrukcia instrukcia = Readers.instrukcia().citaj(tC);
        return new Condition( akSlovo, vrzBool, instrukcia, z1, z2 );
    }
}
