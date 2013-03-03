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

public enum SymbolEnum {
    //ARITM
    PLUS("+"), MINUS("-"), TIMES("*"),
    MODULO("%") , REST("/"),
    //BOOL
    AND("&"), OR("|"), AND_STRONG("&&"), OR_STRONG("||"),
    //COMPARISON
    SMALLER("<"), GREATER(">"), SMALLER_EQUAL("<="),
    GRATER_EQUAL(">=") , EQUAL("=="), UNEQUAL("!="),
    ASSIGN("="),

    BRACKET_NORM_OPEN("("),
    BRACKET_NORM_CLOSE(")"),
    PARENTHESIS_BLOCK_OPEN("{"),
    PARENTHESIS_BLOCK_CLOSE("}"),

    COMMA(","),
    SEMICOLON(";"),
    DOT(",");


    private final String symbol;

    SymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toString() {
        return symbol;
    }

    public static SymbolEnum makeSymbol(String s) {
        for ( SymbolEnum op : SymbolEnum.values()) {
            if ( op.toString().equals(s))
                return op;
        }
        return null;
    }

    public boolean jePrefix(char a) {
        return symbol.charAt(0) == a;
    }
}
