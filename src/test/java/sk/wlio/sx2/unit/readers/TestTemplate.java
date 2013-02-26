package sk.wlio.sx2.unit.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.rozhrania.TextReader;

import static org.testng.AssertJUnit.assertEquals;

public abstract class TestTemplate<T extends ISlovo> {

    final StringBuffer sb;
    private T result;
    private TextReader<T> textReader;

    protected TestTemplate(StringBuffer sb, TextReader<T> textReader) {
        this.sb = sb;
        this.textReader = textReader;
    }

    public TextContext run(String vyraz, String postupnost )  {
        TextContext tC = new TextContext( vyraz);
        nastavReader();
        result = textReader.citaj(tC);
        assertEquals(postupnost, sb.toString() );
        return tC;
    }

    public abstract void nastavReader();

    public T getVysledok() {
        return result;
    }
}
