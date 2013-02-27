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

package sk.wlio.sx2.integra.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.readers.vyraz.VyrazReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.DeklaracieVisitor;
import sk.wlio.sx2.visitors.TypVyrazuVisitor;
import sk.wlio.sx2.integra.TestAbstract;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

@Deprecated
public class TypVyrazuVisitorTest {

    class TypZlozenehoVyrazuTest extends TestAbstract<IVyraz>  {

        @Override
        protected String[] getDobreVety() {
            return new String[] {
                "4+5", "6 < 45 - 4", " (premenna + 4) * 3 "
            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                "4+ 5 & 5 > 6",
                " (6 < 45 - 4) + 4",
                " prikaz() & 4 > 7 | 4 < premenna > 4"
            };
        }

        public TypZlozenehoVyrazuTest() {
            super(new VyrazReader(),
                new TestVisitor<IVyraz>() {
                    public void visit(TextContext tC, IVyraz slovo) {
                        TypVyrazuVisitor visitor = new TypVyrazuVisitor();
                        visitor.visit( (VyrazZlozeny) slovo);
                    }
                });
        }
    }

    @Test
    public void testZlozenyVyrazOK() {
        new TypZlozenehoVyrazuTest().testOk();
    }

    @Test
    public void testZlozenyVyrazFail() {
        new TypZlozenehoVyrazuTest().testFail();
    }


    class TypDeklaraciaTriedaTest extends TestAbstract<Program>  {

        @Override
        protected String[] getDobreVety() {
            return new String[] {
                "cislo sum(cislo n) { vrat 3; } cislo a = sum(3);", " cislo v = 3;"
            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                " bool k = 3;", "cislo sum(cislo n) { vrat 3; } bool v; cislo a = sum(v);"
            };
        }

        public TypDeklaraciaTriedaTest() {
            super(new ProgramReader(),
                new TestAbstract.TestVisitor<Program>() {
                    public void visit(TextContext tC, Program slovo) {
                        DeklaracieVisitor dVisitor = new DeklaracieVisitor();
                        dVisitor.visit( slovo);
                        TypVyrazuVisitor visitor = new TypVyrazuVisitor( );
                        visitor.visit( slovo);
                    }
                });
        }
    }

    @Test
    public void testDeklaraciaTriedaOK() {
        new TypDeklaraciaTriedaTest().testOk();
    }

    @Test
    public void testDeklaraciaTriedaFail() {
        new TypDeklaraciaTriedaTest().testFail();

    }
}
