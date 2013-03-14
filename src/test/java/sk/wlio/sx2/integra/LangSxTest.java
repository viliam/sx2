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
import sk.wlio.sx2.parsers.ProgramReader;
import sk.wlio.sx2.visitors.ContextAnalysisVisitor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LangSxTest extends TestAbstract<Program> {

    final String DIR = LangSxTest.class.getResource(".").getFile();

    public LangSxTest() {
        super(new ProgramReader(), new TestVisitor<Program>() {
            public void visit(TextContext tC, Program program) {
                ContextAnalysisVisitor visitor = new ContextAnalysisVisitor();
                visitor.visit(program);
            }
        } );
    }


    @Override
    protected String[] getGoodSentences() {
        List<String> li = readGoodPrograms();
        return li.toArray(new String[li.size()]);
    }

    @Override
    protected String[] getWrongSentences() {
        Map<String, String> map = readWrongPrograms();
        List<String> li =new ArrayList<String>( map.keySet());
        return li.toArray( new String[li.size()]);
    }

    private List<String> readGoodPrograms() {
        List<String> li = new ArrayList<String>();
        for (File file :FileUtils.listFiles( new File( DIR), new String[] {"sx"}, false) ) {
            if (!file.getName().contains("_"))
                try {
                    li.add( FileUtils.readFileToString( file));
                } catch (IOException e) {
                    System.out.println("Can't read a file : " + file);
                }
        }

        return li;
    }

    private Map<String, String> readWrongPrograms() {
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
