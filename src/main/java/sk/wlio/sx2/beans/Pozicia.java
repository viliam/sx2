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