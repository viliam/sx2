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

package sk.wlio.sx2.beans;

import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPrikaz;

import java.util.HashMap;
import java.util.Map;

public class Program extends SlovoAbstract {

    final Map<String, DeklaraciaPremennej> mapPremenna = new HashMap<String, DeklaraciaPremennej>();
    final Map<String, DeklaraciaPrikaz> mapPrikaz = new HashMap<String, DeklaraciaPrikaz>();

    public Program(Pozicia pozicia) {
        super(pozicia);
    }

    public void pridajPremennu( DeklaraciaPremennej deklaraciaPremennej) {
        mapPremenna.put( deklaraciaPremennej.getNazov().getObsah(), deklaraciaPremennej);
    }

    public void pridajPrikaz( DeklaraciaPrikaz deklaraciaPrikaz) {
        mapPrikaz.put( deklaraciaPrikaz.getNazov().getObsah(), deklaraciaPrikaz);
    }

    public Map<String, DeklaraciaPrikaz> getMapPrikaz() {
        return mapPrikaz;
    }

    public Map<String, DeklaraciaPremennej> getMapPremenna() {
        return mapPremenna;
    }
}
