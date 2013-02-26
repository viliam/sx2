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
//            public boolean is(char a) {
//                return !Znak.CIARKA.is(a)
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
//        CIARKA {
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
