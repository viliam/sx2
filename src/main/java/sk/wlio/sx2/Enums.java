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

    public static enum VyrazTyp {
        NIC ,CISLO, BOOL, POROVNANIE, NEURCENY;

        
        public static VyrazTyp getDatovyTyp( DataType dt)  {
            ReservedWordEnum zs = RezervedWordsEnum.DATA_TYPE.vrat(dt.getObsah());

            switch ( zs) {
                case INT : return Enums.VyrazTyp.CISLO;
                case RETURN: return Enums.VyrazTyp.NEURCENY;
                case IF    : case VOID: return Enums.VyrazTyp.NIC;
                case BOOL  : case TRUE: case FALSE:  return Enums.VyrazTyp.BOOL;
            }
            throw SxException.create(SxExTyp.CAKAL_DATOVY_TYP, dt.getPosition());
        }
    }

    public static enum SlovoPrefix {
        CISLO, DATOVY_TYP, INSTRUKCIA, PRIKAZ, PREMENNA, ZLE
    }

////    public static final String[] ZAKAZANE_SLOVA = new String[] { "vrat", "cislo", "bool"};
//
//    public static enum Znak {
//        PISMENO{
//            public boolean is(char a) {
//                return !Znak.COMMA.is(a)
//                    && !Znak.CISLO.is(a)
//                    && !Znak.OPERATOR.is(a)
//                    && !Znak.ZATVORKA.is(a) ;  //vsetko ostatne
//            }
//        },
//        CISLO {
//            public boolean is(char a) {
//                return is("0123456789", a);
//            }
//        },
//        OPERATOR{
//            public boolean is(char a) {
//                return is("+-/%&|<>=!", a);
//            }
//        },
//        ZATVORKA{
//            public boolean is(char a) {
//                return is("(){}[]", a);
//            }
//        },
//        COMMA {
//            public boolean is(char a) {
//                return is(";,", a);
//            }
//        };
//
//        public abstract boolean is(char a);
//
//        protected boolean is(String s, char a) {
//            return s.indexOf(a) != -1;
//        }
//    }

}
