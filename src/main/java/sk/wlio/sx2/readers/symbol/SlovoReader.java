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

package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.TextUtils;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.TextReader;

public class SlovoReader implements TextReader<Slovo> {

    public Slovo citaj(TextContext tC)  {
        tC.najdiNasledujuciZnak();
        Pozicia inx= tC.getPozicia();
        int zacX =inx.getX();

        String riadok = tC.getRiadok();
        String slovo = predcitajSlovo(riadok, zacX);
        if ("".equals(slovo) )
            throw SxException.create(SxExTyp.PRAZDNE_SLOVO, tC);

        int konX = zacX + slovo.length();
        tC.setPozicia(new Pozicia(konX, inx.getY()));
        return new Slovo(inx, slovo);
    }

    public static String predcitajSlovo(String riadok, int x) {
        int konX = najdiKoniecSlova(riadok, x);
        if ( x >= konX) {
            return "";
        }
        return riadok.substring(x, konX);
    }

    private static int najdiKoniecSlova(String riadok, int x)  {
        if (x>=riadok.length() ) return x;
        int konX = x;
         //kym sa nerovnas zakazanym znakom ani operatorom, ani koncu riadka, tak v pohode
        do {
            char p = riadok.charAt( konX);
            if (!TextUtils.jePismeno(p) && !TextUtils.jeCislo(p))
                return konX;

            konX++;
        } while ( konX<riadok.length() );

        return konX;
    }

}
