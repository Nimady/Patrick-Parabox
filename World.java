import java.util.ArrayList;
import java.util.HashMap;


public class World extends Cellule {
    
   
    private int taille;
    private char name;
    public char type='W';
    private int nb_boxs;
    private int nb_cibles;
    private HashMap<Couple, Cellule> board = new HashMap<Couple, Cellule>();
    private ArrayList<World> subworlds;
    private int numbSub;
    private ArrayList<Cible> cibles;
    private ArrayList<Box> boxs;
    private ArrayList<char[]> lines;


    
    @Override
    public Character getType(){
        return type;
    } 
    public Character getName(){
        return name;
    }
    
    public ArrayList<char[]> getLines(){
        return lines;
    }
    public int getTaille() {
        return taille;
    }
    public int getNb_boxs(){
        return nb_boxs;
    }
    public int getNb_cibles(){
        return nb_cibles;
    }
    ArrayList<World> getSubWorlds(){
        return this.subworlds;
    } 
  
    public World(int taille,char name,int nb_boxs) {
        super();
        type = 'W';
        this.name=name;
        this.taille = taille;
        this.subworlds= null;
        this.boxs=null;
        this.cibles=null;
        this.lines=new ArrayList<char[]>();
        this.numbSub=0;
        this.nb_boxs=nb_boxs;
        this.nb_cibles=nb_boxs-1;

    }

    public World(int taille) {
        super();
        type = 'W';
        this.name= '@' ;
        this.taille = taille;
        this.subworlds= null;
        this.numbSub=0;
        this.nb_boxs=0;
        this.nb_cibles=1;

    }

    public World(int taille, char name,int nb_boxs,ArrayList<World> subworlds, ArrayList<Cible> cibles, ArrayList<Box> boxs, ArrayList<char[]> lines){
        super();
        type = 'W';
        this.name=name;
        this.taille = taille;
        this.nb_boxs=nb_boxs;
        this.nb_cibles=nb_boxs-1;
        this.subworlds= new ArrayList<World>();
        for(int i=0; i<subworlds.size(); i++){
            this.subworlds.add(subworlds.get(i));
        }
        this.numbSub=subworlds.size();
        this.boxs=new ArrayList<Box>();
        for(int j=0; j<boxs.size() ; j++){
            this.boxs.add(boxs.get(j));
        }
        this.cibles=new ArrayList<Cible>();
        for(int j=0; j<cibles.size() ; j++){
            this.cibles.add(cibles.get(j));
        }
        this.lines=new ArrayList<char[]>();
        for(int j=0;j<lines.size();j++){
            this.lines.add(lines.get(j));
        }
    }

    public int getNumbSub(){
        return numbSub;
    }

    public HashMap<Couple, Cellule> getBoard(){
        return board;
    }

    public Couple getCellPosition(Cellule cel)
    {
        return utility.getKeyByValue(board, cel);
    }
    
    public void removeFromBoard(Cellule cel) {
        Couple pos = getCellPosition(cel);
        if (pos != null) {
            board.remove(pos);
        }
    }

    public void AddToBoard(Cellule cel, Couple pos) {
        board.put(pos, cel);
        cel.currentWorld = this;
    }
    
    public boolean RequestMovement(Cellule cel, Direction dir) {
        Couple pos = getCellPosition(cel);
        return RequestMovement(cel, dir, pos);
    }

    public boolean RequestMovement(Cellule cel, Direction dir, Couple from) {

        Couple newPos;
        if (from == null) {
            switch (dir) {
                case Up:
                    newPos = new Couple(taille / 2, taille - 1);
                    break;
                case Down:
                    newPos = new Couple(taille / 2, 0);
                    break;
                case Left:
                    newPos = new Couple(taille - 1, taille / 2);
                    break;
                case Right:
                default:
                    newPos = new Couple(0, taille / 2);
                    break;
            }
        } else {
            newPos = new Couple(from, dir);
        }

        if (!(0 <= newPos.getX() && newPos.getX() < taille &&
                0 <= newPos.getY() && newPos.getY() < taille)) {
            if (currentWorld == null) {
                return false;
            }
            if (currentWorld.RequestMovement(cel, dir, currentWorld.getCellPosition(this)))
            {
                removeFromBoard(cel);
                return true;
            }
            return false;
        }

        MoveBehavior moveBehavior = MoveBehavior.Move;
        
        Cellule otherCel = board.get(newPos);
        if (otherCel != null) {
            moveBehavior = otherCel.tryMove(dir, cel);
            if (moveBehavior == MoveBehavior.None) {
                return false;
            }
        }
        removeFromBoard(cel);
        if (moveBehavior == MoveBehavior.Move)
            AddToBoard(cel, newPos);
        return true;
    }

    @Override
    public MoveBehavior tryMove(Direction dir, Cellule pusher) {
        MoveBehavior moveOut = super.tryMove(dir, pusher);
        if (moveOut != MoveBehavior.Move) {
            if (RequestMovement(pusher, dir)) {
                return MoveBehavior.Absorb;
            }
        }
        return moveOut;
    }

    public boolean isBox(){
        int i=0,j=0;
        boolean isBox ;
        boolean wall1;
        boolean wall2;
        boolean wall3;
        boolean wall4;


        i=1;
            for(j=0;j<this.getTaille();j++){
                if((this.getLines().get(i))[j]=='#'){
                    wall1=true;
                }
                else return false;
            }
        i=this.getTaille();
        for(j=0;j<this.getTaille();j++){
            if((this.getLines().get(i))[j]=='#'){
                wall2=true;
            }
            else return false;
        }
        j=0;
        for(i=1;i<this.getTaille()+1;i++){
            if((this.getLines().get(i))[j]=='#'){
                wall3=true;
            }
            else return false;
        }
        j=this.getTaille()-1;
        j=0;
        for(i=1;i<this.getTaille()+1;i++){
            if((this.getLines().get(i))[j]=='#'){
                wall4=true;
            }
            else return false;
        }
        return true;
    }
    

    public void printWorld() {
        for (int y = 0; y < taille; y++) {
            for (int x = 0; x < taille; x++) {
                {
                    
                    if (board.get(new Couple(x, y)) != null)
                        if(board.get(new Couple(x, y)).getType()=='W'){
                            System.out.print(board.get(new Couple(x, y)).getType());
                        }
                        else{
                            System.out.print(board.get(new Couple(x, y)).getType());
                        }


                    else
                        System.out.print(' ');
                }
            }
            System.out.print("\n");
        }
    }
    /*initializes a world
     * (creates the walls)
     
    public void initWorld(char charArray[], int taille){
        for (int y = 0; y < taille; y++) {
            for (int x = 0; x < taille; x++) {
                Cellule in = getBoard().getWorld().get(new Couple(x, y));
        
            }
        }
    }
*/

    
    /*Initializes a new subworld
     * (creates the walls and a random exit)
     */
    public void initSubWorlds(ArrayList<World> subWorlds){
        int b=0;
        int c=0;
        int w=0;


        for(int n=0;n<subWorlds.size();n++){
            for(int i=0;i<subWorlds.get(n).getTaille()+1;i++){
                for(int j=0;j<subWorlds.get(n).getLines().get(i).length;j++){
                    if((subWorlds.get(n).getLines().get(i))[j]=='B'){
                        subWorlds.get(n).AddToBoard(subWorlds.get(n).boxs.get(b),new Couple(i-1,j));  
                        b++;  
                    }
                    if((subWorlds.get(n).getLines().get(i))[j]=='@'){
                        subWorlds.get(n).AddToBoard(subWorlds.get(n).cibles.get(c),new Couple(i-1,j));
                        if(c<subWorlds.get(n).getNb_cibles())  
                        c++; 
                    }
                    if((subWorlds.get(n).getLines().get(i))[j]==subWorlds.get(w).getName()){
                        subWorlds.get(n).AddToBoard(subWorlds.get(w),new Couple(i-1,j));  
                        if(w<subWorlds.get(n).getNumbSub()-1)
                        w++; 
                    }
                    if((subWorlds.get(n).getLines().get(i))[j]=='#'){
                        subWorlds.get(n).AddToBoard(new Wall(), new Couple(i-1,j));
                    }
                }
            }
        }
    }


}
