public class Wall extends Cellule {
    
    public Wall()
    {
        super();
        type = '#';
    }

    @Override
    public MoveBehavior tryMove(Direction dir, Cellule pusher) {
        return MoveBehavior.None;
    }
}
