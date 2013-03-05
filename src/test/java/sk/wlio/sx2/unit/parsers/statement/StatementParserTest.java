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

package sk.wlio.sx2.unit.parsers.statement;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.statement.StatementReader;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

public class StatementParserTest extends AbstractParserTest {

    @Test
    public void testAssignment()  {
        new TestTemplate<Statement>(sb, new StatementReader()) {
            @Override
            public void setUpParsers() {
                mr.word().setShift(0, 0, 0, 0, 2, 0);
                mr.word().setOutput(new Word(null, "v"), new Word(null, "v"), new Word(null, "v"));
                mr.assignment().setPosun( 0, 5);
            }
        }.run(" v = 3;", "word;word;assigment;");
    }

    @Test
    public void testDecVariable()  {
        new TestTemplate<Statement>(sb, new StatementReader()) {
            @Override public void setUpParsers() {
                mr.word().setPosun(  5,0);
                mr.word().setOutput(new Word(null, "int"));
                mr.decVariable().setPosun( 0, 0);
            }
        }.run(" int v = 3;", "word;decVariable;");
    }

}
