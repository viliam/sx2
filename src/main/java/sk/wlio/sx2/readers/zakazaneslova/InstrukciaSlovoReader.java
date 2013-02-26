package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class InstrukciaSlovoReader extends RezervovaneSlovaAbstractReader<InstructionWord> {

    public InstructionWord citaj(TextContext tC)  {
        return new InstructionWord( super.citaj(tC) );
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_INSTRUKCIA_SLOVO;
    }

    @Override
    protected RezervedWordsEnum getZakazaneSlova() {
        return RezervedWordsEnum.INSTRUCTION_WORD;
    }

}
