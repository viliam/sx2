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

package sk.wlio.sx2.beans;

public class Slovo extends SlovoAbstract {

    private String obsah;

    public Slovo(Pozicia pozicia, String obsah) {
        super(pozicia);
        this.obsah = obsah;
    }

    public Slovo(Pozicia pozicia) {
        super(pozicia);
    }

    public Slovo(Slovo slovo) {
        this(slovo.getPozicia(),slovo.getObsah());
    }

    @Override
    public String toString() { return obsah;}

    public String getObsah() {
        return obsah;
    }
}


