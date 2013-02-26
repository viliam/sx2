package sk.wlio.sx2.unit;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextUtils;

import static org.testng.AssertJUnit.assertEquals;

public class TextUtilsTest {

    @Test
    public void testNajdiNasledujuciZnakVriadku() {
        int ns = TextUtils.posunNasledujuciZnakVriadku(" 1234 56", 3);
        assertEquals("nasledujuci znak", 3, ns);
        ns = TextUtils.posunNasledujuciZnakVriadku(" 12   34 56", 3);
        assertEquals("nasledujuci znak", 6, ns);
        ns = TextUtils.posunNasledujuciZnakVriadku(" 12   34 56", 3);
        assertEquals("nasledujuci znak", 6, ns);
    }

    @Test
    public void testNajdiKoniecSlova() {
        //prekrocenie
        assertEquals(4 , TextUtils.najdiKoniecSlova("aa", 4));
        //konci pismenom
        assertEquals(2 , TextUtils.najdiKoniecSlova("aa 2", 0));
        //konci cislom
        assertEquals(2 , TextUtils.najdiKoniecSlova("a2 2", 0));

    }
}
