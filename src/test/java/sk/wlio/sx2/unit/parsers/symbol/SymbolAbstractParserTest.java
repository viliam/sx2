/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.wlio.sx2.unit.parsers.symbol;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.parsers.symbol.SymbolAbstractParser;

import static org.testng.AssertJUnit.*;

import java.lang.reflect.Method;

public class SymbolAbstractParserTest {

    class TestSymbolAbstractParser extends SymbolAbstractParser<Word> {

        @Override
        protected String[] getSymbols() {
            return new String[] { "ano", "nie"};
        }

        @Override
        protected Word create(Position position, SymbolEnum oEnum)  {
            return new Word( new Position(0,0), "test");
        }

        @Override
        protected SxExTyp getExceptionType() {
            return SxExTyp.EXPECTED_IF;
        }
    }

    @Test
    public void testInvokeSymbol()  {
        TextContext tC = new TextContext("   ano ja som");
        TestSymbolAbstractParser symbolReader = new TestSymbolAbstractParser();
        String ano = invokeTakeSymbol(symbolReader, tC);
        assertNotNull(ano);
        assertEquals("ano", ano);
        assertEquals(new Position(6,0), tC.getPosition());
    }

    @Test
    public void testFail() {
        TextContext tC = new TextContext("   nano ja som");
        TestSymbolAbstractParser symbolReader = new TestSymbolAbstractParser();
        try {
            invokeTakeSymbol(symbolReader, tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.EXPECTED_IF, e.getType());
        }

    }

    @Test
    public void testBasic()  {
        TextContext tC = new TextContext("   ano ja som");
        TestSymbolAbstractParser symbolReader = new TestSymbolAbstractParser();
        Word symbol = symbolReader.read(tC);
        assertNotNull(symbol);        
        assertEquals("test", symbol.toString());
    }
    
    private String invokeTakeSymbol(TestSymbolAbstractParser symbolReader, TextContext tC)  {
        try {
            Class testClass = SymbolAbstractParser.class;
            Method testMetoda = testClass.getDeclaredMethod("takeSymbol", TextContext.class);
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
