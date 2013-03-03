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
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.readers.expression.ExprReader;
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
                "int a;  int b() { return a + 3; }",
                "int s() { return 3+3; } int a = s();",
                "int sum(int n) { return 3; } int a = sum(3);", " int v = 3;"
                    ,"int sum(int n) { int a = ine(3); } int ine(int n) { int a = 3; }"
            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                "int a;  int b() { return c + 3; }",
                " bool k = 3;", "int sum(int n) { return 3; } bool v; int a = sum(v);"

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

    class TypZlozenehoVyrazuTest extends TestAbstract<IExpression>  {

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
                new TestVisitor<IExpression>() {
                    public void visit(TextContext tC, IExpression slovo) {
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
