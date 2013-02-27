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

package sk.wlio.sx2.integra.deklaracia;

import org.testng.annotations.Test;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.instruction.Block;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.ISlovo;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class BlokReaderTest {
    
    public final static String[] zloziteBloky = new String[] {
            " { cislo p = 33; }",
            " { a = 3 + 4; vrat 43; }"
    };

    public final static String[] chybneBloky = new String[] {
        " { ahoj; } ", "{ nieco = 3; ", " inak "
    };

    @Test
    public void testZloziteBloky()  {
        for (String v: zloziteBloky) {
            Block block = citajBlok(v);
            assertNotNull(block);
        }
    }


    @Test
    public void testFail() {
        for (String v : chybneBloky)
            try {
                TextContext tC = new TextContext(v);
                citajBlok( tC, false);
                if (tC.jeKoniec() ) fail();
            } catch (SxException ex) {}
    }
    
    private Block citajBlok(String tento)  {
        TextContext tC = new TextContext(tento);
        return citajBlok(tC, true);
    }
    
    private Block citajBlok(TextContext tC, boolean checkKoniec)  {
        ISlovo slovo = Readers.blok().citaj(tC);

        assertNotNull(slovo);
        assertTrue( "Slovo nie je instancia Blok. Slovo.class = " + slovo.getClass().getName()
                , slovo instanceof Block);

        if (checkKoniec) assertTrue( tC.jeKoniec());
        return (Block) slovo;
    }
}
