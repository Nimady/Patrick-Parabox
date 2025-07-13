public class Cible extends World {
    
    public Cible() {
        super(1);
        type = '@';
    }
    
     @Override
    public Character getType() {
        if (getBoard().isEmpty())
            return type;
        Cellule inside = getBoard().values().iterator().next();
        return Character.toLowerCase(inside.getType());
    }

    public Boolean isSuccess() {
        if(!getBoard().isEmpty()){
            return true;
        } 
        return false;
    }

    @Override
    public MoveBehavior tryMove(Direction dir, Cellule pusher) {
        MoveBehavior moveOut = MoveBehavior.Absorb;
         if (RequestMovement(pusher, dir)) {
             return MoveBehavior.Absorb;
         }
        return moveOut;
    }

}
