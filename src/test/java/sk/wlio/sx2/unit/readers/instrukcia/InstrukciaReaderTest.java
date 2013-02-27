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

package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.readers.instrukcia.InstrukciaReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

public class InstrukciaReaderTest extends AbstractReaderTest {

    @Test
    public void testPriradenie()  {
        new TestTemplate<Instrukcia>(sb, new InstrukciaReader()) {
            @Override
            public void nastavReader() {
                mr.slovo().setPosun( 0,0,  0,0, 2,0);
                mr.slovo().setVystup( new Slovo(null, "v"), new Slovo(null, "v"), new Slovo(null, "v"));
                mr.priradenie().setPosun( 0, 5);
            }
        }.run(" v = 3;", "slovo;slovo;priradenie;");
    }

    @Test
    public void testDekPremennej()  {
        new TestTemplate<Instrukcia>(sb, new InstrukciaReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun(  5,0);
                mr.slovo().setVystup( new Slovo(null, "cislo"));
                mr.dekPremennej().setPosun( 0, 0);
            }
        }.run(" cislo v = 3;", "slovo;dekPremennej;");
    }

}
