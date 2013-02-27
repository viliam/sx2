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

package sk.wlio.sx2.readers.vyraz;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.VyrazAritm;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class VyrazReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        IVyraz v;

        //ak vyraz je v zatvorke, nacitam ozatvorkovany prvy vyraz o1,. samozrejme rekurzivne
        if ( tC.jePrefixZatvorkaOtovorena() )  {
            v = Readers.vrzVzatvorke().citaj(tC);
        } else   { //nie je zatvorka, odkusnem jedno slovo (prikaz, premenne, cislo, bool)
            v = Readers.vrzJednduchy().citaj(tC);
        }

        if (tC.jeKoniec())  return v;

        TextReader<Operator> opReader = null;
        if (tC.jePrefixOperatorAritm() )
            opReader = Readers.opAritm();
        if (tC.jePrefixOperatorBool())
            opReader = Readers.opBool();
        if (tC.jePrefixOperatorPorovnania())
            opReader = Readers.opPorovnanie();

        if (opReader != null)
            v = new VyrazZlozeny(v, opReader.citaj(tC), Readers.vyraz().citaj(tC));

        if (!tC.jeKoniec() )
            skontrolujKorektnyKoniecVyrazu(tC);

        return v;
    }

    private void skontrolujKorektnyKoniecVyrazu(  TextContext tC) {
        if    ( !tC.jePrefixOperator()
             && !tC.jePrefixCiarka()
             && !tC.jePrefixZatvorkaZatvorena() )
            throw SxException.create(SxExTyp.CAKAL_OPERATOR, tC);
    }
//        //operator - bool
//        if ( tC.jePrefixOperatorPorovnania() || tC.jePrefixOperatorBool() ) {
//            tC.setPozicia(inx);
//            return Readers.vrzBool().citaj(tC);
//
//        //operator - artim
//        } else if ( tC.jePrefixOperatorAritm()) {
//            Operator op = Readers.opAritm().citaj( tC);
//            IVyraz v = Readers.vrzAritm().citaj(tC);
//
//            if (  tC.jePrefixOperatorPorovnania() )  {
//                tC.setPozicia(inx);
//                return Readers.vrzBool().citaj(tC);
//            }
//            return new VyrazAritm( o1,op,v);
//        }
//        return o1;

}
