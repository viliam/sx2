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

package sk.wlio.sx2.unit.visitors;

import org.testng.annotations.Test;
import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.visitors.DeklaracieVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertEquals;

@Deprecated
public class DeklaracieVisitorTest {

    @Test
    public void testDekPremenna() {
        DeclarationVariable dekPremennej = new DeclarationVariable(
                new DataType( null, "bool"),
                new Word(null, "ahoj"), new Comma(null, null)
        );


        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        visitor.visit(dekPremennej);

        String nazov = dekPremennej.getNazov().toString();
        assertEquals(dekPremennej.getDatovyTyp(),
                visitor.getPremenna(nazov).getDatovyTyp());
    }

    @Test
    public void testVisitDekPrikazu() {
        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        Word nazov = new Word(null, "prikaz");
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Instrukcia[0], new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        DeklaracieVisitor visitor = new DeklaracieVisitor();
        visitor.visit(dekPrikaz);

        DeclarationCommand dekPrikazPamet = visitor.getPrikaz("prikaz");
        assertNotNull( dekPrikazPamet);
        assertEquals( Enums.VyrazTyp.CISLO, dekPrikazPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( dekPrikaz.getNazov().getObsah(), dekPrikazPamet.getNazov().getObsah());
    }

    @Test
    public void testDeklaraciaParameter() {
        DataType datovyTyp = new DataType( new Word(null, "bool"));
        datovyTyp.setTyp( Enums.VyrazTyp.BOOL);
        Word nazov = new Word(null, "ahoj");
        DeclarationVariable d1 = new DeclarationVariable(datovyTyp, nazov, new Comma(null, null) );

        List<DeclarationVariable> liDekPremennaj = new ArrayList<DeclarationVariable>();
        liDekPremennaj.add( d1);
        DeclarationParameter dekParameter = new DeclarationParameter(null, null, null, liDekPremennaj);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        visitor.visit(dekParameter);

        DeclarationVariable dekPremennejPamet = visitor.getPremenna("ahoj");
        assertNotNull( dekPremennejPamet);
        assertEquals( Enums.VyrazTyp.BOOL, dekPremennejPamet.getDatovyTyp().getVyrazTyp() );
        assertEquals( d1.getNazov().getObsah(), dekPremennejPamet.getNazov().getObsah());
    }

    @Test
    public void testVisitPrikaz() {
        Word nazov = new Word(null, "prikaz");
        Parameters parameters = new Parameters(new Bracket(null, null), null);
        Command command = new Command(nazov, parameters);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        try {
            visitor.visit(command);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMY_PRIKAZ, ex.getTyp());
        }

        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeclarationParameter dekParameter =new DeclarationParameter(null, null);
        Block telo = new Block(new Instrukcia[] { new Return(new InstructionWord( new Position(0,0), null), null, null) }
                             , new Bracket(null, null), null);
        DeclarationCommand dekPrikaz =
                new DeclarationCommand(datovyTyp, nazov, dekParameter, telo);

        visitor.pridajPrikaz(dekPrikaz);

        visitor.visit(command);
        assertEquals( command.getVyrazTyp(), dekPrikaz.getDatovyTyp().getVyrazTyp());
    }

    @Test
    public void testVisitPremenna() {
        Word nazov = new Word(null, "ahoj");
        Variable variable = new Variable( nazov);

        DeklaracieVisitor visitor = new DeklaracieVisitor( );
        try {
            visitor.visit(variable);
            fail();
        } catch (SxException ex) {
            assertEquals(SxExTyp.NEZNAMA_PREMENNA, ex.getTyp());
        }

        DataType datovyTyp = new DataType(new Word(null, "cislo"));
        datovyTyp.setTyp( Enums.VyrazTyp.CISLO);
        DeclarationVariable dekPremennej =
                new DeclarationVariable(datovyTyp, nazov, new Comma(null, null));

        visitor.pridajPremennu( dekPremennej);

        visitor.visit(variable);
        assertEquals( variable.getVyrazTyp(), dekPremennej.getDatovyTyp().getVyrazTyp());
    }


}
