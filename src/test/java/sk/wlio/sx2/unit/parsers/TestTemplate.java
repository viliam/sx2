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

package sk.wlio.sx2.unit.parsers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.SxParser;

import static org.testng.AssertJUnit.assertEquals;

//todo: provide json setUp for test
public abstract class TestTemplate<T extends IWord> {

    final StringBuffer sb;
    private T result;
    private SxParser<T> sxParser;

    protected TestTemplate(StringBuffer sb, SxParser<T> sxParser) {
        this.sb = sb;
        this.sxParser = sxParser;
    }

    public TextContext run(String expr, String postupnost )  {
        TextContext tC = new TextContext( expr);
        setUpParsers();
        result = sxParser.read(tC);
        assertEquals(postupnost, sb.toString() );
        return tC;
    }

    public abstract void setUpParsers();

    public T getVysledok() {
        return result;
    }
}
