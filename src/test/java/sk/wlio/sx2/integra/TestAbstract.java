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
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.TextReader;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public abstract class TestAbstract<T extends IWord> {

    protected static interface TestVisitor<T> {
        void visit(TextContext tC, T slovo);
    }

    final protected TextReader<T> reader;
    final protected TestVisitor<T> testVisitor;

    protected TestAbstract(TextReader<T> reader) {
        this(reader, new TestVisitor<T>() {
            public void visit(TextContext tC, T slovo) {}
        });
    }

    protected TestAbstract(TextReader<T> reader, TestVisitor<T> testVisitor) {
        this.reader = reader;
        this.testVisitor = testVisitor;
    }

    protected abstract String[] getGoodSentences();
    protected abstract String[] getWrongSentences();

    @Test
    public void testOk() {
        for (String v: getGoodSentences()) {
            System.out.println("good sentences testing  : " + v);
            TextContext tC = new TextContext(v);
            T sentence= readIt(tC);
            assertNotNull( sentence);

            testVisitor.visit( tC, sentence);
        }
    }

    @Test
    public void testFail() {
        for (String v : getWrongSentences()) {
            System.out.println("wrong sentences testing : " + v);
            try  {
                TextContext tC = new TextContext(v);
                T word = readIt(tC);

                testVisitor.visit(tC, word);

                fail(v);
            } catch (SxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private T readIt(TextContext tC)  {
        T slovo = reader.read(tC);
        assertNotNull(slovo);
        return slovo;
    }
}