package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class InstrukciaSlovoReader extends RezervovaneSlovaAbstractReader<InstrukciaSlovo> {

    public InstrukciaSlovo citaj(TextContext tC)  {
        return new InstrukciaSlovo( super.citaj(tC) );
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_INSTRUKCIA_SLOVO;
    }

    @Override
    protected RezervovaneSlovaEnum getZakazaneSlova() {
        return RezervovaneSlovaEnum.INSTRUKCIA_SLOVO;
    }

}
