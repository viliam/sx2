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

package sk.wlio.sx2.exception;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;

public class SxException extends RuntimeException {

    private final SxExTyp typ;
    private final Position position;

    public static SxException create( SxExTyp typ, TextContext tC)  {
        return create(typ, null,  tC);
    }

    public static SxException create( SxExTyp typ,  String message, TextContext tC) {
        Position inx = tC.getPosition();

        if (message == null) {
            if (tC.isEndOfFile())
                return new SxException(typ, inx);

            message= makeMessage(typ, inx, tC);
        }

        return new SxException( typ, message, inx);
    }

    private static String makeMessage(SxExTyp typ, Position position, TextContext tC) {
        int row = position.getY();
        int column = position.getX();

        String line = tC.getLine(row);
        char aChar = ( line.length() < column) ?
                line.charAt(column) : ' ';

        return typ + "  : " + line + "    \n char = " + aChar;

    }

    public static SxException create( SxExTyp typ, Position position)   {
        return new SxException(typ, position);
    }

    private SxException(SxExTyp typ, Position position) {
        this( typ, typ.toString(), position);
    }

    private  SxException( SxExTyp typ, String message, Position position)  {
        super(message);
        this.typ = typ;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "  \n" 
                + " row = " + position.getY() + " ,   column = " + position.getX();
    }

    public SxExTyp getType() {
        return typ;
    }


}