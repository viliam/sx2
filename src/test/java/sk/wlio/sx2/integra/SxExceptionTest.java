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

package sk.wlio.sx2.integra;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.symbol.OperatorAritmReader;
import sk.wlio.sx2.readers.symbol.ZatvorkaReader;
import sk.wlio.sx2.readers.vyraz.*;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

import static org.testng.AssertJUnit.*;

public class SxExceptionTest {

    private void testException(TextContext tC, TextReader tR, SxExTyp typOcakavany) {
        try {
            tR.citaj(tC);
            fail();
        } catch (SxException e) {
            assertEquals(typOcakavany, e.getTyp());
        }
    }

    @Test
    public void testEndOfFile()  {
        TextContext tC = new TextContext(" 123");
        TextReader<Int> cR = new CisloReader();
        cR.citaj( tC);
        testException( tC, cR, SxExTyp.PRAZDNE_SLOVO);
    }

    @Test
    public void testUnExpectedPrefix() {
        TextContext tC = new TextContext(" +123");
        TextReader<IVyraz> vJr = new VyrazJednoduchyReader();
        testException(tC, vJr, SxExTyp.UNEXPECTED_PREFIX );
    }


    @Test
    public void testUnknowOperator() {
        TextContext tC = new TextContext(" c3");
        TextReader oAr = new OperatorAritmReader();
        testException(tC, oAr , SxExTyp.CAKAL_OPERATOR );
    }

    @Test
    public void testCakalZatvorku() {
        TextContext tC = new TextContext(" c3");
        TextReader<Parenthesis> zR = new ZatvorkaReader();
        testException(tC, zR , SxExTyp.CAKAL_ZATVORKU);
    }

    @Test
    public void testCakalAritmOperator() {
        TextContext tC = new TextContext(" 3 e");
        TextReader<IVyraz> zR = new VyrazAritmReader();
        testException(tC, zR , SxExTyp.CAKAL_OPERATOR);
    }

    @Test
    public void testCakalBoolOperator() {
        TextContext tC = new TextContext(" 3 e");
        TextReader<IVyraz> zR = new VyrazBoolReader();
        testException(tC, zR , SxExTyp.CAKAL_OPERATOR_POROVNANIA);
    }


    @Test
    public void testCakalCislo() {
        TextContext tC = new TextContext(" 3d");
        TextReader<IVyraz> zR = new VyrazAritmReader();
        testException(tC, zR , SxExTyp.CAKAL_CISLO);
    }


    @Test
    public void testCakalAritmetickyVyraz() {
        TextContext tC = new TextContext("39(");
        VyrazAritmReader zR = new VyrazAritmReader();
        testException(tC, zR , SxExTyp.CAKAL_OPERATOR);
    }

}
