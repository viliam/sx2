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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    //najde nasledujuci znak v slove
    public static int posunNasledujuciZnakVriadku(String riadok, int x) {
        while (x<riadok.length() && (riadok.charAt(x)==' ' || riadok.charAt(x)=='\n' ) ) x++;
        return x;
    }

    //odkusne slovo na poziciii i-tej
    //pozor, nesmie zacinat medzerou
    public static int najdiKoniecSlova(String slovo, int i)  {
        if (i>=slovo.length() ) return i;
         //kym sa nerovnas zakazanym znakom ani operatorom, ani koncu riadka, tak v pohode
        char p = slovo.charAt(i);
        while ( i<slovo.length() && p != ' ' && (jePismeno(p)|| isInt(p))) {
            i++;
            if ( i < slovo.length() ) p = slovo.charAt(i);
        }
        return i;
    }

    public static boolean jePismeno(char a) {
        Pattern pattern = Pattern.compile( "[a-zA-Z]" );
        Matcher matcher = pattern.matcher(String.valueOf(a));
        return matcher.matches();
    }

    public static boolean isInt(char a) {
        Pattern pattern = Pattern.compile( "[0-9]" );
        Matcher matcher = pattern.matcher(String.valueOf(a ));
        return matcher.matches();
    }

}
