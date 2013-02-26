package sk.wlio.sx2.rozhrania;

import sk.wlio.sx2.beans.Pozicia;

public interface ISlovo {

    //vrati poziciu slova v ramci kontext
    public Pozicia getPozicia();

    public String toString();
}
