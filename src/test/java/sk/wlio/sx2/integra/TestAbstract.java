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

    protected abstract String[] getDobreVety();
    protected abstract String[] getChybneVety();

    @Test
    public void testOk() {
        for (String v: getDobreVety()) {
            System.out.println("testovane word OK : " + v);
            TextContext tC = new TextContext(v);
            T veta= spracuj(tC);
            assertNotNull( veta);

            testVisitor.visit( tC, veta);
        }
    }

    @Test
    public void testFail() {
        for (String v : getChybneVety()) {
            System.out.println("testovane word Fail : " + v);
            try  {
                TextContext tC = new TextContext(v);
                T slovo = spracuj(tC);

                testVisitor.visit(tC, slovo);

                fail(v);
                //if (tC.isEndOfFile() ) fail("");
            } catch (SxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private T spracuj( TextContext tC)  {
        T slovo = reader.read(tC);
        assertNotNull(slovo);
        return slovo;
    }
}