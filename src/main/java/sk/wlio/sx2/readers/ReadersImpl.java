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

package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.statement.*;
import sk.wlio.sx2.readers.symbol.*;
import sk.wlio.sx2.readers.expression.*;
import sk.wlio.sx2.readers.expression.BracketExpression;
import sk.wlio.sx2.readers.zakazaneslova.DatovyTypReader;
import sk.wlio.sx2.readers.zakazaneslova.InstrukciaSlovoReader;
import sk.wlio.sx2.interfaces.TextReader;

public class ReadersImpl implements  IReaders {
    
    public TextReader<Word> slovo() {  return  new SlovoReader(); }
    public TextReader<Int> cislo() {  return   new IntReader(); }
    public TextReader<Operator> opAritm() {  return   new OperatorAritmReader(); }
    public TextReader<Operator> opBool() {  return   new OperatorBoolReader(); }
    public TextReader<Operator> opPorovnanie() {  return   new OperatorPorovnanieReader(); }
    public TextReader<Operator> opPriradenia() {  return   new OperatorPriradenieReader(); }
    public TextReader<IExpression> vyraz() {  return   new ExprReader(); }
    public TextReader<IExpression> vrzJednduchy() {  return   new SimpleExprReader(); }
    public TextReader<IExpression> vrzVzatvorke() {  return   new BracketExpression(); }
    public TextReader<Bracket> zatvorka() {  return   new ZatvorkaReader(); }
    public TextReader<Comma> ciarka  () {  return   new CiarkaReader(); }

    public TextReader<DataType> datovyTyp() {  return   new DatovyTypReader(); }
    public TextReader<InstructionWord> instrukciaSlovo() { return new InstrukciaSlovoReader(); }

    public TextReader<Statement> instrukcia() {  return   new StatementReader(); }
    public TextReader<Assignment> priradenie() {  return   new AssignmentReader(); }
    public TextReader<Condition> podmienka() { return new ConditionReader(); }
    public TextReader<Block> blok() {  return   new BlockReader(); }
    public TextReader<Variable> premenna() {  return   new PremennaReader(); }
    public TextReader<DeclarationVariable> dekPremennej() {  return   new DeclarationVariableReader(); }
    public TextReader<Command> prikaz() {  return   new CommandReader(); }
    public TextReader<DeclarationCommand> dekPrikaz () {  return   new DeclarationCommandReader(); }
    public TextReader<Program> dekTrieda() {   return new ProgramReader();  }

    public TextReader<Parameters> parametre() {  return   new ParameterReader(); }
    public TextReader<DeclarationParameter> dekParameter() {  return   new DeclarationParameterReader(); }
    public TextReader<Return> vrat() {  return   new ReturnReader(); }
}
