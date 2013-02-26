package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class DatovyTypReader extends RezervovaneSlovaAbstractReader<DatovyTyp> {

    public DatovyTyp citaj(TextContext tC)  {
        DatovyTyp dt = new DatovyTyp( super.citaj(tC) );
        dt.setTyp( Enums.VyrazTyp.getDatovyTyp( dt));

        return dt;
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_DATOVY_TYP;
    }

    @Override
    protected RezervovaneSlovaEnum getZakazaneSlova() {
        return RezervovaneSlovaEnum.DATOVY_TYP;
    }
}
