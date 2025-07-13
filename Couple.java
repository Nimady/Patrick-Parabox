
public class Couple {

    private int x;
    private int y;

    public Couple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Couple(Couple c, Direction dir) {
        if (c == null)
            throw new NullPointerException("object est null");

        this.x = c.x;
        this.y = c.y;
        switch (dir) {
            case Up:
                this.y--;
                break;
            case Down:
                this.y++;
                break;
            case Left:
                this.x--;
                break;
            case Right:
                this.x++;
                break;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        String tmp = Integer.toString(x) + Integer.toString(y);
        return tmp.hashCode();
        // final int prime = 31;
        // int result = 1;
        // result = prime * result + x;
        // result = prime * result + y;
        // return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            throw new NullPointerException("type est null");
        if (!(obj instanceof Couple))
            return false;
        Couple co = (Couple) obj;

        if (this.x != co.x)
            return false;

        if (this.y != co.y)
            return false;

        return true;

    }

}
