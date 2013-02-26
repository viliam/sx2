package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;

public class RezervovaneSlovaAbstractReaderTest {

    class TestTriedaReader extends RezervovaneSlovaAbstractReader {

        @Override
        protected SxExTyp getSxExceptionTyp() {
            return SxExTyp.ZLY_DATOVY_TYP;
        }

        @Override
        protected RezervedWordsEnum getZakazaneSlova() {
//            return new TestZakazaneSlova();
            return null;
        }
    }

    class TestZakazaneSlova {
        public boolean je(String s) {
            return true;
        }

        public ReservedWordEnum vrat(String s) {
            return ReservedWordEnum.IF;
        }
    }
}
