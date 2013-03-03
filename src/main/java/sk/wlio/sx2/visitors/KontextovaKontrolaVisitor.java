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
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.DataValue;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.beans.vyraz.VyrazVzatvorke;
import sk.wlio.sx2.beans.vyraz.VyrazZlozeny;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.IWord;
import sk.wlio.sx2.interfaces.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KontextovaKontrolaVisitor implements IVisitor {

    protected final Map<String, DeclarationVariable> mapDekPremennej = new HashMap<String, DeclarationVariable>();
    protected final Map<String, DeclarationCommand> mapDekPrikaz = new HashMap<String, DeclarationCommand>();

    public KontextovaKontrolaVisitor () {    }

    public KontextovaKontrolaVisitor ( Map<String, DeclarationVariable> mapDekPremennej,
                              Map<String, DeclarationCommand> mapDekPrikaz ) {
        this.mapDekPremennej.putAll( mapDekPremennej);
        this.mapDekPrikaz.putAll(mapDekPrikaz);
    }

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

    public void visit(Program dekTrieda) {
        for (DeclarationVariable dekPremennej : dekTrieda.getMapPremenna().values() ) {
            if ( mapDekPremennej.containsKey( dekPremennej.getNazov().toString()))
                throw SxException.create(SxExTyp.PREMENNA_UZ_EXISTUJE, dekPremennej.getPosition());
            pridajPremennu( dekPremennej);
        }

        for (DeclarationCommand dekPrikaz : dekTrieda.getMapPrikaz().values() )  {
            if ( mapDekPrikaz.containsKey( dekPrikaz.getNazov().toString() ))
                throw SxException.create(SxExTyp.PRIKAZ_UZ_EXISTUJE, dekPrikaz.getPosition());
            pridajPrikaz( dekPrikaz);
        }

        for (DeclarationVariable dekPremennej : dekTrieda.getMapPremenna().values() )
            dekPremennej.visit(this);

        for (DeclarationCommand dekPrikaz : dekTrieda.getMapPrikaz().values() )
            dekPrikaz.visit(this);

    }

    public void visit(DeclarationVariable dekPremennej) {
        this.pridajPremennu( dekPremennej);
        //ak pripradenie, tak skontroluj
        Assignment assignment = dekPremennej.getAssignment();
        if (assignment != null)
            assignment.visit( this);
    }

    public void visit(DeclarationCommand dekPrikaz) {
        //deklaracia parametrov rozsiri o nove premenne
        //preto kopirujem do noveho visitora, aby povodny zostal
        //zachovany
        pridajPrikaz( dekPrikaz);
        KontextovaKontrolaVisitor dekVisitor = new KontextovaKontrolaVisitor( mapDekPremennej, mapDekPrikaz);

        dekPrikaz.getDekParam().visit( dekVisitor);
        dekPrikaz.getTelo().visit( dekVisitor);
    }

    public void visit(DeclarationParameter dekParameter) {
        for (DeclarationVariable dekPremenna: dekParameter.getLiDekPremennej()) {
            dekPremenna.visit(this);
        }
    }

    public void visit(Block block) {
        KontextovaKontrolaVisitor dekVisitor = new KontextovaKontrolaVisitor( mapDekPremennej, mapDekPrikaz);

        for (Statement statement : block.getInstrukcie())
            statement.visit(dekVisitor);
    }

    public void visit(Command command) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeclarationCommand dekPrikaz = mapDekPrikaz.get( command.getNazov().getObsah());
        if (dekPrikaz == null)
            throw SxException.create(SxExTyp.NEZNAMY_PRIKAZ, command.getPosition());
        //nastav typ prikazu na zaklade deklaracie
        command.setExpType(dekPrikaz.getDatovyTyp().getExpType());

        //skontrolu pocet parametrov
        List<DeclarationVariable> liDekPremennej = dekPrikaz.getDekParam().getLiDekPremennej();
        List<IExpression> liParemetre = command.getParameters().getParametre();
        if ( liDekPremennej.size() != liParemetre.size())
            throw SxException.create(SxExTyp.NESPRAVNY_POCET_PARAMETROV, command.getPosition());

        //skontroluj typ parametrov
        for (int i=0; i<liDekPremennej.size(); i++)
            if ( liDekPremennej.get(i).getDatovyTyp().getExpType() != liParemetre.get(i).getExpType())
                throw SxException.create( SxExTyp.ZLY_DATOVY_TYP, liParemetre.get(i).getPosition());

        command.getParameters().visit(this);
    }

    public void visit(Parameters parameters) {
        for (IExpression expression : parameters.getParametre() )
            expression.visit( this);
    }

    public void visit(Variable variable) {
        //skontroluj ci prikaz existuje
        //pridaj ho do mnoziny znamych prikazov
        DeclarationVariable dekPremennej = mapDekPremennej.get( variable.getNazov().getObsah());
        if (dekPremennej == null)
            throw SxException.create(SxExTyp.NEZNAMA_PREMENNA, variable.getPosition());
        //nastav typ prikazu na zaklade deklaracie
        variable.setExpType(dekPremennej.getDatovyTyp().getExpType());
    }

    public void visit(Assignment assignment) {
        IExpression expression = assignment.getExpression();
        expression.visit(this);

        Variable variable = assignment.getVariable();
        if (variable.getExpType() != expression.getExpType() )
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, expression.getPosition());
    }

    public void visit(VyrazZlozeny vyrazZlozeny) {
        Enums.ExpType ocakavanyTyp = vyrazZlozeny.getExpType();
        if ( Enums.ExpType.COMPARISON.equals(ocakavanyTyp))
            ocakavanyTyp = Enums.ExpType.INT;

        IExpression v1 = vyrazZlozeny.getV1();
        if ( !Enums.ExpType.UNKNOWN.equals(v1.getExpType()) && !v1.getExpType().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v1.getPosition());

        IExpression v2 = vyrazZlozeny.getV2();
        if ( !Enums.ExpType.UNKNOWN.equals(v2.getExpType()) && !v2.getExpType().equals( ocakavanyTyp))
            throw SxException.create(SxExTyp.ZLY_DATOVY_TYP, v2.getPosition());

        v1.visit(this);
        v2.visit(this);
    }

    public void visit(VyrazVzatvorke vyrazVzatvorke) {
        vyrazVzatvorke.getV().visit(this);
    }

    public void visit(Return aReturn) {
        aReturn.getVyraz().visit( this);
    }

    public void visit(Condition condition) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Operator operator) {}

    public void visit(DataType datovyTyp) {}
    public void visit(DataValue dataValue) {}

    public void visit(Int anInt) {}

    public void visit(IWord word) {
        throw new IllegalStateException("Nezadeklarovana visit metoda pre typ : " + word.getClass().getName());
    }

}
