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

package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.instruction.Parameters;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.readers.statement.ParameterReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

public class ParametreReaderTest extends AbstractReaderTest {

    @Test
    public void testPrazdny()  {
        new TestTemplate<Parameters>(sb, new ParameterReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0, 1, 0);
                mr.zatvorka().setVystup(new Bracket(null, SymbolEnum.BRACKET_NORM_OPEN));
            }
        }.run(" ()", "bracket;bracket;");
    }

    @Test
    public void testJedenParameter()  {
        new TestTemplate<Parameters>(sb, new ParameterReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0, 1, 0);
                mr.zatvorka().setVystup(new Bracket(null, null));
                mr.vyraz().setPosun(2, 0);
            }
        }.run(" (ff)", "bracket;expression;bracket;");
    }

    @Test
    public void testViacParametrov()  {
        new TestTemplate<Parameters>(sb, new ParameterReader()) {
            @Override public void nastavReader() {
                mr.zatvorka().setPosun( 2, 0,  1, 0);
                mr.zatvorka().setVystup( new Bracket(null, null));
                mr.vyraz().setPosun( 2, 0, 3, 0);
                mr.ciarka().setPosun( 1, 0);
            }
        }.run(" (ff,1+3) ", "bracket;expression;comma;expression;bracket;");
    }

}
