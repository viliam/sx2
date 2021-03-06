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

package sk.wlio.sx2.parsers.statement;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.parsers.Readers;
import sk.wlio.sx2.interfaces.*;

public class StatementParser implements SxParser<Statement> {

    public Statement read(TextContext tC)  {

        if (tC.isPrefixDataType())
            return Readers.decVariable().read(tC);

        if (tC.isPrefixVariable())
            return Readers.assignment().read(tC);

        if (tC.isPrefixStatement() ) {
            switch ( tC.vratPrefixZakazaneSlovo()) {
                case RETURN:
                    return Readers.aReturn().read(tC);
                case IF : {
                    return Readers.condition().read(tC);
                }
               }
//                //3. todo (case, while, for,..)
            }

//            case Prefix.SLOVO_PRIKAZ: {
//                PrikazLink pri = odkusniPrikazLink();
//                pri.pridajBodkoCiarku(odkusniCiarku(';'));
//                return pri;
//            }


        throw SxException.create(SxExTyp.EXPECTED_STATEMENT,tC);
    }

}
