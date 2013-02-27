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
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.readers.instruction.CommandReader;
import sk.wlio.sx2.unit.readers.AbstractReaderTest;
import sk.wlio.sx2.unit.readers.TestTemplate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PrikazReaderTest extends AbstractReaderTest {

    @Test
    public void testPrikaz()  {
        TestTemplate<Command> tt =new TestTemplate<Command>( sb, new CommandReader()) {
            @Override public void nastavReader() {
                mr.slovo().setPosun( 4,0);
                mr.slovo().setVystup( new Word( null, "ahoj"));
                mr.parametre().setPosun( 8,0);
            }
        };
        tt.run("   ahoj(3 , p);  ","slovo;parametre;");
        Command command = tt.getVysledok();
        assertNotNull(command);
        assertEquals( "ahoj", command.getNazov().toString() );
    }

}
