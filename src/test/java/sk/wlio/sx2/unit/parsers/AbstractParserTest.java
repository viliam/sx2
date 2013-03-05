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

package sk.wlio.sx2.unit.parsers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.unit.parsers.mock.MockParsers;

public abstract class AbstractParserTest {

    protected StringBuffer sb;
    protected MockParsers mr;

    @BeforeMethod
    public void before() {
        sb = new StringBuffer();
        Readers.recreateReaders( mr = new MockParsers(sb));
    }

    @AfterMethod
    public void after() {
        Readers.recreate();
    }
}
