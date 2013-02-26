package sk.wlio.sx2.readers.vyraz.zatvorka;

import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazAritmVzatvorkeReader extends VyrazVzatvorkeAbstractReader {

    @Override
    protected TextReader<IVyraz> getVyrazReader() {
        return Readers.vrzAritm();
    }

}
