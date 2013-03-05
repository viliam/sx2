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
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.Return;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.statement.ReturnReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;
import sk.wlio.sx2.unit.readers.mock.TestVyraz;

import static org.testng.AssertJUnit.*;

public class VratReaderTest extends AbstractReaderTest {

    @Test
    public void testVrat()  {
        new TestTemplate<Return>(sb, new ReturnReader()) {
            @Override
            public void nastavReader() {
                mr.word().setPosun( 4,0) ;
                mr.word().setVystup( new Word(null, "return"));
                mr.expression().setPosun(1, 0);
                mr.expression().setVystup(new TestVyraz());
            }
        }.run("return 4;", "word;expression;");
    }

    @Test
    public void testNeznameSlovo() {
        mr.word().setPosun( 4,0 );
        mr.word().setVystup( new Word(null, "trat"));

        TextContext tC = new TextContext("trat 4;  ");
        try {
            new ReturnReader().read(tC);
            fail();
        } catch (SxException e) {
            assertEquals(SxExTyp.EXPECTED_RETURN, e.getType());
        }
    }
}
