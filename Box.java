public class Box extends Cellule {
    Character name;

    public Box(Character name)
    {
        super();
        type = 'B';
        this.name=name;
    }

    public Character getName(){
        return name;
    }
}
