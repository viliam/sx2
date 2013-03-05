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

package sk.wlio.sx2.unit.readers.instrukcia;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.statement.Assignment;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.statement.AssignmentReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.*;

public class PriradenieReaderTest extends AbstractReaderTest {

    @Test
    public void testPriradenie()  {
        new TestTemplate<Assignment>(sb, new AssignmentReader()) {
            @Override
            public void nastavReader() {
                mr.variable().setPosun( 1, 0 );
                mr.variable().setVystup( new Variable( new Word( new Position(1,1)) ) );
                mr.opAssignment().setPosun( 1, 0 );
                mr.expression().setPosun( 1, 0 );
                mr.comma().setPosun(1,0);
            }
        }.run("c=4;", "variable;opAssigment;expression;comma;");
    }

    @Test
    public void testFail()  {
        try {
            mr.variable().setPosun( 1, 0 );
            mr.variable().setVystup( new Variable( new Word( new Position(1,1)) ) );

            new AssignmentReader().read(new TextContext("c + 4;"));

            fail();
        } catch (SxException ex) {

        }
    }

}
