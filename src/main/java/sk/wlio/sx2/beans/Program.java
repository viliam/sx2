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

import sk.wlio.sx2.beans.instruction.DeclarationCommand;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;

import java.util.HashMap;
import java.util.Map;

public class Program extends SlovoAbstract {

    final Map<String, DeclarationVariable> mapPremenna = new HashMap<String, DeclarationVariable>();
    final Map<String, DeclarationCommand> mapPrikaz = new HashMap<String, DeclarationCommand>();

    public Program(Pozicia pozicia) {
        super(pozicia);
    }

    public void pridajPremennu( DeclarationVariable declarationVariable) {
        mapPremenna.put( declarationVariable.getNazov().getObsah(), declarationVariable);
    }

    public void pridajPrikaz( DeclarationCommand declarationCommand) {
        mapPrikaz.put( declarationCommand.getNazov().getObsah(), declarationCommand);
    }

    public Map<String, DeclarationCommand> getMapPrikaz() {
        return mapPrikaz;
    }

    public Map<String, DeclarationVariable> getMapPremenna() {
        return mapPremenna;
    }
}
