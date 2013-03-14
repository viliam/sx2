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
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.interfaces.SxParser;
import sk.wlio.sx2.parsers.Readers;

public class BracketExpression implements SxParser<IExpression> {

    public IExpression read(TextContext tC)  {
        Bracket z1 = Readers.bracket().read(tC);
        IExpression expression = Readers.expression().read(tC);
        Bracket z2 = Readers.bracket().read(tC);
        return new sk.wlio.sx2.beans.expression.BracketExpression(z1, expression, z2);
    }

}
