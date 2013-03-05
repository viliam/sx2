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

public class Readers {

    private static IParsers parsers = new ParsersImpl();

    public static void recreate() {
        Readers.parsers = new ParsersImpl();
    }

    public static void recreateReaders(IParsers parsers) {
        if (parsers == null) throw new IllegalArgumentException("parsers can't be null..");
        Readers.parsers = parsers;
    }

    public static SxParser<Word> word() { return parsers.word(); }
    public static SxParser<Int> integer() { return parsers.integer(); }
    public static SxParser<Operator> opExpr() { return parsers.opExpr(); }
    public static SxParser<Operator> opAssigment() { return parsers.opAssignment(); }
    public static SxParser<IExpression> expression() { return parsers.expression(); }
    public static SxParser<IExpression> exprSimple() { return parsers.exprSimple(); }
    public static SxParser<IExpression> exprBracket() { return parsers.exprBracket(); }
    public static SxParser<Bracket> bracket() { return parsers.bracket(); }
    public static SxParser<Comma> comma() { return parsers.comma(); }

    public static SxParser<DataType> dataType() { return parsers.dataType(); }
    public static SxParser<StatementWord> statementWord() { return parsers.statementWord();}

    public static SxParser<Statement> statment() { return parsers.statement(); }
    public static SxParser<Assignment> assignment() { return parsers.assignment(); }
    public static SxParser<Block> block() { return parsers.block(); }
    public static SxParser<Variable> variable() { return parsers.variable(); }
    public static SxParser<Condition> condition() { return parsers.condition(); }
    public static SxParser<DeclarationVariable> decVariable() { return parsers.decVariable(); }
    public static SxParser<Command> command() { return parsers.command(); }
    public static SxParser<DeclarationCommand> decCommand() { return parsers.decCommand(); }
    public static SxParser<Parameters> parameters() { return parsers.paramters(); }
    public static SxParser<DeclarationParameter> decParameters() { return parsers.decParamters(); }
    public static SxParser<Program> program() { return parsers.program();}
    public static SxParser<Return> aReturn() { return parsers.aReturn(); }


}
