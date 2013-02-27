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

package sk.wlio.sx2.unit.readers.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.readers.instrukcia.DeklaraciaPremennejReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class DeklaraciaPremennejReaderTest extends AbstractReaderTest {

    @Test
    public void test()  {
        new TestTemplate<DeklaraciaPremennej>(sb, new DeklaraciaPremennejReader()) {
            @Override public void nastavReader() {

                mr.datovyTyp().setVystup( new DatovyTyp(null, "cislo"));
                mr.datovyTyp().setPosun( 5,0 );
                mr.slovo().setPosun(  0,0 ,  1,0);
                mr.slovo().setVystup( new Slovo(null, "a"));
            }
        }.run("cislo a;", "datovyTyp;slovo;");
    }

}

