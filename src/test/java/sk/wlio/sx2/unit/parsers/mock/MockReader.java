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

package sk.wlio.sx2.unit.parsers.mock;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.SxParser;

import java.util.*;

public class MockReader<E extends IWord> implements SxParser<E> {

    private String readerName;
    private StringBuffer callingSequence;

    private Queue<Position> shift;
    private Queue<E> output = null;

    public MockReader(String readerName, StringBuffer callingSequence) {
        this.readerName = readerName;
        this.callingSequence = callingSequence;
    }

    public E read(TextContext tC)  {
        tC.setPosition(makeShift(tC.getPosition()));
        callingSequence.append(readerName);
        callingSequence.append(";");
        return getOutput();
    }

    private Position makeShift(Position inx) {
        if (shift == null || shift.isEmpty()) {
            throw new IllegalStateException("Unexpected next shift. " +
                                            "Reader name  = " + this.readerName +
                                            " Position = " + inx.toString());
        }

        return inx.add( shift.remove());
    }

    public void setShift(int x, int y) {
        setShift(new int[]{x, y});
    }

    public void setShift(int... shift) {
        if (shift.length % 2 != 0 )
            throw new IllegalArgumentException("Shift must by in pairs");
        if (this.shift == null)
            this.shift =new LinkedList<Position>();
        for ( int i =0; i< shift.length; i+=2)
            this.shift.addAll( Arrays.asList(new Position(shift[i], shift[i+1])) );
    }




    protected E getOutput() {
        if (output == null || output.isEmpty())
            return null;
        return output.remove();
    }

    public void setOutput(E... e) {
        if (this.output == null)
            this.output = new LinkedList<E>();

        this.output.addAll(Arrays.asList(e));
    }
}
