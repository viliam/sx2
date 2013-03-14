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

package sk.wlio.sx2.parsers.expression;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.expression.Expression;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.parsers.Readers;
import sk.wlio.sx2.interfaces.SxParser;

public class ExprReader implements SxParser<IExpression> {

    public IExpression read(TextContext tC)  {
        IExpression expr;
        if ( tC.isPrefixBracketOpen() )  {
            expr = Readers.exprBracket().read(tC);
        } else   {
            expr = Readers.exprSimple().read(tC);
        }

        if (tC.isEndOfFile())  return expr;

        if (tC.isPrefixOperatorExp() ) {
            SxParser<Operator> opReader = Readers.opExpr();
            expr = new Expression(expr, opReader.read(tC), Readers.expression().read(tC));
        }

        if (!tC.isEndOfFile() )
            checkEndOfExpression(tC);

        return expr;
    }

    private void checkEndOfExpression(TextContext tC) {
        if    ( !tC.isPrefixOperator()
             && !tC.isPrefixComma()
             && !tC.isPrefixBracketClosed() )
            throw SxException.create(SxExTyp.EXPECTED_OPERATOR, tC);
    }

}
