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

package sk.wlio.sx2.integra;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.parsers.symbol.BracketParser;
import sk.wlio.sx2.parsers.symbol.OperatorExpressionParser;
import sk.wlio.sx2.parsers.expression.*;

import static org.testng.AssertJUnit.*;

public class SxExceptionTest {

    private void testException(TextContext tC, SxParser tR, SxExTyp typOcakavany) {
        try {
            tR.read(tC);
            fail();
        } catch (SxException e) {
            assertEquals(typOcakavany, e.getType());
        }
    }

    @Test
    public void testEndOfFile()  {
        TextContext tC = new TextContext(" 123");
        SxParser<Int> cR = new IntReader();
        cR.read(tC);
        testException( tC, cR, SxExTyp.EMPTY_WORD);
    }

    @Test
    public void testUnExpectedPrefix() {
        TextContext tC = new TextContext(" +123");
        SxParser<IExpression> vJr = new SimpleExprReader();
        testException(tC, vJr, SxExTyp.UNEXPECTED_PREFIX );
    }


    @Test
    public void testUnknowOperator() {
        TextContext tC = new TextContext(" c3");
        SxParser oAr = new OperatorExpressionParser();
        testException(tC, oAr , SxExTyp.EXPECTED_OPERATOR);
    }

    @Test
    public void testCakalZatvorku() {
        TextContext tC = new TextContext(" c3");
        SxParser<Bracket> zR = new BracketParser();
        testException(tC, zR , SxExTyp.EXPECTED_BRACKET);
    }

}
