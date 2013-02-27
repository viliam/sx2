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

package sk.wlio.sx2.integra;

import org.apache.commons.io.FileUtils;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.visitors.KontextovaKontrolaVisitor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JazykSxTest extends TestAbstract<Program> {

    final String DIR = "/C:/jIdea/sx2/test/test/integra/";

    public JazykSxTest() {
        super(new ProgramReader(), new TestVisitor<Program>() {
            public void visit(TextContext tC, Program dekTrieda) {
                KontextovaKontrolaVisitor visitor = new KontextovaKontrolaVisitor();
                visitor.visit(dekTrieda);
            }
        } );
    }


    @Override
    protected String[] getDobreVety() {
        List<String> li = readDobreProgramy();
        return li.toArray(new String[li.size()]);
    }

    @Override
    protected String[] getChybneVety() {
        Map<String, String> map = readChybneProgramy();
        List<String> li =new ArrayList<String>( map.keySet());
        return li.toArray( new String[li.size()]);
    }

    private List<String> readDobreProgramy() {
        List<String> li = new ArrayList<String>();
        for (File file :FileUtils.listFiles( new File( DIR), new String[] {"sx"}, false) ) {
            if (!file.getName().contains("_"))
                try {
                    li.add( FileUtils.readFileToString( file));
                } catch (IOException e) {
                    System.out.println("Neviem citat subor : " + file);
                }
        }

        return li;
    }

    private Map<String, String> readChybneProgramy() {
        Map<String, String> map = new HashMap<String, String>();
        for (File file :FileUtils.listFiles( new File( DIR), new String[] {"sx"}, false) ) {
            if (file.getName().contains("_")) {
                String fName = file.getName();
                String sxTyp = fName.substring(fName.indexOf("_"), fName.indexOf("."));
                try {
                    map.put( FileUtils.readFileToString( file) , sxTyp);
                } catch (IOException e) {
                    System.out.println("Neviem citat subor : " + file);
                }
            }
        }
        return map;
    }

}
