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

package sk.wlio.sx2.unit.readers.vyraz;

import org.testng.annotations.Test;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazBoolVzatvorkeReader;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;

public class VyrazBoolVzatvorkeReaderTest extends AbstractReaderTest {

    @Test
    public void testVyraz()  {
        new TestTemplate<IVyraz>(sb, new VyrazBoolVzatvorkeReader() ) {
            @Override
            public void nastavReader() {
                mr.zatvorka().setPosun( 1, 0 ,  1, 0 );
                 mr.vrzBool().setPosun( 4, 0 );
            }
        }.run("(ahoj)", "zatvorka;vrzBool;zatvorka;");
    }
}
