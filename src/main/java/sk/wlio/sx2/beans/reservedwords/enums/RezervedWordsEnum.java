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

import sk.wlio.sx2.beans.Word;

import java.util.ArrayList;
import java.util.List;

public enum RezervedWordsEnum {

    DATA_TYPE(ReservedWordEnum.INT, ReservedWordEnum.BOOL),
    INSTRUCTION_WORD(ReservedWordEnum.RETURN, ReservedWordEnum.IF),
    DATA_VALUE( ReservedWordEnum.VOID, ReservedWordEnum.TRUE, ReservedWordEnum.FALSE);

    private final ReservedWordEnum[] zakazSlova;

    RezervedWordsEnum(ReservedWordEnum... zakazSlova) {
        this.zakazSlova = zakazSlova;
    }

    public boolean is(String s) {
        return is(zakazSlova, s);
    }

    public static boolean isWord(Word s) {
        for (RezervedWordsEnum za : RezervedWordsEnum.values()) {
            if (za.is(s.getContent())) return true;
        }
        return false;
    }

    public ReservedWordEnum vrat(String s) {
        return returns(zakazSlova, s);
    }

    public String[] getSymbols() {
        List<String> li = new ArrayList<String>();
        for (ReservedWordEnum sy: zakazSlova) {
            li.add( sy.getSymbol());
        }
        return li.toArray( new String[ li.size()]);
    }

    private static boolean is(ReservedWordEnum[] anEnums, String s) {
        return returns(anEnums, s) != null;
    }

    private static ReservedWordEnum returns(ReservedWordEnum[] anEnums, String s) {
        for ( ReservedWordEnum symbol: anEnums) {
            if (symbol.getSymbol().equals(s))
                return symbol;
        }
        return null;
    }

}
