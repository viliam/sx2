package sk.wlio.sx2.integra.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.vyraz.VyrazReader;
import sk.wlio.sx2.rozhrania.IVyraz;

import static org.testng.AssertJUnit.*;

public class  VyrazReaderTest {

    public final static String[] zloziteVyrazy = new String[] {
            "4+3",
            "4 > 3+3",
            "5 <6 | 4 > 3+3",
            "3+4 > 9-3 || t4 ==4"  ,
            " ( 4 == 3 | 5 != 3 ) && p + 3 != 4 "
    };

    public final static String[] chybneVyrazy = new String[] {
        //" ( 4 == 3 | 5 != 3 ) + 5 >5 && p + 3 != 4 ",
        "5 <6 | 4 > 3+3 +"
    };

    @Test
    public void testZloziteVyrazy()  {
        for (String v: zloziteVyrazy) {
            IVyraz vyraz = citajVyraz(v);
            assertNotNull( vyraz);
        }
    }


    @Test
    public void testFail() {
        for (String v : chybneVyrazy)
            try {
                TextContext tC = new TextContext(v);
                citajVyraz( tC, false);
                if (tC.jeKoniec() ) fail(v);
            } catch (SxException ex) {}
    }

    private IVyraz citajVyraz(TextContext tC, boolean checkKoniec)  {
        IVyraz vyraz = new VyrazReader().citaj(tC);
        assertNotNull(vyraz);

        if (checkKoniec) assertTrue( tC.jeKoniec());
        return vyraz;
    }

    private IVyraz citajVyraz(String tento)  {
        TextContext tC = new TextContext(tento);
        return citajVyraz(tC, true);
    }

}
