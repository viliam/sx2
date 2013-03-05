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

package sk.wlio.sx2.unit.parsers.expression;

import org.testng.annotations.Test;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.expression.ExprReader;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;
import sk.wlio.sx2.unit.parsers.mock.TestVyraz;


public class ExpressionParserTest extends AbstractParserTest {

    @Test
    public void testExprBracket()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override public void setUpParsers() {
                mr.exprBracket().setShift(9, 0);
            }
        }.run("( nieco ) , ", "exprBracket;");
    }

    @Test
    public void testExprSimple()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override
            public  void setUpParsers() {
                mr.exprSimple().setShift(5, 0);
            }

        }.run("nazov ", "exprSimple;");
    }


    @Test
    public void testExpression()  {
        new TestTemplate<IExpression>(sb, new ExprReader()) {
            @Override public void setUpParsers() {
                mr.exprSimple().setShift(3, 0);
                mr.exprSimple().setOutput(new TestVyraz());
                mr.opExpr().setShift(2, 0);
                mr.expression().setShift(4, 0);
            }

        }.run("ano > nie", "exprSimple;opExp;expression;");
    }



}
