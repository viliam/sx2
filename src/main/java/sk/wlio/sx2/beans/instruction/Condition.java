package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.SlovoAbstract;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.IVyraz;

public class Condition extends SlovoAbstract {

    private final InstructionWord ak;
    private final Zatvorka z1, z2;
    private final IVyraz vrzBool;
    private final Instrukcia instrukcia;

    public Condition(InstructionWord ak, IVyraz vrzBool, Instrukcia instrukcia, Zatvorka z1, Zatvorka z2) {
        super(ak.getPozicia());
        this.ak = ak;
        this.vrzBool = vrzBool;
        this.instrukcia = instrukcia;
        this.z1 = z1;
        this.z2 = z2;
    }

    public InstructionWord getAk() {
        return ak;
    }

    public Instrukcia getInstrukcia() {
        return instrukcia;
    }

    public Zatvorka getZ1() {
        return z1;
    }

    public Zatvorka getZ2() {
        return z2;
    }
    public IVyraz getVrzBool() {
        return vrzBool;
    }
}
