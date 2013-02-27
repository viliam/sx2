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

package sk.wlio.sx2.readers.zakazaneslova;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.rezervovaneslova.DatovaHodnota;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.RezervovaneSlovaAbstractReader;

public class DatovaHodnotaReader extends RezervovaneSlovaAbstractReader<DatovaHodnota> {

    public DatovaHodnota citaj(TextContext tC)  {
        return new DatovaHodnota( super.citaj(tC) );
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.CAKAL_DATOVU_HODNOTU;
    }

    @Override
    protected RezervovaneSlovaEnum getZakazaneSlova() {
        return RezervovaneSlovaEnum.DATOVA_HODNOTA;
    }

}
