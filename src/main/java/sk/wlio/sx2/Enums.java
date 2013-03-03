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

package sk.wlio.sx2;

import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;

public class Enums {

    public static enum ExpType {
        VOID, INT, BOOL, COMPARISON, UNKNOWN;

        
        public static ExpType getDatovyTyp( DataType dt)  {
            ReservedWordEnum zs = RezervedWordsEnum.DATA_TYPE.vrat(dt.getObsah());

            switch ( zs) {
                case INT : return ExpType.INT;
                case RETURN: return ExpType.UNKNOWN;
                case IF    : case VOID: return ExpType.VOID;
                case BOOL  : case TRUE: case FALSE:  return ExpType.BOOL;
            }
            throw SxException.create(SxExTyp.CAKAL_DATOVY_TYP, dt.getPosition());
        }
    }

}
