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
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.TextReader;

//Template metod
public abstract class SymbolAbstractReader<E extends IWord> implements TextReader<E> {

    public E read(TextContext tC)  {
        try {
            Position poz = tC.findNextCharacter();
            String sSymbol = odkusniSymbol(tC);
            SymbolEnum eSymbol = SymbolEnum.makeSymbol(sSymbol);
            return create( poz, eSymbol);
        } catch (SxException ex) {
            throw SxException.create( ex.getTyp(), tC);
        }

    }

    protected abstract String[] getSymbols();
    //protected abstract E makeSymbol(String s, Bod pozicia);
    protected abstract E create(Position position, SymbolEnum oEnum) ;
    protected abstract SxExTyp getExceptionTyp();


    protected String odkusniSymbol(  TextContext tC)  {
        String[] symbols = getSymbols();

        int najdeneInx = -1, dlzka =0;   //hladam najdlhsi zhodny prefix
        String s = tC.endOfLine();
        for (int i=0 ; i<symbols.length; i++)
            if ( s.startsWith( symbols[i]) && dlzka < symbols[i].length()) {
                    dlzka = symbols[i].length() ;
                    najdeneInx = i;
                }

        if (najdeneInx!= -1) {
            tC.addXpostion(symbols[najdeneInx].length());
            return symbols[najdeneInx];
        }

        throw SxException.create( getExceptionTyp(), tC);
    }

//    public boolean jePrfx( char a) {
//        String[] symbols  = getSymbols();
//        for (int i=0; i < symbols.length; i++)
//                if ( symbols[i].charAt(0) == a  ) return true;
//        return false;
//    }

}
