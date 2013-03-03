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

package sk.wlio.sx2.integra.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.expression.ExprReader;

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
            IExpression expression = citajVyraz(v);
            assertNotNull(expression);
        }
    }


    @Test
    public void testFail() {
        for (String v : chybneVyrazy)
            try {
                TextContext tC = new TextContext(v);
                citajVyraz( tC, false);
                if (tC.isEndOfFile() ) fail(v);
            } catch (SxException ex) {}
    }

    private IExpression citajVyraz(TextContext tC, boolean checkKoniec)  {
        IExpression expression = new ExprReader().read(tC);
        assertNotNull(expression);

        if (checkKoniec) assertTrue( tC.isEndOfFile());
        return expression;
    }

    private IExpression citajVyraz(String tento)  {
        TextContext tC = new TextContext(tento);
        return citajVyraz(tC, true);
    }

}
