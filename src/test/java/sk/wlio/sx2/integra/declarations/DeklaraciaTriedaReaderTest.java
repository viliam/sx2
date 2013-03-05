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

package sk.wlio.sx2.integra.declarations;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.integra.TestAbstract;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class DeclarationTriedaReaderTest extends TestAbstract<Program> {

    public DeclarationTriedaReaderTest() {
        super( new ProgramReader());
    }

    @Override
    protected String[] getGoodSentences() {
        return new String[] {
             " bool ahoj() { aReturn 3; } integer ahoj;"
        };
    }

    @Override
    protected String[] getWrongSentences() {
        return new String[] {
            " bool ahoj() { aReturn 3; } integer ahoj { nie = 3 ;"
        };
    }
}
