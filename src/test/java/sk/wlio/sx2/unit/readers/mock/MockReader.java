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

package sk.wlio.sx2.unit.readers.mock;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.TextReader;

import java.util.*;

public class MockReader<E extends IWord> implements TextReader<E> {

    private String readerName;
    private StringBuffer postupVolania;

    private Queue<Position> posun;
    private Queue<E> vystup = null;

    public MockReader(String readerName, StringBuffer postupVolania) {
        this.readerName = readerName;
        this.postupVolania = postupVolania;
    }

    public E read(TextContext tC)  {
        tC.setPosition(makePosun(tC.getPosition()));
        postupVolania.append( readerName);
        postupVolania.append(";");
        return getVystup();
    }

    private Position makePosun( Position inx) {
        if (posun == null || posun.isEmpty()) {
            throw new IllegalStateException("Neocakavam dalsi posun. " +
                                            "Reader name  = " + this.readerName);
        }

        return inx.add( posun.remove());
    }

    public void setPosun(int x, int y) {
        setPosun( new int[] { x,y });
    }

    public void setPosun(int... posun) {
        if (posun.length % 2 != 0 )
            throw new IllegalArgumentException("Posun musi mat parny pocet prvkov");
        if (this.posun == null)
            this.posun =new LinkedList<Position>();
        for ( int i =0; i<posun.length; i+=2)
            this.posun.addAll( Arrays.asList(new Position(posun[i], posun[i+1])) );
    }




    protected E getVystup() {
        if (vystup == null || vystup.isEmpty())
            return null;
        return vystup.remove();
    }

    public void setVystup(E... e) {
        if (this.vystup == null)
            this.vystup = new LinkedList<E>();

        this.vystup.addAll( Arrays.asList(e ));
    }
}
