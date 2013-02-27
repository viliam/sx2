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

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.beans.rezervovaneslova.DatovaHodnota;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.Instrukcia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KontextovaKontrolaVisitor implements IVisitor {

    protected final Map<String, DeklaraciaPremennej> mapDekPremennej = new HashMap<String, DeklaraciaPremennej>();
    protected final Map<String, DeklaraciaPrikaz> mapDekPrikaz = new HashMap<String, DeklaraciaPrikaz>();

    public KontextovaKontrolaVisitor () {    }

    public KontextovaKontrolaVisitor ( Map<String, DeklaraciaPremennej> mapDekPremennej,
                              Map<String, DeklaraciaPrikaz> mapDekPrikaz ) {
        this.mapDekPremennej.putAll( mapDekPremennej);
        this.mapDekPrikaz.putAll(mapDekPrikaz);
    }

    public void pridajPremennu(DeklaraciaPremennej dekPremennej) {
       mapDekPremennej.put( dekPremennej.getNazov().getObsah(), dekPremennej);
    }

    public void pridajPrikaz(DeklaraciaPrikaz dekPrikaz) {
       mapDekPrikaz.put( dekPrikaz.getNazov().getObsah(), dekPrikaz);
    }

    public DeklaraciaPremennej getPremenna(String nazov) {
        return mapDekPremennej.get(nazov);
    }

    public DeklaraciaPrikaz getPrikaz(String nazov) {
        return mapDekPrikaz.get( nazov);
    }

    public void visit(Program dekTrieda) {
        for (DeklaraciaPremennej dekPremennej : dekTrieda.getMapPremenna().values() ) {
            if ( mapDekPremennej.containsKey( dekPremennej.getNazov().toString()))
                throw SxException.create(SxExTyp.PREMENNA_UZ_EXISTUJE, dekPremennej.getPozicia());
            pridajPremennu( dekPremennej);
        }

        for (DeklaraciaPrikaz dekPrikaz : dekTrieda.getMapPrikaz().values() )  {
            if ( mapDekPrikaz.containsKey( dekPrikaz.getNazov().toString() ))
                throw SxException.create(SxExTyp.PRIKAZ_UZ_EXISTUJE, dekPrikaz.getPozicia());
            pridajPrikaz( dekPrikaz);
        }

        for (DeklaraciaPremennej dekPremennej : dekTrieda.getMapPremenna().values() )
            dekPremennej.visit(this);

        for (DeklaraciaPrikaz dekPrikaz : dekTrieda.getMapPrikaz().values() )
            dekPrikaz.visit(this);

    }

    public void visit(DeklaraciaPremennej dekPremennej) {
        this.pridajPremennu( dekPremennej);
        //ak pripradenie, tak skontroluj
        Priradenie priradenie = dekPremennej.getPriradenie();
        if (priradenie != null)
            priradenie.visit( this);
    }

    public void visit(DeklaraciaPrikaz dekPrikaz) {
        //deklaracia parametrov rozsiri o nove premenne
        //preto kopirujem do noveho visitora, aby povodny zostal
        //zachovany
        pridajPrikaz( dekPrikaz);
        KontextovaKontrolaVisitor dekVisitor = new KontextovaKontrolaVisitor( mapDekPremennej, mapDekPrikaz);

        dekPrikaz.getDekParam().visit( dekVisitor);
        dekPrikaz.getTelo().visit( dekVisitor);
    }

    public void visit(DeklaraciaParameter dekParameter) {
        for (DeklaraciaPremennej dekPremenna: dekParameter.getLiDekPremennej()) {
            dekPremenna.visit(this);
        }
    }

    public void visit(Blok blok) {
        KontextovaKontrolaVisitor dekVisitor = new KontextovaKontrolaVisitor( mapDekPremennej, mapDekPrikaz);

        for (Instrukcia instrukcia : blok.getInstrukcie())
            instrukcia.visit(dekVisitor);
    }

    public void visit(Prikaz prikaz) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeklaraciaPrikaz dekPrikaz = mapDekPrikaz.get( prikaz.getNazov().getObsah());
        if (dekPrikaz == null)
            throw SxException.create(SxExTyp.NEZNAMY_PRIKAZ, prikaz.getPozicia());
        //nastav typ prikazu na zaklade deklaracie
        prikaz.setVyrazTyp( dekPrikaz.getDatovyTyp().getVyrazTyp());

        //skontrolu pocet parametrov
        List<DeklaraciaPremennej> liDekPremennej = dekPrikaz.getDekParam().getLiDekPremennej();
        List<IVyraz> liParemetre = prikaz.getParametre().getParametre();
        if ( liDekPremennej.size() != liParemetre.size())
            throw SxException.create(SxExTyp.NESPRAVNY_POCET_PARAMETROV, prikaz.getPozicia());

        //skontroluj typ parametrov
        for (int i=0; i<liDekPremennej.size(); i++)
            if ( liDekPremennej.get(i).getDatovyTyp().getVyrazTyp() != liParemetre.get(i).getVyrazTyp())
                throw SxException.create( SxExTyp.ZLY_DATOVY_TYP, liParemetre.get(i).getPozicia());

        prikaz.getParametre().visit(this);
    }

    public void visit(Parametre parametre) {
        for (IVyraz vyraz : parametre.getParametre() )
            vyraz.visit( this);
    }

    public void visit(Premenna premenna) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeklaraciaPremennej dekPremennej = mapDekPremennej.get( premenna.getNazov().getObsah());
        if (dekPremennej == null)
            throw SxException.create(SxExTyp.NEZNAMA_PREMENNA, premenna.getPozicia());
        //nastav typ prikazu na zaklade deklaracie
        premenna.setVyrazTyp( dekPremennej.getDatovyTyp().getVyrazTyp());
    }

    public void visit(Priradenie priradenie) {
        IVyraz vyraz = priradenie.getVyraz();
        vyraz.visit(this);

        Premenna premenna = priradenie.getPremenna();
        if (premenna.getVyrazTyp() != vyraz.getVyrazTyp() )
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, vyraz.getPozicia());
    }

    public void visit(VyrazZlozeny vyrazZlozeny) {
        Enums.VyrazTyp ocakavanyTyp = vyrazZlozeny.getVyrazTyp();
        if ( Enums.VyrazTyp.POROVNANIE.equals(ocakavanyTyp))
            ocakavanyTyp = Enums.VyrazTyp.CISLO;

        IVyraz v1 = vyrazZlozeny.getV1();
        if ( !Enums.VyrazTyp.NEURCENY.equals(v1.getVyrazTyp()) && !v1.getVyrazTyp().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v1.getPozicia());

        IVyraz v2 = vyrazZlozeny.getV2();
        if ( !Enums.VyrazTyp.NEURCENY.equals(v2.getVyrazTyp()) && !v2.getVyrazTyp().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v2.getPozicia());

        v1.visit(this);
        v2.visit(this);
    }

    public void visit(VyrazVzatvorke vyrazVzatvorke) {
        vyrazVzatvorke.getV().visit(this);
    }

    public void visit(Vrat vrat) {
        vrat.getVyraz().visit( this);
    }

    public void visit(Operator operator) {}

    public void visit(DatovyTyp datovyTyp) {}
    public void visit(DatovaHodnota datovaHodnota) {}

    public void visit(Cislo cislo) {}

    public void visit(ISlovo slovo) {
        throw new IllegalStateException("Nezadeklarovana visit metoda pre typ : " + slovo.getClass().getName());
    }

}
