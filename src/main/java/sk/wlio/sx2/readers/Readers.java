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

public class Readers {

    private static IReaders readers = new ReadersImpl();

    public static void recreate() {
        Readers.readers = new ReadersImpl();
    }

    public static void recreateReaders(IReaders readers) {
        if (readers == null) throw new IllegalArgumentException("readers can't be null..");
        Readers.readers = readers;
    }

    public static TextReader<Word> slovo() { return readers.word(); }
    public static TextReader<Int> cislo() { return readers.integer(); }
    public static TextReader<Operator> opVyraz() { return readers.opExpr(); }
    public static TextReader<Operator> opPriradenia() { return readers.opAssignment(); }
    public static TextReader<IExpression> vyraz() { return readers.expression(); }
    public static TextReader<IExpression> vrzJednduchy() { return readers.expSimple(); }
    public static TextReader<IExpression> vrzVzatvorke() { return readers.expBracket(); }
    public static TextReader<Bracket> zatvorka() { return readers.bracket(); }
    public static TextReader<Comma> ciarka() { return readers.comma(); }

    public static TextReader<DataType> datovyTyp() { return readers.dataType(); }
    public static TextReader<StatementWord> instrukciaSlovo() { return readers.statementWord();}

    public static TextReader<Statement> instrukcia() { return readers.statement(); }
    public static TextReader<Assignment> priradenie() { return readers.assignment(); }
    public static TextReader<Block> blok() { return readers.block(); }
    public static TextReader<Variable> premena() { return readers.variable(); }
    public static TextReader<Condition> podmienka() { return readers.condition(); }
    public static TextReader<DeclarationVariable> dekPremennej() { return readers.decVariable(); }
    public static TextReader<Command> prikaz() { return readers.command(); }
    public static TextReader<DeclarationCommand> dekPrikaz() { return readers.decCommand(); }
    public static TextReader<Parameters> parametre() { return readers.paramters(); }
    public static TextReader<DeclarationParameter> dekParameter() { return readers.decParamters(); }
    public static TextReader<Program> dekTrieda() { return readers.program();}
    public static TextReader<Return> vrat() { return readers.aReturn(); }


}
