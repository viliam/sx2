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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.statement.Condition;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.statement.ConditionReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class PodmienkaReaderTest extends AbstractReaderTest {

    @Test
    public void testAk()  {
        new TestTemplate<Condition>(sb, new ConditionReader()) {
            @Override
            public void nastavReader() {
                mr.instrukciaSlovo().setPosun(  3,0 );
                mr.instrukciaSlovo().setVystup(new StatementWord( null, "if"));
                mr.zatvorka().setPosun(  1,0 ,  1,0 );
                mr.vyraz().setPosun(5, 0);
                mr.instrukcia().setPosun(9,0);
            }
        }.run("if ( b>32) nieco();", "statmentWord;bracket;expression;bracket;statement;");
    }

    @Test
    public void testNeznameSlovo() {
        mr.instrukciaSlovo().setPosun(4,0);
        mr.instrukciaSlovo().setVystup( new StatementWord(null, "trat"));

        TextContext tC = new TextContext("trat 4;  ");
        try {
            new ConditionReader().read(tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.CAKAL_AK, e.getTyp());
        }
    }
}