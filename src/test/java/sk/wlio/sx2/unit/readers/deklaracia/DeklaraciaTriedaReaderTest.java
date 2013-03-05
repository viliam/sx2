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

package sk.wlio.sx2.unit.readers.deklaracia;

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
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class DeklaraciaTriedaReaderTest extends AbstractReaderTest {

    @Test
    public void testBasic()  {
        TestTemplate<Program> tt =
            new TestTemplate<Program>(sb, new ProgramReader()) {
                @Override
                public void nastavReader() {

                    mr.word().setPosun(5,0, 4,0,
                                        5,0, 4,0,
                                        6,0, 5,0);
                    mr.word().setVystup(new Word(null, "bool"), new Word(null, "ahoj"),
                            new Word(null, "bool"), new Word(null, "ahoj"),
                            new Word(null, "int"), new Word(null, "ahoj"));
                    mr.decCommand().setVystup(new DeclarationCommand(new DataType(new Word( new Position(3,3))),
                                                                  new Word(null, "ahoj"), null, null));
                    mr.decCommand().setPosun( 25, 0 );
                    mr.decVariable().setVystup(new DeclarationVariable(
                            new DataType(new Word( new Position(3,3), "int")), new Word(null, "ahoj"), new Comma(null, SymbolEnum.SEMICOLON)) );
                    mr.decVariable().setPosun( 12, 0 );
                }
            };
        tt.run(" bool ahoj() { return 3; } " +
               " int ahoj;", "word;word;word;word;decCommand;word;word;decVariable;");
    }

}
