package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.DatovaHodnota;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class DatovaHodnotaReader extends RezervovaneSlovaAbstractReader<DatovaHodnota> {

    public DatovaHodnota citaj(TextContext tC)  {
        return new DatovaHodnota( super.citaj(tC) );
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_DATOVU_HODNOTU;
    }

    @Override
    protected RezervovaneSlovaEnum getZakazaneSlova() {
        return RezervovaneSlovaEnum.DATOVA_HODNOTA;
    }

}
