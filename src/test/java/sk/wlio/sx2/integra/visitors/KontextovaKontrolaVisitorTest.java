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
import sk.wlio.sx2.readers.expression.ExprReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.visitors.KontextovaKontrolaVisitor;
import sk.wlio.sx2.integra.TestAbstract;

public class KontextovaKontrolaVisitorTest {

    class DeklaracieVisitorTestImpl extends TestAbstract<Program> {

        public DeklaracieVisitorTestImpl() {
            super(new ProgramReader(), new TestVisitor<Program>() {
                public void visit(TextContext tC, Program slovo) {
                    KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
                    visitor.visit( slovo);
                }
            });
        }

        @Override
        protected String[] getDobreVety() {
            return new String[] {
                "cislo a;  cislo b() { vrat a + 3; }",
                "cislo s() { vrat 3+3; } cislo a = s();",
                "cislo sum(cislo n) { vrat 3; } cislo a = sum(3);", " cislo v = 3;"
                    ,"cislo sum(cislo n) { cislo a = ine(3); } cislo ine(cislo n) { cislo a = 3; }"
            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                "cislo a;  cislo b() { vrat c + 3; }",
                " bool k = 3;", "cislo sum(cislo n) { vrat 3; } bool v; cislo a = sum(v);"

            };
        }
    }

    @Test
    public void testTriedaOK() {
        new DeklaracieVisitorTestImpl().testOk();
    }

    @Test
    public void testTriedaFail() {
        new DeklaracieVisitorTestImpl().testFail();
    }

    class TypZlozenehoVyrazuTest extends TestAbstract<IVyraz>  {

        @Override
        protected String[] getDobreVety() {
            return new String[] {
                "4+5", "6 < 45 - 4", " (5 + 4) * 3 "
            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                "4+ 5 & 5 > 6",
                " (6 < 45 - 4) + 4",
                " 56 & 4 > 7 | 4 < 4 > 4"
            };
        }

        public TypZlozenehoVyrazuTest() {
            super(new ExprReader(),
                new TestVisitor<IVyraz>() {
                    public void visit(TextContext tC, IVyraz slovo) {
                        KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
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

}
