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

package sk.wlio.sx2.unit.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.TextReader;

import static org.testng.AssertJUnit.assertEquals;

public abstract class TestTemplate<T extends IWord> {

    final StringBuffer sb;
    private T result;
    private TextReader<T> textReader;

    protected TestTemplate(StringBuffer sb, TextReader<T> textReader) {
        this.sb = sb;
        this.textReader = textReader;
    }

    public TextContext run(String vyraz, String postupnost )  {
        TextContext tC = new TextContext( vyraz);
        nastavReader();
        result = textReader.read(tC);
        assertEquals(postupnost, sb.toString() );
        return tC;
    }

    public abstract void nastavReader();

    public T getVysledok() {
        return result;
    }
}
