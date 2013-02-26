package sk.wlio.sx2.unit.readers;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.OperatorPorovnanieReader;

import static org.testng.AssertJUnit.assertEquals;

public class OperatorReaderTest {

    @Test
    public void testOperatorAritm()  {
        TextContext tC= new TextContext("  + ");
        Operator operator = Readers.opAritm().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Pozicia(2,0), operator.getPozicia() );
        assertEquals( "Cislo kontrola ", "+", operator.getSymbol() );
        assertEquals( "Posunuty inx", new Pozicia(3,0), tC.getPozicia() );
    }

    @Test
    public void testOperatorBoolPorovnanie()  {
        TextContext tC= new TextContext("  <= ");
        Operator operator = new OperatorPorovnanieReader().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Pozicia(2,0), operator.getPozicia() );
        assertEquals( "Cislo kontrola ", "<=", operator.getSymbol() );
        assertEquals( "Posunuty inx", new Pozicia(4,0), tC.getPozicia() );
    }

}
