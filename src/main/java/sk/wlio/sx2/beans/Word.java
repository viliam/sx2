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

public class Word extends WordAbstract {

    private String obsah;

    public Word(Position position, String obsah) {
        super(position);
        this.obsah = obsah;
    }

    public Word(Position position) {
        super(position);
    }

    public Word(Word word) {
        this(word.getPosition(), word.getObsah());
    }

    @Override
    public String toString() { return obsah;}

    public String getObsah() {
        return obsah;
    }
}


