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

package sk.wlio.sx2.unit.parsers.declaration;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.DeclarationCommand;
import sk.wlio.sx2.beans.statement.DeclarationVariable;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.unit.parsers.AbstractParserTest;
import sk.wlio.sx2.unit.parsers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class DeclarationProgramParser extends AbstractParserTest {

    @Test
    public void testBasic()  {
        TestTemplate<Program> tt =
            new TestTemplate<Program>(sb, new ProgramReader()) {
                @Override
                public void setUpParsers() {

                    mr.word().setShift(5, 0, 4, 0,
                            5, 0, 4, 0,
                            6, 0, 5, 0);
                    mr.word().setOutput(new Word(null, "bool"), new Word(null, "ahoj"),
                            new Word(null, "bool"), new Word(null, "ahoj"),
                            new Word(null, "int"), new Word(null, "ahoj"));
                    mr.decCommand().setOutput(new DeclarationCommand(new DataType(new Word(new Position(3, 3))),
                            new Word(null, "ahoj"), null, null));
                    mr.decCommand().setShift(25, 0);
                    mr.decVariable().setOutput(new DeclarationVariable(
                            new DataType(new Word(new Position(3, 3), "int")), new Word(null, "ahoj"), new Comma(null, SymbolEnum.SEMICOLON)));
                    mr.decVariable().setShift(12, 0);
                }
            };
        tt.run(" bool ahoj() { return 3; } " +
               " int ahoj;", "word;word;word;word;decCommand;word;word;decVariable;");
    }

}
