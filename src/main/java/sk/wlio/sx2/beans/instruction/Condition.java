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

package sk.wlio.sx2.beans.instruction;

import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.IVyraz;

public class Condition extends WordAbstract {

    private final InstructionWord ak;
    private final Bracket z1, z2;
    private final IVyraz vrzBool;
    private final Instrukcia instrukcia;

    public Condition(InstructionWord ak, IVyraz vrzBool, Instrukcia instrukcia, Bracket z1, Bracket z2) {
        super(ak.getPosition());
        this.ak = ak;
        this.vrzBool = vrzBool;
        this.instrukcia = instrukcia;
        this.z1 = z1;
        this.z2 = z2;
    }

    public InstructionWord getAk() {
        return ak;
    }

    public Instrukcia getInstrukcia() {
        return instrukcia;
    }

    public Bracket getZ1() {
        return z1;
    }

    public Bracket getZ2() {
        return z2;
    }
    public IVyraz getVrzBool() {
        return vrzBool;
    }
}
