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

package sk.wlio.sx2.unit.parsers.mock;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.statement.*;
import sk.wlio.sx2.beans.expression.Int;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.readers.IParsers;

public class MockParsers implements IParsers {

    StringBuffer callingSequence;

    protected MockReader<Int> mInt;
    protected MockReader<Word> mWord;

    protected MockReader<Operator> mOpExpr;
    protected MockReader<Operator> mOpAssignment;

    protected MockReader<IExpression> mExpression;
    protected MockReader<IExpression> mExprSimple;
    protected MockReader<IExpression> mExprBracket;

    protected MockReader<Bracket> mBracket;
    protected MockReader<Comma> mComma;

    protected MockReader<DataType> mDataType;
    protected MockReader<StatementWord> mStatementWord;

    protected MockReader<Statement> mStatement;
    protected MockReader<Assignment> mAssigment;
    protected MockReader<Block> mBlock;
    protected MockReader<Variable> mVariable;
    protected MockReader<DeclarationVariable> mDecVariable;
    protected MockReader<Command> mCommand;
    protected MockReader<DeclarationCommand> mDecCommand;
    protected MockReader<Program> mProgram;
    protected MockReader<Parameters> mParameters;
    protected MockReader<DeclarationParameter> mDecParameter;
    protected MockReader<Return> mReturn;
    protected MockReader<Condition> mCondition;


    public MockParsers(StringBuffer callingSequence) {
        this.callingSequence = callingSequence;

        mInt = new MockReader<Int>("int", callingSequence);
        mWord = new MockReader<Word>("word", callingSequence);

        mOpExpr = new MockReader<Operator>("opExp", callingSequence);
        mOpAssignment = new MockReader<Operator>("opAssigment", callingSequence);

        mExpression = new MockReader<IExpression>("expression", callingSequence);
        mExprSimple = new MockReader<IExpression>("exprSimple", callingSequence);
        mExprBracket = new MockReader<IExpression>("exprBracket", callingSequence);

        mBracket = new MockReader<Bracket>("bracket", callingSequence);
        mComma = new MockReader<Comma>("comma", callingSequence);

        mStatementWord = new MockReader<StatementWord>("statementWord", callingSequence);
        mDataType = new MockReader<DataType>("dataType", callingSequence);

        mStatement = new MockReader<Statement>("statement", callingSequence);
        mAssigment = new MockReader<Assignment>("assigment", callingSequence);
        mBlock = new MockReader<Block>("block", callingSequence);
        mVariable =new MockReader<Variable>("variable", callingSequence);
        mDecVariable = new MockReader<DeclarationVariable>("decVariable", callingSequence);
        mCommand = new MockReader<Command>("command", callingSequence);
        mDecCommand = new MockReader<DeclarationCommand>("decCommand", callingSequence);
        mParameters = new MockReader<Parameters>("parameters", callingSequence);
        mDecParameter = new MockReader<DeclarationParameter>("decParameter", callingSequence);
        mReturn =new MockReader<Return>("return", callingSequence);
        mCondition = new MockReader<Condition>("if", callingSequence);
    }

    public MockReader<Word> word() {  return mWord; }
    public MockReader<Int> integer() {  return mInt; }

    public MockReader<Operator> opExpr() {  return mOpExpr; }
    public MockReader<Operator> opAssignment() {  return mOpAssignment; }

    public MockReader<IExpression> expression() {  return mExpression; }
    public MockReader<IExpression> exprSimple() {  return mExprSimple; }
    public MockReader<IExpression> exprBracket() {  return mExprBracket; }

    public MockReader<Bracket> bracket() {  return mBracket; }
    public MockReader<Comma> comma() {  return mComma; }

    public MockReader<StatementWord> statementWord() {  return mStatementWord; }
    public MockReader<DataType> dataType() {  return mDataType; }

    public MockReader<Statement> statement() {  return mStatement; }
    public MockReader<Assignment> assignment() {  return mAssigment; }
    public MockReader<Block> block() {  return mBlock; }
    public MockReader<Variable> variable() {  return mVariable; }
    public MockReader<DeclarationVariable> decVariable() {  return mDecVariable; }
    public MockReader<Command> command() {  return mCommand; }
    public MockReader<DeclarationCommand> decCommand() {  return mDecCommand; }

    public SxParser<Program> program() {
        return mProgram;
    }

    public MockReader<Parameters> paramters() {  return mParameters;}
    public MockReader<DeclarationParameter> decParamters() {  return mDecParameter; }
    public MockReader<Return> aReturn() {  return mReturn; }

    public SxParser<Condition> condition() { return mCondition;    }

}
