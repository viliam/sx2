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

package sk.wlio.sx2.beans.vyraz;

import sk.wlio.sx2.beans.Position;
import sk.wlio.sx2.beans.WordAbstract;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.rozhrania.IVyraz;

public abstract class VyrazAbstract extends WordAbstract implements IVyraz {

    protected IVyraz v1;
    protected Operator op;
    protected IVyraz v2;

    protected VyrazAbstract(Position position, IVyraz v1, Operator op, IVyraz v2) {
        super(position);
        this.v1 = v1;
        this.op = op;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return v1.toString() + op.toString() + v2.toString();
    }

    public IVyraz getV1() {
        return v1;
    }

    public Operator getOp() {
        return op;
    }

    public IVyraz getV2() {
        return v2;
    }
}
