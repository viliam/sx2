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

    public static int findNextCharacterInLine(String line, int x) {
        while (x<line.length() && (line.charAt(x)==' ' || line.charAt(x)=='\n' ) ) x++;
        return x;
    }

    //take a word on i-positions (!i can be on empty character)
    public static int findEndOfWord(String line, int i)  {
        if (i>=line.length() ) return i;

        char p = line.charAt(i);
        while ( i<line.length() && p != ' ' && (isLetter(p)|| isInt(p))) {
            i++;
            if ( i < line.length() ) p = line.charAt(i);
        }
        return i;
    }

    public static boolean isLetter(char a) {
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
