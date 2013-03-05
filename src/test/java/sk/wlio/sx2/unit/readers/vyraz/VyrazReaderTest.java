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

package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.expression.ExprReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;
import sk.wlio.sx2.unit.readers.mock.TestVyraz;


public class VyrazReaderTest extends AbstractReaderTest {

    @Test
    public void testVzatvorke()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override public void nastavReader() {
                mr.expBracket().setPosun(9,0);
            }
        }.run("( nieco ) , ", "expBracket;");
    }

    @Test
    public void testJednoduchyVyraz()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override
            public  void nastavReader() {
                mr.expSimple().setPosun( 5,0);
            }

        }.run("nazov ", "expSimple;");
    }


    @Test
    public void testZlozeny()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override public void nastavReader() {
                mr.expSimple().setPosun( 3,0);
                mr.expSimple().setVystup(new TestVyraz());
                mr.opExpr().setPosun(2,0);
                mr.expression().setPosun(4,0);
            }

        }.run("ano > nie", "expSimple;opExp;expression;");
    }



}
