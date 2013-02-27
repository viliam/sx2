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

package sk.wlio.sx2.beans.symbol.enums;

import java.util.ArrayList;
import java.util.List;

import static sk.wlio.sx2.beans.symbol.enums.SymbolEnum.*;

public enum SymbolsEnum {
    OP_ARITM( PLUS, MINUS, KRAT, MODULO, ZVYSOK),
    OP_BOOL( AND, AND_STRONG, OR, OR_STRONG),
    OP_POROVNANIE( MENSI, MENSI_ROVNY, VACSI, VACSI_ROVNY,
                ROVNY, NEROVNY),
    OP_PRIRADENIE( PRIRAD ),

    CIARKY( CIARKA, BODKO_CIARKA, BODKA),
    ZATVORKY( ZATVORKA_NORM_OTOVRENA, ZATVORKA_NORM_ZATVORENA,
              ZATVORKA_BLOK_OTVORENA, ZATVORKA_BLOK_ZATVORENA);

    private final SymbolEnum[] symboly;

    SymbolsEnum(SymbolEnum... symboly) {
        this.symboly = symboly;
    }

    public String[] getSymbols() {
        List<String> li = new ArrayList<String>();
        for (SymbolEnum sy: symboly) {
            li.add( sy.getSymbol());
        }
        return li.toArray( new String[ li.size()]);
    }

    public boolean jePrefix(char a) {
        for (SymbolEnum sy: symboly) {
            if ( sy.jePrefix( a))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (SymbolEnum sy: symboly) {
            sb.append( sy.getSymbol());
        }
        return sb.toString();
    }

    public static boolean jeSymbol(char a) {
        for ( SymbolsEnum symbolEnum : SymbolsEnum.values()) {
             if ( symbolEnum.toString().indexOf(a) != -1) {
                 return true;
             }
        }
        return false;
    }
}
