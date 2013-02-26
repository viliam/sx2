package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class DatovyTypReader extends RezervovaneSlovaAbstractReader<DataType> {

    public DataType citaj(TextContext tC)  {
        DataType dt = new DataType( super.citaj(tC) );
        dt.setTyp( Enums.VyrazTyp.getDatovyTyp( dt));

        return dt;
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_DATOVY_TYP;
    }

    @Override
    protected RezervedWordsEnum getZakazaneSlova() {
        return RezervedWordsEnum.DATA_TYPE;
    }
}
