
public abstract class Cellule  {

    protected Character type;
    public World currentWorld;

    public Character getType() {
        return type;
    }


    public Cellule() {
    }

    @Override
    public String toString() {
        return "Cellule [type=" + this.type + "]";
    }

    public MoveBehavior tryMove(Direction dir, Cellule pusher) {
        if (currentWorld.RequestMovement(this, dir))
        {
            return MoveBehavior.Move;
        }
        return MoveBehavior.None;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null)
            throw new NullPointerException("object est null");

        Cellule c = (Cellule) o;
        if (this != c)
            return false;

        return true;

    }

}
