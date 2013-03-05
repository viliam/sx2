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

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.interfaces.IExpression;
import sk.wlio.sx2.visitors.IVisitor;

public class TestVyraz implements IExpression {

    Enums.ExpType expType = Enums.ExpType.INT;

    public TestVyraz() {}
    public TestVyraz(Enums.ExpType expType) {
        this.expType = expType;
    }

    public Enums.ExpType getExpType() {
        return expType;
    }

    public Position getPosition() {
        return null;
    }

    public void visit(IVisitor iVisitor) {
     //   iVisitor.visit(this);
    }
}
