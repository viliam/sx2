package sk.wlio.sx2.unit.readers.znak;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.symbol.SymbolAbstractReader;
import static org.testng.AssertJUnit.*;

import java.lang.reflect.Method;

public class SymbolAbstractReaderTest {

    class TestSymbolAbstractReader extends SymbolAbstractReader<Slovo> {

        @Override
        protected String[] getSymbols() {
            return new String[] { "ano", "nie"};
        }

        @Override
        protected Slovo create(Pozicia pozicia, SymbolEnum oEnum)  {
            return new Slovo( new Pozicia(0,0), "test");
        }

        @Override
        protected SxExTyp getExceptionTyp() {
            return SxExTyp.CAKAL_AK;
        }
    }
    
    @Test
    public void testOdkusniSymbol()  {
        TextContext tC = new TextContext("   ano ja som");
        TestSymbolAbstractReader symbolReader = new TestSymbolAbstractReader();
        String ano = invokeOdkusniSymbol(symbolReader, tC);
        assertNotNull(ano);
        assertEquals("ano", ano);
        assertEquals(new Pozicia(6,0), tC.getPozicia());
    }

    @Test
    public void testOdkusniSymbolBad() {
        TextContext tC = new TextContext("   nano ja som");
        TestSymbolAbstractReader symbolReader = new TestSymbolAbstractReader();
        try {
            invokeOdkusniSymbol(symbolReader, tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.CAKAL_AK, e.getTyp());
        }

    }
    
    @Test
    public void testSymbolReader()  {
        TextContext tC = new TextContext("   ano ja som");
        TestSymbolAbstractReader symbolReader = new TestSymbolAbstractReader();
        Slovo symbol = symbolReader.citaj(tC);
        assertNotNull(symbol);        
        assertEquals("test", symbol.toString());
    }
    
    private String invokeOdkusniSymbol(TestSymbolAbstractReader symbolReader, TextContext tC)  {
        try {
            Class testClass = SymbolAbstractReader.class;
            Method testMetoda = testClass.getDeclaredMethod("odkusniSymbol", TextContext.class);
            testMetoda.setAccessible( true);
            return (String) testMetoda.invoke(symbolReader, tC);
        } catch (Exception e) {
            if (e.getCause() != null
                && SxException.class.equals( e.getCause().getClass() ))
                throw (SxException) e.getCause();
            throw new RuntimeException(e);
        }
    }
}
