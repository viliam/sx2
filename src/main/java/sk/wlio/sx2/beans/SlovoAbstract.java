package sk.wlio.sx2.beans;

import sk.wlio.sx2.rozhrania.ISlovo;

public abstract class SlovoAbstract implements ISlovo {

    private final Pozicia pozicia;

    public SlovoAbstract(Pozicia pozicia) {
        this.pozicia = pozicia;
    }

    public Pozicia getPozicia() {
        return pozicia;
    }
}
