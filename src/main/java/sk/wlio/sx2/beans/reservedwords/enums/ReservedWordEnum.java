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

package sk.wlio.sx2.beans.reservedwords.enums;

public enum ReservedWordEnum {

    INT("int") ,
    BOOL("bool"),
    RETURN("return"),
    IF("if"),
    VOID("void"),
    TRUE("true"),
    FALSE("false");

    private final String symbol;

    ReservedWordEnum(String symbol) {
        this.symbol = symbol;
    }

    public boolean je(String s) {
        return symbol.equals(s);
    }

    public String getSymbol() {
        return symbol;
    }

    public static ReservedWordEnum makeSymbol(String sZakaz) {
        for ( ReservedWordEnum zakaz : ReservedWordEnum.values()) {
            if ( zakaz.toString().equals(sZakaz))
                return zakaz;
        }
        return null;
    }

    public String toString() {
        return symbol;
    }
    
    public static boolean jeZakazaneSlovo(String zakazaneSlovo) {
        for ( ReservedWordEnum zs : ReservedWordEnum.values())
            if (zs.getSymbol().equals( zakazaneSlovo) )
                return true;
        return false;
    }

    public static String[] getZakazaneSlova() {
        ReservedWordEnum[] zs = ReservedWordEnum.values();
        String[] strs = new String[ zs.length];
        for (int i=0; i <zs.length; i++) {
            strs[i] = zs[i].getSymbol();
        }
        return  strs;
    }

    public boolean jePrefix(char a) {
        throw new IllegalStateException("Nema zmysel zistovat prefix slova");
    }
}
