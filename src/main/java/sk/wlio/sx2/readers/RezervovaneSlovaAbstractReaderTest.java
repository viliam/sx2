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

package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
import sk.wlio.sx2.exception.SxExTyp;

public class RezervovaneSlovaAbstractReaderTest {

    class TestTriedaReader extends RezervovaneSlovaAbstractReader {

        @Override
        protected SxExTyp getSxExceptionTyp() {
            return SxExTyp.ZLY_DATOVY_TYP;
        }

        @Override
        protected RezervovaneSlovaEnum getZakazaneSlova() {
//            return new TestZakazaneSlova();
            return null;
        }
    }

    class TestZakazaneSlova {
        public boolean je(String s) {
            return true;
        }

        public RezervovaneSlovoEnum vrat(String s) {
            return RezervovaneSlovoEnum.AK;
        }
    }
}
