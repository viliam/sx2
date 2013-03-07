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

package sk.wlio.sx2.integra.expression;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.expression.ExprReader;

import static org.testng.AssertJUnit.*;

public class ExpressionParserTest {

    public final static String[] expression = new String[] {
            "4+3",
            "4 > 3+3",
            "5 <6 | 4 > 3+3",
            "3+4 > 9-3 || t4 ==4"  ,
            " ( 4 == 3 | 5 != 3 ) && p + 3 != 4 "
    };

    public final static String[] wrongExpressoin = new String[] {
        //" ( 4 == 3 | 5 != 3 ) + 5 >5 && p + 3 != 4 ",
        "5 <6 | 4 > 3+3 +"
    };

    @Test
    public void testExpression()  {
        for (String v: expression) {
            IExpression expression = readExpression(v);
            assertNotNull(expression);
        }
    }


    @Test
    public void testFail() {
        for (String v : wrongExpressoin)
            try {
                TextContext tC = new TextContext(v);
                readExpression(tC, false);
                if (tC.isEndOfFile() ) fail(v);
            } catch (SxException ex) {}
    }

    private IExpression readExpression(TextContext tC, boolean checkKoniec)  {
        IExpression expression = new ExprReader().read(tC);
        assertNotNull(expression);

        if (checkKoniec) assertTrue( tC.isEndOfFile());
        return expression;
    }

    private IExpression readExpression(String tento)  {
        TextContext tC = new TextContext(tento);
        return readExpression(tC, true);
    }

}
