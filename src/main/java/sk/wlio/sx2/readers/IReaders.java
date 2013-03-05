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
import sk.wlio.sx2.interfaces.TextReader;

public interface IReaders {
    
    public TextReader<Word> word();
    public TextReader<Int> integer();
    public TextReader<Operator> opExpr();
    public TextReader<Operator> opAssignment();
    public TextReader<IExpression> expression();
    public TextReader<IExpression> exprSimple();
    public TextReader<IExpression> exprBracket();
    public TextReader<Bracket> bracket();
    public TextReader<Comma> comma();

    public TextReader<DataType> dataType();

    public TextReader<Statement> statement();
    public TextReader<Assignment> assignment();
    public TextReader<Block> block();
    public TextReader<Variable> variable();
    public TextReader<DeclarationVariable> decVariable();
    public TextReader<Command> command();
    public TextReader<DeclarationCommand> decCommand();
    public TextReader<Program> program();
    public TextReader<Parameters> paramters();
    public TextReader<DeclarationParameter> decParamters();
    public TextReader<Return> aReturn();
    public TextReader<Condition> condition();

    public TextReader<StatementWord> statementWord();


}
