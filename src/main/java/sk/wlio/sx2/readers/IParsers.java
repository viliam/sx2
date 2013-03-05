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

public interface IParsers {
    
    public SxParser<Word> word();
    public SxParser<Int> integer();
    public SxParser<Operator> opExpr();
    public SxParser<Operator> opAssignment();
    public SxParser<IExpression> expression();
    public SxParser<IExpression> exprSimple();
    public SxParser<IExpression> exprBracket();
    public SxParser<Bracket> bracket();
    public SxParser<Comma> comma();

    public SxParser<DataType> dataType();

    public SxParser<Statement> statement();
    public SxParser<Assignment> assignment();
    public SxParser<Block> block();
    public SxParser<Variable> variable();
    public SxParser<DeclarationVariable> decVariable();
    public SxParser<Command> command();
    public SxParser<DeclarationCommand> decCommand();
    public SxParser<Program> program();
    public SxParser<Parameters> paramters();
    public SxParser<DeclarationParameter> decParamters();
    public SxParser<Return> aReturn();
    public SxParser<Condition> condition();

    public SxParser<StatementWord> statementWord();


}
