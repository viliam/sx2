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

package sk.wlio.sx2.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.TextReader;

public class ProgramReader implements TextReader<Program> {

    public Program read(TextContext tC)  {
        Program program = new Program( tC.getPosition());

        boolean foundIt;
        do {
           foundIt = false;
           if ( tC.isPrefixDeclarationVariable()) {
               program.addVariable(Readers.decVariable().read(tC));
               foundIt = true;
           }

           if ( tC.isPrefixDeclarationCommand() ) {
               program.addCommand(Readers.decCommand().read(tC));
               foundIt = true;
           }
        }  while ( foundIt);

        if (!tC.isEndOfFile())
            throw SxException.create(SxExTyp.EXPECTED_DECLARATION, tC);

        return program;
    }
}