package sk.wlio.sx2.beans.rezervovaneslova;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;

public class InstrukciaSlovo extends Slovo {

    public InstrukciaSlovo(Slovo slovo) {
        super(slovo);
    }

    public InstrukciaSlovo(Pozicia pozicia, String obsah) {
        super(pozicia, obsah);
    }
}
