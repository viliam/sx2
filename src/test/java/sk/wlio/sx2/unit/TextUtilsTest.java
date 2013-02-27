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

package sk.wlio.sx2.unit;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextUtils;

import static org.testng.AssertJUnit.assertEquals;

public class TextUtilsTest {

    @Test
    public void testNajdiNasledujuciZnakVriadku() {
        int ns = TextUtils.posunNasledujuciZnakVriadku(" 1234 56", 3);
        assertEquals("nasledujuci znak", 3, ns);
        ns = TextUtils.posunNasledujuciZnakVriadku(" 12   34 56", 3);
        assertEquals("nasledujuci znak", 6, ns);
        ns = TextUtils.posunNasledujuciZnakVriadku(" 12   34 56", 3);
        assertEquals("nasledujuci znak", 6, ns);
    }

    @Test
    public void testNajdiKoniecSlova() {
        //prekrocenie
        assertEquals(4 , TextUtils.najdiKoniecSlova("aa", 4));
        //konci pismenom
        assertEquals(2 , TextUtils.najdiKoniecSlova("aa 2", 0));
        //konci cislom
        assertEquals(2 , TextUtils.najdiKoniecSlova("a2 2", 0));

    }
}
