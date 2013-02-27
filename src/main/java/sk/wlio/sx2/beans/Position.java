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

public class Position {

    private final int x, y;

    public Position(int x, int y) {
        this.x =x;
        this.y= y;
    }

    public Position(Position b) {
        this( b.x, b.y);
    }

    public Position add(Position position) {
        return new Position( this.x + position.x, this.y + position.y);
    }
    public Position addX( int x) {
        return new Position(this.x + x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x && y == position.y;

    }

    public int getX() {
        return x;
    }

    public Position setX(int x) {
        return new Position( x, this.y);
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