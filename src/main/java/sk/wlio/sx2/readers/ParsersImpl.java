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
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.readers.rezervedwords.DataTypeReader;
import sk.wlio.sx2.readers.rezervedwords.StatementWordReader;
import sk.wlio.sx2.readers.statement.*;
import sk.wlio.sx2.readers.symbol.*;
import sk.wlio.sx2.readers.expression.*;
import sk.wlio.sx2.readers.expression.BracketExpression;

public class ParsersImpl implements IParsers {
    
    public SxParser<Word> word() {  return  new WordParser(); }
    public SxParser<Int> integer() {  return   new IntReader(); }
    public SxParser<Operator> opExpr() {  return   new OperatorExpressionParser(); }
    public SxParser<Operator> opAssignment() {  return   new OperatorAssignmentParser(); }
    public SxParser<IExpression> expression() {  return   new ExprReader(); }
    public SxParser<IExpression> exprSimple() {  return   new SimpleExprReader(); }
    public SxParser<IExpression> exprBracket() {  return   new BracketExpression(); }
    public SxParser<Bracket> bracket() {  return   new BracketParser(); }
    public SxParser<Comma> comma() {  return   new CommaParser(); }

    public SxParser<DataType> dataType() {  return   new DataTypeReader(); }
    public SxParser<StatementWord> statementWord() { return new StatementWordReader(); }

    public SxParser<Statement> statement() {  return   new StatementReader(); }
    public SxParser<Assignment> assignment() {  return   new AssignmentReader(); }
    public SxParser<Condition> condition() { return new ConditionReader(); }
    public SxParser<Block> block() {  return   new BlockReader(); }
    public SxParser<Variable> variable() {  return   new VariableReader(); }
    public SxParser<DeclarationVariable> decVariable() {  return   new DeclarationVariableReader(); }
    public SxParser<Command> command() {  return   new CommandReader(); }
    public SxParser<DeclarationCommand> decCommand() {  return   new DeclarationCommandReader(); }
    public SxParser<Program> program() {   return new ProgramReader();  }

    public SxParser<Parameters> paramters() {  return   new ParameterReader(); }
    public SxParser<DeclarationParameter> decParamters() {  return   new DeclarationParameterReader(); }
    public SxParser<Return> aReturn() {  return   new ReturnReader(); }
}
