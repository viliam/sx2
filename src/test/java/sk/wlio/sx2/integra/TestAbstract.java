package sk.wlio.sx2.integra;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.rozhrania.TextReader;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public abstract class TestAbstract<T extends ISlovo> {

    protected static interface TestVisitor<T> {
        void visit(TextContext tC, T slovo);
    }

    final protected TextReader<T> reader;
    final protected TestVisitor<T> testVisitor;

    protected TestAbstract(TextReader<T> reader) {
        this(reader, new TestVisitor<T>() {
            public void visit(TextContext tC, T slovo) {}
        });
    }

    protected TestAbstract(TextReader<T> reader, TestVisitor<T> testVisitor) {
        this.reader = reader;
        this.testVisitor = testVisitor;
    }

    protected abstract String[] getDobreVety();
    protected abstract String[] getChybneVety();

    @Test
    public void testOk() {
        for (String v: getDobreVety()) {
            System.out.println("testovane slovo OK : " + v);
            TextContext tC = new TextContext(v);
            T veta= spracuj(tC);
            assertNotNull( veta);

            testVisitor.visit( tC, veta);
        }
    }

    @Test
    public void testFail() {
        for (String v : getChybneVety()) {
            System.out.println("testovane slovo Fail : " + v);
            try  {
                TextContext tC = new TextContext(v);
                T slovo = spracuj(tC);

                testVisitor.visit(tC, slovo);

                fail(v);
                //if (tC.jeKoniec() ) fail("");
            } catch (SxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private T spracuj( TextContext tC)  {
        T slovo = reader.citaj(tC);
        assertNotNull(slovo);
        return slovo;
    }
}