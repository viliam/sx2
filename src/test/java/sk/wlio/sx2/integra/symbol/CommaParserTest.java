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

package sk.wlio.sx2.integra.symbol;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

import static org.testng.AssertJUnit.*;

public class CommaParserTest {

    @Test
     public void testBasic()  {
        TextContext tC= new TextContext("  ;");
        Comma comma = Readers.comma().read(tC);

        assertEquals( "Position ", new Position(2,0), comma.getPosition() );
        assertEquals( "Symbol ", ";", comma.getSymbol() );
        assertEquals( "Position after ", new Position(3,0), tC.getPosition() );
    }

    @Test
    public void testExpectedComma() {
        TextContext tC = new TextContext(" k");
        try {
            Readers.comma().read(tC);
            fail();
        } catch (SxException e) {
            assertEquals( e.getType(), SxExTyp.EXPECTED_COMMA);
        }
    }

}
