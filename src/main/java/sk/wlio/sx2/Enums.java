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

import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovaEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;

public class Enums {

    public static enum VyrazTyp {
        NIC ,CISLO, BOOL, POROVNANIE, NEURCENY;

        
        public static VyrazTyp getDatovyTyp( DatovyTyp dt)  {
            RezervovaneSlovoEnum zs = RezervovaneSlovaEnum.DATOVY_TYP.vrat(dt.getObsah());

            switch ( zs) {
                case CISLO : return Enums.VyrazTyp.CISLO;
                case VRAT  : return Enums.VyrazTyp.NEURCENY;
                case AK    : case NIC  : return Enums.VyrazTyp.NIC;
                case BOOL  : case ANO   : case NIE  :  return Enums.VyrazTyp.BOOL;
            }
            throw SxException.create(SxExTyp.CAKAL_DATOVY_TYP, dt.getPozicia());
        }
    }

    public static enum SlovoPrefix {
        CISLO, DATOVY_TYP, INSTRUKCIA, PRIKAZ, PREMENNA, ZLE
    }

////    public static final String[] ZAKAZANE_SLOVA = new String[] { "vrat", "cislo", "bool"};
//
//    public static enum Znak {
//        PISMENO{
//            public boolean je(char a) {
//                return !Znak.CIARKA.je(a)
//                    && !Znak.CISLO.je(a)
//                    && !Znak.OPERATOR.je(a)
//                    && !Znak.ZATVORKA.je(a) ;  //vsetko ostatne
//            }
//        },
//        CISLO {
//            public boolean je(char a) {
//                return je("0123456789", a);
//            }
//        },
//        OPERATOR{
//            public boolean je(char a) {
//                return je("+-/%&|<>=!", a);
//            }
//        },
//        ZATVORKA{
//            public boolean je(char a) {
//                return je("(){}[]", a);
//            }
//        },
//        CIARKA {
//            public boolean je(char a) {
//                return je(";,", a);
//            }
//        };
//
//        public abstract boolean je(char a);
//
//        protected boolean je(String s, char a) {
//            return s.indexOf(a) != -1;
//        }
//    }

}
