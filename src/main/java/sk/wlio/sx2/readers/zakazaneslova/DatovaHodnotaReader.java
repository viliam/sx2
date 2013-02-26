package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.DataValue;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class DatovaHodnotaReader extends RezervovaneSlovaAbstractReader<DataValue> {

    public DataValue citaj(TextContext tC)  {
        return new DataValue( super.citaj(tC) );
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_DATOVU_HODNOTU;
    }

    @Override
    protected RezervedWordsEnum getZakazaneSlova() {
        return RezervedWordsEnum.DATA_VALUE;
    }

}
