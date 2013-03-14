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
import sk.wlio.sx2.beans.expression.Expression;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.parsers.ProgramReader;
import sk.wlio.sx2.parsers.expression.ExprReader;
import sk.wlio.sx2.visitors.ContextAnalysisVisitor;
import sk.wlio.sx2.integra.TestAbstract;

public class ContextAnalysisVisitorTest {

    class ContextAnalysisVisitorTestImpl extends TestAbstract<Program> {

        public ContextAnalysisVisitorTestImpl() {
            super(new ProgramReader(), new TestVisitor<Program>() {
                public void visit(TextContext tC, Program slovo) {
                    ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
                    visitor.visit( slovo);
                }
            });
        }

        @Override
        protected String[] getGoodSentences() {
            return new String[] {
                "int a;  int b() { return a + 3; }",
                "int s() { return 3+3; } int a = s();",
                "int sum(int n) { return 3; } int a = sum(3);", " int v = 3;"
                    ,"int sum(int n) { int a = ine(3); } int ine(int n) { int a = 3; }"
            };
        }

        @Override
        protected String[] getWrongSentences() {
            return new String[] {
                "int a;  int b() { return c + 3; }",
                " bool k = 3;", "int sum(int n) { return 3; } bool v; int a = sum(v);"

            };
        }
    }

    @Test
    public void testProgramOK() {
        new ContextAnalysisVisitorTestImpl().testOk();
    }

    @Test
    public void testProgramFail() {
        new ContextAnalysisVisitorTestImpl().testFail();
    }

    class TypeOfExpressionTest extends TestAbstract<IExpression>  {

        @Override
        protected String[] getGoodSentences() {
            return new String[] {
                "4+5", "6 < 45 - 4", " (5 + 4) * 3 "
            };
        }

        @Override
        protected String[] getWrongSentences() {
            return new String[] {
                "4+ 5 & 5 > 6",
                " (6 < 45 - 4) + 4",
                " 56 & 4 > 7 | 4 < 4 > 4"
            };
        }

        public TypeOfExpressionTest() {
            super(new ExprReader(),
                new TestVisitor<IExpression>() {
                    public void visit(TextContext tC, IExpression slovo) {
                        ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
                        visitor.visit( (Expression) slovo);
                    }
                });
        }
    }

    @Test
    public void testExpressionOk() {
        new TypeOfExpressionTest().testOk();
    }

    @Test
    public void testExpressionFail() {
        new TypeOfExpressionTest().testFail();
    }

}
