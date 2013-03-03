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

package sk.wlio.sx2.unit.readers.mock;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.IReaders;
import sk.wlio.sx2.interfaces.TextReader;

public class MockReaders implements IReaders {

    StringBuffer postupVolania;

    protected MockReader<Int> mCislo;
    protected MockReader<Word> mSlovo;

    protected MockReader<Operator> mOpVyraz;
    protected MockReader<Operator> mOpPriradenia;

    protected MockReader<IExpression> mVyraz;
    protected MockReader<IExpression> mVrzArtim;
    protected MockReader<IExpression> mVrzBool;
    protected MockReader<IExpression> mVrzJednoduchy;
    protected MockReader<IExpression> mVrzZatvorka;
    protected MockReader<IExpression> mVrzAritmVzatvorke;
    protected MockReader<IExpression> mVrzBoolVzatvorke;

    protected MockReader<Bracket> mZatvorka;
    protected MockReader<Comma> mCiarka;

    protected MockReader<DataType> mDatovyTyp;
    protected MockReader<InstructionWord> mInstrukciaSlovo;

    protected MockReader<Statement> mInstrukcia;
    protected MockReader<Assignment> mPriradenie;
    protected MockReader<Block> mBlok;
    protected MockReader<Variable> mPremenna;
    protected MockReader<DeclarationVariable> mDekPremennej;
    protected MockReader<Command> mPrikaz;
    protected MockReader<DeclarationCommand> mDekPrikaz;
    protected MockReader<Program> mDekTrieda;
    protected MockReader<Parameters> mParametre;
    protected MockReader<DeclarationParameter> mDekParameter;
    protected MockReader<Return> mVrat;
    protected MockReader<Condition> mPodmienka;


    public MockReaders(StringBuffer postupVolania) {
        this.postupVolania = postupVolania;

        mCislo = new MockReader<Int>("int", postupVolania);
        mSlovo= new MockReader<Word>("word", postupVolania);

        mOpVyraz = new MockReader<Operator>("opExp", postupVolania);
        mOpPriradenia= new MockReader<Operator>("opAssigment", postupVolania);

        mVyraz= new MockReader<IExpression>("expression", postupVolania);
        mVrzArtim= new MockReader<IExpression>("expAritm", postupVolania);
        mVrzBool= new MockReader<IExpression>("expBool", postupVolania);
        mVrzJednoduchy = new MockReader<IExpression>("expSimple", postupVolania);
        mVrzZatvorka = new MockReader<IExpression>("expBracket", postupVolania);
        mVrzAritmVzatvorke= new MockReader<IExpression>("expAritmVzatvorke", postupVolania);
        mVrzBoolVzatvorke= new MockReader<IExpression>("vrzBoolVzatvorke", postupVolania);

        mZatvorka = new MockReader<Bracket>("bracket", postupVolania);
        mCiarka= new MockReader<Comma>("comma", postupVolania);

        mInstrukciaSlovo= new MockReader<InstructionWord>("statmentWord", postupVolania);
        mDatovyTyp= new MockReader<DataType>("dataType", postupVolania);

        mInstrukcia= new MockReader<Statement>("statement", postupVolania);
        mPriradenie= new MockReader<Assignment>("assigment", postupVolania);
        mBlok= new MockReader<Block>("block", postupVolania);
        mPremenna =new MockReader<Variable>("variable", postupVolania);
        mDekPremennej = new MockReader<DeclarationVariable>("decVariable", postupVolania);
        mPrikaz= new MockReader<Command>("command", postupVolania);
        mDekPrikaz= new MockReader<DeclarationCommand>("decCommand", postupVolania);
        mParametre= new MockReader<Parameters>("parameters", postupVolania);
        mDekParameter= new MockReader<DeclarationParameter>("decParameter", postupVolania);
        mVrat=new MockReader<Return>("return", postupVolania);
        mPodmienka = new MockReader<Condition>("if", postupVolania);
    }

    public MockReader<Word> slovo() {  return  mSlovo; }
    public MockReader<Int> cislo() {  return   mCislo; }

    public MockReader<Operator> opVyraz() {  return mOpVyraz; }
    public MockReader<Operator> opPriradenia() {  return mOpPriradenia; }

    public MockReader<IExpression> vyraz() {  return   mVyraz; }
    public MockReader<IExpression> vrzAritm() {  return mVrzArtim; }
    public MockReader<IExpression> vrzBool() {  return mVrzBool; }
    public MockReader<IExpression> vrzJednduchy() {  return mVrzJednoduchy; }
    public MockReader<IExpression> vrzVzatvorke() {  return mVrzZatvorka; }

    public MockReader<Bracket> zatvorka() {  return mZatvorka; }
    public MockReader<Comma> ciarka  () {  return mCiarka; }

    public MockReader<InstructionWord> instrukciaSlovo() {  return mInstrukciaSlovo; }
    public MockReader<DataType> datovyTyp() {  return mDatovyTyp; }

    public MockReader<Statement> instrukcia() {  return mInstrukcia; }
    public MockReader<Assignment> priradenie() {  return mPriradenie; }
    public MockReader<Block> blok() {  return mBlok; }
    public MockReader<Variable> premenna() {  return mPremenna; }
    public MockReader<DeclarationVariable> dekPremennej() {  return mDekPremennej; }
    public MockReader<Command> prikaz() {  return mPrikaz; }
    public MockReader<DeclarationCommand> dekPrikaz () {  return mDekPrikaz; }

    public TextReader<Program> dekTrieda() {
        return mDekTrieda;
    }

    public MockReader<Parameters> parametre() {  return mParametre;}
    public MockReader<DeclarationParameter> dekParameter() {  return mDekParameter; }
    public MockReader<Return> vrat() {  return mVrat; }

    public TextReader<Condition> podmienka() { return mPodmienka;    }

}
