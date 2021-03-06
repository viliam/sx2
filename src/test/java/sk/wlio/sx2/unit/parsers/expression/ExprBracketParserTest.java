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
import sk.wlio.sx2.parsers.expression.BracketExpression;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class ExprBracketParserTest extends AbstractParserTest {

    @Test
    public void testBasic()  {
        new TestTemplate<IExpression>(sb, new BracketExpression()) {
            @Override
            public void setUpParsers() {
                mr.bracket().setShift(1, 0, 1, 0);
                   mr.expression().setShift(4, 0);
            }
        }.run("(ahoj)", "bracket;expression;bracket;");
    }

}
