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

public class Pozicia {

    private final int x, y;

    public Pozicia(int x, int y) {
        this.x =x;
        this.y= y;
    }

    public Pozicia(Pozicia b) {
        this( b.x, b.y);
    }

    public Pozicia add(Pozicia pozicia) {
        return new Pozicia( this.x + pozicia.x, this.y + pozicia.y);
    }
    public Pozicia addX( int x) {
        return new Pozicia(this.x + x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pozicia pozicia = (Pozicia) o;

        return x == pozicia.x && y == pozicia.y;

    }

    public int getX() {
        return x;
    }

    public Pozicia setX(int x) {
        return new Pozicia( x, this.y);
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Bod{x=" + x + ", y=" + y + '}';
    }
}