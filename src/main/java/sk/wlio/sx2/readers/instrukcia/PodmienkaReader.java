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

package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Condition;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class PodmienkaReader implements TextReader<Condition> {

    public Condition citaj(TextContext tC)        {
        InstructionWord akSlovo = Readers.instrukciaSlovo().citaj(tC);
        if ( !ReservedWordEnum.IF.je( akSlovo.toString())  )
            throw SxException.create( SxExTyp.CAKAL_AK, tC);


        Zatvorka z1 = Readers.zatvorka().citaj(tC);
        IVyraz vrzBool = Readers.vrzBool().citaj(tC);
        Zatvorka z2 = Readers.zatvorka().citaj(tC);
        Instrukcia instrukcia = Readers.instrukcia().citaj(tC);
        return new Condition( akSlovo, vrzBool, instrukcia, z1, z2 );
    }
}
