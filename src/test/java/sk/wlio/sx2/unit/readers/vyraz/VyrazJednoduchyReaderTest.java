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
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.expression.SimpleExprReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class VyrazJednoduchyReaderTest extends AbstractReaderTest {

    @Test
    public void testCislo()  {
        new TestTemplate<IVyraz>(sb, new SimpleExprReader()) {
            @Override
            public void nastavReader() {
                mr.cislo().setPosun( 1,0 );
            }
        }.run("4", "cislo;");
    }

    @Test
    public void testPremenna()  {
        new TestTemplate<IVyraz>(sb, new SimpleExprReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 0,0 );
                mr.slovo().setVystup( new Word(null, "asdfds"));
                mr.premenna().setPosun(  6,0) ;
            }
        }.run( "asdfds", "slovo;premenna;");
    }

    @Test
    public void testPrikaz()  {
        new TestTemplate<IVyraz>(sb, new SimpleExprReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 6,0 ,  6,0 ,
                                     6,0 ,  0,0 );
                Word word = new Word(null, "asdfds");
                mr.slovo().setVystup(word, word, word);
                mr.prikaz().setPosun(  6,0 );
            }
        }.run( "asdfds(aa", "slovo;slovo;slovo;prikaz;");
    }

    @Test
    public void testUnknowExpresion() {
        TextContext tC = new TextContext( "+asdfds");
        try {
            new SimpleExprReader().citaj( tC);
            fail();
        } catch (SxException e) {
            assertEquals( SxExTyp.UNEXPECTED_PREFIX, e.getTyp());
        }

    }
}
