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
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.statement.*;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.readers.rezervedwords.DataTypeReader;
import sk.wlio.sx2.readers.rezervedwords.StatementWordReader;
import sk.wlio.sx2.readers.statement.*;
import sk.wlio.sx2.readers.symbol.*;
import sk.wlio.sx2.readers.expression.*;
import sk.wlio.sx2.readers.expression.BracketExpression;
import sk.wlio.sx2.interfaces.TextReader;

public class ReadersImpl implements  IReaders {
    
    public TextReader<Word> word() {  return  new WordReader(); }
    public TextReader<Int> integer() {  return   new IntReader(); }
    public TextReader<Operator> opExpr() {  return   new OperatorExpressionReader(); }
    public TextReader<Operator> opAssignment() {  return   new OperatorPriradenieReader(); }
    public TextReader<IExpression> expression() {  return   new ExprReader(); }
    public TextReader<IExpression> exprSimple() {  return   new SimpleExprReader(); }
    public TextReader<IExpression> exprBracket() {  return   new BracketExpression(); }
    public TextReader<Bracket> bracket() {  return   new BracketReader(); }
    public TextReader<Comma> comma() {  return   new CommaReader(); }

    public TextReader<DataType> dataType() {  return   new DataTypeReader(); }
    public TextReader<StatementWord> statementWord() { return new StatementWordReader(); }

    public TextReader<Statement> statement() {  return   new StatementReader(); }
    public TextReader<Assignment> assignment() {  return   new AssignmentReader(); }
    public TextReader<Condition> condition() { return new ConditionReader(); }
    public TextReader<Block> block() {  return   new BlockReader(); }
    public TextReader<Variable> variable() {  return   new VariableReader(); }
    public TextReader<DeclarationVariable> decVariable() {  return   new DeclarationVariableReader(); }
    public TextReader<Command> command() {  return   new CommandReader(); }
    public TextReader<DeclarationCommand> decCommand() {  return   new DeclarationCommandReader(); }
    public TextReader<Program> program() {   return new ProgramReader();  }

    public TextReader<Parameters> paramters() {  return   new ParameterReader(); }
    public TextReader<DeclarationParameter> decParamters() {  return   new DeclarationParameterReader(); }
    public TextReader<Return> aReturn() {  return   new ReturnReader(); }
}
