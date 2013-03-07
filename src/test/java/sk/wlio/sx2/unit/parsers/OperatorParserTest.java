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

package sk.wlio.sx2.unit.parsers;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.readers.symbol.OperatorExpressionParser;

import static org.testng.AssertJUnit.assertEquals;

public class OperatorParserTest {

    @Test
    public void testOperatorAritm()  {
        TextContext tC= new TextContext("  + ");
        Operator operator = Readers.opExpr().read(tC);

        assertEquals( "Position ", new Position(2,0), operator.getPosition() );
        assertEquals( "Symbol ", "+", operator.getSymbol() );
        assertEquals( "Postion after ", new Position(3,0), tC.getPosition() );
    }

    @Test
    public void testOperatorBoolComparison()  {
        TextContext tC= new TextContext("  <= ");
        Operator operator = new OperatorExpressionParser().read(tC);

        assertEquals( "Position ", new Position(2,0), operator.getPosition() );
        assertEquals( "Symbol ", "<=", operator.getSymbol() );
        assertEquals( "Position after ", new Position(4,0), tC.getPosition() );
    }

}
