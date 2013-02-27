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

package sk.wlio.sx2.unit.readers;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.OperatorPorovnanieReader;

import static org.testng.AssertJUnit.assertEquals;

public class OperatorReaderTest {

    @Test
    public void testOperatorAritm()  {
        TextContext tC= new TextContext("  + ");
        Operator operator = Readers.opAritm().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Position(2,0), operator.getPosition() );
        assertEquals( "Cislo kontrola ", "+", operator.getSymbol() );
        assertEquals( "Posunuty inx", new Position(3,0), tC.getPozicia() );
    }

    @Test
    public void testOperatorBoolPorovnanie()  {
        TextContext tC= new TextContext("  <= ");
        Operator operator = new OperatorPorovnanieReader().citaj(tC);

        assertEquals( "Pozicia kontrola ", new Position(2,0), operator.getPosition() );
        assertEquals( "Cislo kontrola ", "<=", operator.getSymbol() );
        assertEquals( "Posunuty inx", new Position(4,0), tC.getPozicia() );
    }

}
