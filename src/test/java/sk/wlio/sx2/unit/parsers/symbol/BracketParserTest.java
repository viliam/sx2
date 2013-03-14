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
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.parsers.Readers;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class BracketParserTest {

    @Test
    public void testBasic()  {
        TextContext tC= new TextContext("  ( ");
        Bracket bracket = Readers.bracket().read(tC);

        assertEquals( "Pozicia kontrola ", new Position(2,0), bracket.getPosition() );
        assertEquals( "Cislo kontrola ", "(", bracket.getSymbol() );
        assertEquals( "Posunuty inx", new Position(3,0), tC.getPosition() );
    }

    @Test
    public void testFail() {
        TextContext tC = new TextContext(" k");
        try {
            Readers.bracket().read(tC);
            fail();
        } catch (SxException e) {
            assertEquals( SxExTyp.EXPECTED_BRACKET, e.getType() );
        }
    }


}
