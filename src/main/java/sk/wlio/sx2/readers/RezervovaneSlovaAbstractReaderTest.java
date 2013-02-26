package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
import sk.wlio.sx2.exception.SxExTyp;

public class RezervovaneSlovaAbstractReaderTest {

    class TestTriedaReader extends RezervovaneSlovaAbstractReader {

        @Override
        protected SxExTyp getSxExceptionTyp() {
            return SxExTyp.ZLY_DATOVY_TYP;
        }

        @Override
        protected RezervovaneSlovaEnum getZakazaneSlova() {
//            return new TestZakazaneSlova();
            return null;
        }
    }

    class TestZakazaneSlova {
        public boolean je(String s) {
            return true;
        }

        public RezervovaneSlovoEnum vrat(String s) {
            return RezervovaneSlovoEnum.AK;
        }
    }
}
