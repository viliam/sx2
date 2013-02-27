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
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.readers.instruction.*;
import sk.wlio.sx2.readers.symbol.*;
import sk.wlio.sx2.readers.vyraz.*;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazAritmVzatvorkeReader;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazVzatvorkeReader;
import sk.wlio.sx2.readers.zakazaneslova.DatovyTypReader;
import sk.wlio.sx2.readers.zakazaneslova.InstrukciaSlovoReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class ReadersImpl implements  IReaders {
    
    public TextReader<Word> slovo() {  return  new SlovoReader(); }
    public TextReader<Int> cislo() {  return   new CisloReader(); }
    public TextReader<Operator> opAritm() {  return   new OperatorAritmReader(); }
    public TextReader<Operator> opBool() {  return   new OperatorBoolReader(); }
    public TextReader<Operator> opPorovnanie() {  return   new OperatorPorovnanieReader(); }
    public TextReader<Operator> opPriradenia() {  return   new OperatorPriradenieReader(); }
    public TextReader<IVyraz> vyraz() {  return   new VyrazReader(); }
    public TextReader<IVyraz> vrzAritm() {  return   new VyrazAritmReader(); }
    public TextReader<IVyraz> vrzBool() {  return   new VyrazBoolReader(); }
    public TextReader<IVyraz> vrzJednduchy() {  return   new VyrazJednoduchyReader(); }
    public TextReader<IVyraz> vrzVzatvorke() {  return   new VyrazVzatvorkeReader(); }
    public TextReader<IVyraz> vrzAritmVzatvorke() {  return   new VyrazAritmVzatvorkeReader(); }
    public TextReader<Parenthesis> zatvorka() {  return   new ZatvorkaReader(); }
    public TextReader<Comma> ciarka  () {  return   new CiarkaReader(); }

    public TextReader<DataType> datovyTyp() {  return   new DatovyTypReader(); }
    public TextReader<InstructionWord> instrukciaSlovo() { return new InstrukciaSlovoReader(); }

    public TextReader<Instrukcia> instrukcia() {  return   new InstructionReader(); }
    public TextReader<Assignment> priradenie() {  return   new AssignmentReader(); }
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
