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

package sk.wlio.sx2.unit.readers.declaration;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.DeclarationCommand;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.statement.DeclarationCommandReader;
import sk.wlio.sx2.interfaces.TextReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.*;

public class DeklaraciaPrikazReaderTest extends AbstractReaderTest {

    @Test
    public void testBasic()  {
        TestTemplate<DeclarationCommand> tt =
            new TestTemplate<DeclarationCommand>(sb, new DeclarationCommandReader()) {
                @Override
                public void nastavReader() {
                    mr.decParamters().setPosun( 2,0);
                    mr.dataType().setPosun( 5,0);
                    mr.dataType().setOutput(new DataType(null, "bool"));
                    mr.word().setShift(6, 0, 6, 0);
                    mr.word().setOutput(new Word(null, "aa"), new Word(null, "ahoj"));
                    mr.block().setPosun( 11, 0 );
                }
            };
        tt.run("  bool ahoj() { return 3; } ", "dataType;word;word;decParameter;block;");

        DeclarationCommand dp = tt.getVysledok();
        assertEquals("nazov prikazu", "ahoj", dp.getName().toString() );
    }

    @Test
    public void testNiejeDatovyTyp() {
        try {
            mr.dataType().setPosun( 5,0 );
            mr.dataType().setOutput(new DataType(null, "boool"));

            citajDekPrikaz("  boool ahoj() { return 3; } ");
            fail("Cakal chybu, zla declarations prikazu");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.EXPECTED_COMMAND_DECLARATION,  e.getType());
        }
    }

    @Test
    public void testZlyNazovPrikazu() {
        try {
            mr.dataType().setPosun( 5,0 );
            mr.dataType().setOutput(new DataType(null, "bool"));

            mr.word().setShift(4, 0, 4, 0);
            mr.word().setOutput(new Word(null, "3ahoj"));

            citajDekPrikaz("  bool 3ahoj() { aReturn 3; } ");
            fail("Cakal chybu, zly nazov prikazu");
        } catch (SxException e) {
            assertEquals( "Typ chyby", SxExTyp.WRONG_COMMAND_NAME,  e.getType());
        }
    }

    private DeclarationCommand citajDekPrikaz(String ts)  {
        TextContext text = new TextContext(ts);
        TextReader<DeclarationCommand> dpReader = new DeclarationCommandReader();
        DeclarationCommand dekPrikaz= dpReader.read(text);
        assertNotNull("nenulovy command", dekPrikaz);
        return dekPrikaz;
    }
}
