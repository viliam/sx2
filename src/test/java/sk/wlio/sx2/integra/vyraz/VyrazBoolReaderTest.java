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

package sk.wlio.sx2.integra.vyraz;


@Deprecated
public class VyrazBoolReaderTest {
//
//
//    @Test
//    public void testVyrazBoolProvnanie()  {
//        IVyraz expression = citajBoolVyraz("  ahoj & 5<4 ");
//        assertEquals( "pozicia" , new Pozicia(2,0), expression.getPozicia());
//    }
//
//    @Test
//    public void testVyrazBoolAndOr()  {
//        IVyraz expression = citajBoolVyraz("  5<4 & 4>3 | 6!=4");
//        assertEquals( "pozicia" , new Pozicia(2,0), expression.getPozicia());
//        assertTrue( expression instanceof VyrazBool);
//        VyrazBool vBool = (VyrazBool) expression;
//        assertTrue( vBool.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool.getV2() instanceof VyrazBool);
//        assertTrue( vBool.getOp() instanceof Operator);
//        assertEquals( vBool.getOp().getSymbol(), "&");
//
//        VyrazBool vBool2 = (VyrazBool) vBool.getV2();
//        assertTrue( vBool2.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool2.getV2() instanceof VyrazPorovnanie);
//        assertEquals( vBool2.getOp().getSymbol(), "|");
//
//    }
//
//    @Test
//    public void testVyrazBoolZatvorky()  {
//        IVyraz expression = citajBoolVyraz("  5<4 & (4>3 || 6!=4)");
//        assertEquals( "pozicia" , new Pozicia(2,0), expression.getPozicia());
//        assertTrue( expression instanceof VyrazBool);
//        VyrazBool vBool = (VyrazBool) expression;
//        assertTrue( vBool.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool.getV2() instanceof VyrazVzatvorke);
//        assertTrue( vBool.getOp() instanceof Operator );
//        assertEquals( vBool.getOp().getSymbol(), "&");
//
//        VyrazVzatvorke vZatvorke = (VyrazVzatvorke) vBool.getV2();
//        assertTrue( vZatvorke.getV()  instanceof VyrazBool);
//        VyrazBool vBool2 = (VyrazBool) vZatvorke.getV();
//        assertTrue( vBool2.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool2.getV2() instanceof VyrazPorovnanie);
//        assertEquals( vBool2.getOp().getSymbol(), "||");
//
//    }
//
//
//    private IVyraz citajBoolVyraz(String tento)  {
//        TextContext tC = new TextContext(tento);
//        VyrazBoolReader vBoolReader = new VyrazBoolReader();
//        IVyraz expression = vBoolReader.citaj(tC);
//
//        assertNotNull(expression);
//
//        return expression;
//    }

}
