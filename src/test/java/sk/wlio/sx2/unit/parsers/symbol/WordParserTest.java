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

package sk.wlio.sx2.unit.parsers.symbol;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.parsers.symbol.WordParser;

import java.lang.reflect.Method;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class WordParserTest {

    @Test
    public void testBasic()  {
        TextContext tc = new TextContext("  sl34ovo  ");
        WordParser sParser = new WordParser();
        Word word = sParser.read(tc);

        assertNotNull(word);
        assertEquals( "sl34ovo", word.toString());
        assertEquals( new Position(2,0), word.getPosition());
        assertEquals( new Position(9,0), tc.getPosition());
    }

    @Test
    public void testFail() {
        TextContext tc = new TextContext("  //sl34ovo  ");
        WordParser sParser = new WordParser();
        try {
            sParser.read(tc);
            fail();
        } catch (SxException e) {
            assertEquals( SxExTyp.EMPTY_WORD, e.getType());
        }
    }

    @Test
    public void testFindEndOfWord() {
        WordParser sr = new WordParser();
        int position  = invokeFileEndOfWord(sr, " nieco  ", 4);
        assertEquals( 6, position);

        position = invokeFileEndOfWord(sr, " nieco34Ine", 4);
        assertEquals( 11, position);
    }

    private int invokeFileEndOfWord(WordParser wordParser, String line, int x) {
        try {
            Class testClass = WordParser.class;
            Method testMetoda = testClass.getDeclaredMethod("findEndOfWord", String.class, int.class);
            testMetoda.setAccessible( true);
            return (Integer) testMetoda.invoke(wordParser,line , x);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
