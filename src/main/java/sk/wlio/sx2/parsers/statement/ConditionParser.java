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

package sk.wlio.sx2.parsers.statement;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.StatementWord;
import sk.wlio.sx2.beans.statement.Condition;
import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.Statement;
import sk.wlio.sx2.parsers.Readers;
import sk.wlio.sx2.interfaces.SxParser;

public class ConditionParser implements SxParser<Condition> {

    public Condition read(TextContext tC)        {
        StatementWord ifWord = Readers.statementWord().read(tC);
        if ( !ReservedWordEnum.IF.is(ifWord.toString())  )
            throw SxException.create( SxExTyp.EXPECTED_IF, tC);


        Bracket z1 = Readers.bracket().read(tC);
        IExpression vrzBool = Readers.expression().read(tC);
        Bracket z2 = Readers.bracket().read(tC);
        Statement statement = Readers.statment().read(tC);
        return new Condition( ifWord, vrzBool, statement, z1, z2 );
    }
}
