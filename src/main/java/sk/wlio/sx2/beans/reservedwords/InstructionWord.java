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

package sk.wlio.sx2.beans.reservedwords;

import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;

public class InstructionWord extends Slovo {

    public InstructionWord(Slovo slovo) {
        super(slovo);
    }

    public InstructionWord(Pozicia pozicia, String obsah) {
        super(pozicia, obsah);
    }
}
