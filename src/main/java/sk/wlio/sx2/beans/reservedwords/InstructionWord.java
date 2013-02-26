package sk.wlio.sx2.beans.reservedwords;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;

public class InstructionWord extends Slovo {

    public InstructionWord(Slovo slovo) {
        super(slovo);
    }

    public InstructionWord(Pozicia pozicia, String obsah) {
        super(pozicia, obsah);
    }
}
