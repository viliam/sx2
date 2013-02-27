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

package sk.wlio.sx2.visitors;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.Instrukcia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kazdy prikaz, kazda premenna musi byt definovane
 * Ked natrafime na referenciu premennej alebo prikazu, nastavime typ
 */

@Deprecated
public class DeklaracieVisitor extends VisitorAbstract {

    protected final Map<String, DeclarationVariable> mapDekPremennej = new HashMap<String, DeclarationVariable>();
    protected final Map<String, DeclarationCommand> mapDekPrikaz = new HashMap<String, DeclarationCommand>();


    public DeklaracieVisitor( Map<String, DeclarationVariable> mapDekPremennej,
                              Map<String, DeclarationCommand> mapDekPrikaz ) {
        this.mapDekPremennej.putAll( mapDekPremennej);
        this.mapDekPrikaz.putAll(mapDekPrikaz);
    }

    public DeklaracieVisitor() {    }


    public void pridajPremennu(DeclarationVariable dekPremennej) {
       mapDekPremennej.put( dekPremennej.getNazov().getObsah(), dekPremennej);
   }

   public void pridajPrikaz(DeclarationCommand dekPrikaz) {
       mapDekPrikaz.put( dekPrikaz.getNazov().getObsah(), dekPrikaz);
   }


    public DeclarationVariable getPremenna(String nazov) {
        return mapDekPremennej.get(nazov);
    }

    public DeclarationCommand getPrikaz(String nazov) {
        return mapDekPrikaz.get( nazov);
    }

    @Override
    public void visit(Program dekTrieda) {
        for (DeclarationVariable dekPremennej : dekTrieda.getMapPremenna().values() ) {
            if ( mapDekPremennej.containsKey( dekPremennej.getNazov().toString()))
                throw SxException.create(SxExTyp.PREMENNA_UZ_EXISTUJE, dekPremennej.getPozicia());
            pridajPremennu( dekPremennej);
        }

        for (DeclarationCommand dekPrikaz : dekTrieda.getMapPrikaz().values() )  {
            if ( mapDekPrikaz.containsKey( dekPrikaz.getNazov().toString() ))
                throw SxException.create(SxExTyp.PRIKAZ_UZ_EXISTUJE, dekPrikaz.getPozicia());
            pridajPrikaz( dekPrikaz);
        }

        super.visit( dekTrieda);
    }

    @Override
    public void visit(DeclarationVariable dekPremennej) {
        this.pridajPremennu( dekPremennej);
        //ak pripradenie, tak skontroluj
        Assignment assignment = dekPremennej.getAssignment();
        if (assignment != null)
            assignment.visit( this);
    }

    @Override
    public void visit(DeclarationCommand dekPrikaz) {
        //deklaracia parametrov rozsiri o nove premenne
        //preto kopirujem do noveho visitora, aby povodny zostal
        //zachovany
        pridajPrikaz( dekPrikaz);
        DeklaracieVisitor dekVisitor = new DeklaracieVisitor( mapDekPremennej, mapDekPrikaz);

        dekPrikaz.getDekParam().visit( dekVisitor);
        dekPrikaz.getTelo().visit( dekVisitor);
    }

    @Override
    public void visit(Block block) {
        DeklaracieVisitor dekVisitor = new DeklaracieVisitor( mapDekPremennej, mapDekPrikaz);

        for (Instrukcia instrukcia : block.getInstrukcie())
            instrukcia.visit(dekVisitor);
    }

    @Override
    public void visit(Command command) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeclarationCommand dekPrikaz = mapDekPrikaz.get( command.getNazov().getObsah());
        if (dekPrikaz == null)
            throw SxException.create(SxExTyp.NEZNAMY_PRIKAZ, command.getPozicia());
        //nastav typ prikazu na zaklade deklaracie
        command.setVyrazTyp( dekPrikaz.getDatovyTyp().getVyrazTyp());

        //skontrolu pocet parametrov
        List<DeclarationVariable> liDekPremennej = dekPrikaz.getDekParam().getLiDekPremennej();
        List<IVyraz> liParemetre = command.getParameters().getParametre();
        if ( liDekPremennej.size() != liParemetre.size())
            throw SxException.create(SxExTyp.NESPRAVNY_POCET_PARAMETROV, command.getPozicia());

        //skontroluj typ parametrov
        for (int i=0; i<liDekPremennej.size(); i++)
            if ( liDekPremennej.get(i).getDatovyTyp().getVyrazTyp() != liParemetre.get(i).getVyrazTyp())
                throw SxException.create( SxExTyp.ZLY_DATOVY_TYP, liParemetre.get(i).getPozicia());

        command.getParameters().visit(this);
    }



    @Override
    public void visit(Premenna premenna) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeclarationVariable dekPremennej = mapDekPremennej.get( premenna.getNazov().getObsah());
        if (dekPremennej == null)
            throw SxException.create(SxExTyp.NEZNAMA_PREMENNA, premenna.getPozicia());
        //nastav typ prikazu na zaklade deklaracie
        premenna.setVyrazTyp( dekPremennej.getDatovyTyp().getVyrazTyp());
    }

}
