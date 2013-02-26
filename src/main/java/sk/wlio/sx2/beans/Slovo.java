package sk.wlio.sx2.beans;

public class Slovo extends SlovoAbstract {

    private String obsah;

    public Slovo(Pozicia pozicia, String obsah) {
        super(pozicia);
        this.obsah = obsah;
    }

    public Slovo(Pozicia pozicia) {
        super(pozicia);
    }

    public Slovo(Slovo slovo) {
        this(slovo.getPozicia(),slovo.getObsah());
    }

    @Override
    public String toString() { return obsah;}

    public String getObsah() {
        return obsah;
    }
}


