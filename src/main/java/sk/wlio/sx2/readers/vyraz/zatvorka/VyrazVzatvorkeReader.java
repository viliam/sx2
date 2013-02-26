package sk.wlio.sx2.readers.vyraz.zatvorka;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.ZatvorkaReader;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazVzatvorkeReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        Zatvorka z1 = Readers.zatvorka().citaj(tC);
        IVyraz vyraz= Readers.vyraz().citaj(tC);
        Zatvorka z2 = Readers.zatvorka().citaj(tC);
        return new VyrazVzatvorke(z1, vyraz, z2);
    }

}
