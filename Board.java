import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.util.Timer;
import java.util.TimerTask;
import java.util.spi.CurrencyNameProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;



public class Board extends JPanel{

    private World WorldDisplayed;
    private ArrayList<World> subWorlds;
    private int nb_subWorlds;
    private Player player;
    
    final int originalTilesize = 10;
    final int scale = 5;
    final int tileSize = originalTilesize * scale;
    final int maxScreenRow = 11;
    final int maxScreenCol = 11;
    final int screenWidth = (tileSize * maxScreenCol)+20;
    final int screenHeight = (tileSize * maxScreenRow)+20;
    static int numb;
    static int playerX;
    static int playerY;
    static int cloneX;
    static int cloneY;
    static World CloneWorld;
    static int CloneRemaining=1;

    Color DarkGreen= new Color(50,205,50);
    Color DarkerDarkGreen= new Color(50,180,50);
    Color DarkRed= new Color(139,0,0);
    Color DeepPink= new Color(255,20,147);
    Color DarkYellow= new Color(246,190,0);
    Color DeepSkyBlue= new Color(0,191,255);
    Color SkyBlue= new Color(135,206,235);
    Color CornFlowerBlue= new Color(100,149,237);
    Color DodgerBlue= new Color(30,144,255);
    Color AliceBlue= new Color(240,248,255);
    Color Spindle= new Color(165,211,230);
    
    /*Board constructor */
    public Board(World WorldDisplayed,Player p, ArrayList<World> subworlds,int nb_subWorlds) {
        
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setFocusable(true);
        this.WorldDisplayed = WorldDisplayed;
        this.player=p;
        this.nb_subWorlds=nb_subWorlds;
        this.subWorlds=new ArrayList<World>();
        
        for(int i=0;i<nb_subWorlds;i++){
            this.subWorlds.add(subworlds.get(i));
        }
        
    }

    public Board(World WorldDisplayed, Player p){
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setFocusable(true);
        this.WorldDisplayed = WorldDisplayed;
        this.player=p;
        
    }

    

    /*Paints the different worlds by painting each individual cell depending on their type
     * BLUE == Wall
     * RED == Player
     * PINK == World 
     * YELLOW == Box
     * GREEN == Everything else
     */
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        
        for (int y = 0; y < getWorld().getTaille(); y++) {
            for (int x = 0; x < getWorld().getTaille(); x++) {
                Cellule in = getWorld().getBoard().get(new Couple(x, y));
                

                if (in!=null){     
                    if(in.getType()=='#'){           
                        g.setColor(DodgerBlue);
                        g.fillRect(x*50+10, y*50+10, tileSize, tileSize);
                        g.setColor(DeepSkyBlue);
                        g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8); 
                        g.setColor(SkyBlue);
                        g.fillRect(x*50+20, y*50+20, tileSize-20, tileSize-20);          
                        g.setColor(Spindle);
                        g.fillRect(x*50+30, y*50+30, tileSize-35, tileSize-35);
                    }
                    if(in.getType()=='B'){
                        g.setColor(Color.BLACK);
                        g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                        g.setColor(Color.YELLOW);
                        g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);           
                    }
                    if(in.getType()=='A'){
                        if(numb==6){
                            g.setColor(DarkRed);
                            g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                            g.setColor(DeepPink);
                            g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                            g.setColor(DarkRed);
                            g.fillRect(x*50+20,y*50+25, tileSize/5, tileSize/15);
                            g.fillRect(x*50+40,y*50+25, tileSize/5, tileSize/15);

                        }
                        else{
                            g.setColor(DarkRed);
                            g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                            g.setColor(DeepPink);
                            g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                            g.setColor(DarkRed);
                            g.fillRect(x*50+20,y*50+20, tileSize/5, tileSize/5);
                            g.fillRect(x*50+40,y*50+20, tileSize/5, tileSize/5);
                        }
                        
                        
                    }
                    if(in.getType()=='@'){
                        g.setColor(Color.ORANGE);
                        g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                        g.setColor(DarkGreen);
                        g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                        g.setColor(Color.GREEN);
                        g.fillRect(x*50+20,y*50+20, tileSize-35, tileSize-35);
                        g.fillRect(x*50+37,y*50+40, tileSize-40, tileSize-40);
                    }
                    if(in.getType()=='b'){
                        g.setColor(Color.ORANGE);
                        g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                        g.setColor(Color.YELLOW);
                        g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);

                    }
                    if(in.getType()=='a'){
                        g.setColor(Color.ORANGE);
                        g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                        g.setColor(DeepPink);
                        g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                        g.setColor(DarkRed);
                        g.fillRect(x*50+20,y*50+20, tileSize/5, tileSize/5);
                        g.fillRect(x*50+40,y*50+20, tileSize/5, tileSize/5);
                    }
                    if(in.getType()=='w'){
                        
                        for(int u=0;u<getNbSub();u++){
                            for (int h = 0; h < getSubWorld(u).getTaille(); h++) {
                                for (int k = 0; k < getSubWorld(u).getTaille(); k++) {
                                    Cellule insub = getSubWorld(u).getBoard().get(new Couple(h, k));
                                    if(getSubWorld(u).getTaille()==7){
                                    if (insub!=null){     
                                        if(insub.getType()=='#'){           
                                            g.setColor(DodgerBlue);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            g.setColor(SkyBlue);
                                            g.fillRect(h*7+12+x*50,k*7+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                        }
                                        if(insub.getType()=='B'){
                                            g.setColor(Color.BLACK);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            g.setColor(Color.YELLOW);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                        }
                                        if(insub.getType()=='A'){
                                            g.setColor(DeepPink);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                        }
                                    }
                                    else{
                                        g.setColor(DarkerDarkGreen);
                                        g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                    }
                                    }
                                    if(getSubWorld(u).getTaille()==5){
                                        if (insub!=null){     
                                            if(insub.getType()=='#'){           
                                                g.setColor(DodgerBlue);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                g.setColor(SkyBlue);
                                                g.fillRect(h*10+12+x*50,k*10+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                            }
                                            if(insub.getType()=='B'){
                                                g.setColor(Color.BLACK);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                g.setColor(Color.YELLOW);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                            }
                                            if(insub.getType()=='A'){
                                                g.setColor(DeepPink);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            }
                                        }
                                        else{
                                            g.setColor(DarkerDarkGreen);
                                            g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                        }
                                        }
                                        if(getSubWorld(u).getTaille()==3){
                                            if (insub!=null){     
                                                if(insub.getType()=='#'){           
                                                    g.setColor(DodgerBlue);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                    g.setColor(SkyBlue);
                                                    g.fillRect(h*17+12+x*50,k*17+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                                }
                                                if(insub.getType()=='B'){
                                                    g.setColor(Color.BLACK);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                    g.setColor(Color.YELLOW);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                                }
                                                if(insub.getType()=='A'){
                                                    g.setColor(DeepPink);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                }
                                            }
                                            else{
                                                g.setColor(DarkerDarkGreen);
                                                g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            }
                                            }
                                }
                            }
                        }
                        g.setColor(Color.ORANGE);
                        g.drawRect(x*50+11,y*50+10, tileSize-2, tileSize-2);
                        g.drawRect(x*50+11,y*50+10, tileSize-2, tileSize-2);
                        g.drawRect(x*50+12,y*50+11, tileSize-3, tileSize-3);
                        g.drawRect(x*50+13,y*50+12, tileSize-4, tileSize-4);
                        g.drawRect(x*50+13,y*50+12, tileSize-5, tileSize-5);
                        g.drawRect(x*50+13,y*50+12, tileSize-6, tileSize-6);
                        
                    }
                    if(in.getType()=='W'){
                        for(int u=0;u<getNbSub();u++){
                            for (int h = 0; h < getSubWorld(u).getTaille(); h++) {
                                for (int k = 0; k < getSubWorld(u).getTaille(); k++) {
                                    Cellule insub = getSubWorld(u).getBoard().get(new Couple(h, k));
                                    if(getSubWorld(u).getTaille()==7){
                                    if (insub!=null){     
                                        if(insub.getType()=='#'){           
                                            g.setColor(DodgerBlue);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            g.setColor(SkyBlue);
                                            g.fillRect(h*7+12+x*50,k*7+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                        }
                                        if(insub.getType()=='B'){
                                            g.setColor(Color.BLACK);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            g.setColor(Color.YELLOW);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                        }
                                        if(insub.getType()=='A'){
                                            g.setColor(DeepPink);
                                            g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                        }
                                    }
                                    else{
                                        g.setColor(DarkerDarkGreen);
                                        g.fillRect(h*7+10+x*50,k*7+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                    }
                                    }
                                    if(getSubWorld(u).getTaille()==5){
                                        if (insub!=null){     
                                            if(insub.getType()=='#'){           
                                                g.setColor(DodgerBlue);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                g.setColor(SkyBlue);
                                                g.fillRect(h*10+12+x*50,k*10+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                            }
                                            if(insub.getType()=='B'){
                                                g.setColor(Color.BLACK);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                g.setColor(Color.YELLOW);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                            }
                                            if(insub.getType()=='A'){
                                                g.setColor(DeepPink);
                                                g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            }
                                        }
                                        else{
                                            g.setColor(DarkerDarkGreen);
                                            g.fillRect(h*10+10+x*50,k*10+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                        }
                                        }
                                        if(getSubWorld(u).getTaille()==3){
                                            if (insub!=null){     
                                                if(insub.getType()=='#'){           
                                                    g.setColor(DodgerBlue);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                    g.setColor(SkyBlue);
                                                    g.fillRect(h*17+12+x*50,k*17+12+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);
                                                }
                                                if(insub.getType()=='B'){
                                                    g.setColor(Color.BLACK);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                    g.setColor(Color.YELLOW);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille() -4, tileSize/getSubWorld(u).getTaille() -4);         
                                                }
                                                if(insub.getType()=='A'){
                                                    g.setColor(DeepPink);
                                                    g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                                }
                                            }
                                            else{
                                                g.setColor(DarkerDarkGreen);
                                                g.fillRect(h*17+10+x*50,k*17+10+y*50, tileSize/getSubWorld(u).getTaille(), tileSize/getSubWorld(u).getTaille());
                                            }
                                            }
                                }
                            }
                        }
                        
                    }
                    }
                    else{
                        g.setColor(DarkGreen);
                        g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                        g.setColor(Color.GREEN);
                        g.fillRect(x*50+20,y*50+20, tileSize-35, tileSize-35);
                        g.fillRect(x*50+37,y*50+40, tileSize-40, tileSize-40);
                        if(x==cloneX && y==cloneY){
                            if(numb==6){
                                g.setColor(CornFlowerBlue);
                                g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                                g.setColor(Spindle);
                                g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                                g.setColor(CornFlowerBlue);
                                g.fillRect(x*50+20,y*50+25, tileSize/5, tileSize/15);
                                g.fillRect(x*50+40,y*50+25, tileSize/5, tileSize/15);
    
                            }
                            else{
                                g.setColor(CornFlowerBlue);
                                g.fillRect(x*50+10,y*50+10, tileSize, tileSize);
                                g.setColor(Spindle);
                                g.fillRect(x*50+14,y*50+14, tileSize-8, tileSize-8);
                                g.setColor(CornFlowerBlue);
                                g.fillRect(x*50+20,y*50+20, tileSize/5, tileSize/5);
                                g.fillRect(x*50+40,y*50+20, tileSize/5, tileSize/5);
                            }
                        }
                    }
                }
            }
       
        }

    /*Returns the Board's player */
    public Player getPlayer(){
        return this.player;
    }

    public int getNbSub(){
        return nb_subWorlds;
    }

    /*Returns the Board's world */
    public World getWorld(){
        return this.WorldDisplayed;
    }

    /*Returns the Board's subworld */
    public World getSubWorld(int i){
        return this.subWorlds.get(i);
    }

    /*Main function */
    public static void main (String[] args) throws IOException {

        /* lecture du fichiers cage.txt */
         

    BufferedReader reader = new BufferedReader(new FileReader(new File("cage.txt")));
    /*on stock les caractére*/
    ArrayList <char[]> lines = new ArrayList<>();
    /*pour trouver le nembre de ligne dans le fichier */
    Scanner file  = new Scanner(new File("cage.txt"));
    int nbLines = 0;   
        while(file.hasNextLine()) {
            file.nextLine();
            nbLines++;
        }
        
        String line2;
        int i=0;
        int n=0;
        /* lire les lignes caractere par caractere*/
        while(n<nbLines){
            line2 = reader.readLine();         
            char[] line1 = new char[line2.length()];
            line1 = line2.toCharArray();
            lines.add(line1);
            n++;
        }

    ArrayList<World> subWorlds  =new ArrayList<World>();
    ArrayList<Box> boxs=new ArrayList<Box>();
    i=0;
    int nb_world=1;
    int nb_boxs=0;
    /*On récupere le name du monde principale*/
    char name=(lines.get(0))[0];
    /* On récupere la taille du monde principale*/
    int taille=(lines.get(0))[2]-'0';
    while(i<nbLines){
        i=i+taille+2;

        if(i<nbLines){
            /* On récupere la taille du monde*/
            taille=(lines.get(i))[2]-'0';
            /*On récupere le name du monde*/
            name=(lines.get(i))[0];
            /* On initialise le monde et on le rajoute dans un ArrayList*/
            World subw = new World(taille,name,0);
            Box box = new Box(name);
            for(int f=i;f<i+taille+1;f++){
                subw.getLines().add(lines.get(f));
            }
            if(subw.isBox()==false){
                subWorlds.add(subw);
                nb_world++;
            }
            /*Si c'est un box on le mis dans un ArrayList des boxes*/
            else{
                boxs.add(box);
                nb_boxs++;
            }
        }
        
    }
 

    taille=(lines.get(0))[2]-'0';
    name=(lines.get(0))[0];
    

    int nb_cibles=0;
    /*On récupere les cible dans un ArrayList*/
    ArrayList<Cible> cibles=new ArrayList<Cible>();
    for(i=1;i<lines.size();i++){
        for(int j=0;j<(lines.get(i)).length;j++){
                if((lines.get(i))[j]=='@'){
                    cibles.add(new Cible());
                    nb_cibles++;
                }
        }
    }




    /*Solves the path*/
    LinkedList<Couple> solution=new LinkedList<Couple>();
    Couple depart=new Couple(2, 1);
    Couple distination=new Couple(4,4);
    
        /* c */
        LinkedList<Couple> c=new LinkedList<Couple>();
        /*F*/
        ArrayList<LinkedList> tabChemin=new ArrayList<LinkedList>();
        /*V*/
        ArrayList<Couple> cordonnesVisites = new ArrayList<Couple>();
        
        /*Fair F ={[(x1,y1)]} et V=vide*/
        LinkedList <Couple> cdepart = new LinkedList<Couple>();
        cdepart.push(depart);
        tabChemin.add(cdepart) ;
        while(!(tabChemin.isEmpty())){
            /*1*/
            c = tabChemin.remove(0);
     
            
            if(c.getFirst().equals(distination)){
    
                solution=c;
                break;
            }
            /*4*/
            if((c.getFirst().getX()>1) || (c.getFirst().getY()>0) || (c.getFirst().getX()<taille)|| (c.getFirst().getY()<taille)){
                /*5 */

                if( ((lines.get(c.getFirst().getX()) )[c.getFirst().getY()]==' ') ){
                    /*6*/
                    if(!cordonnesVisites.contains(c.getFirst())){
                        /*7*/
                        cordonnesVisites.add(c.getFirst());
                        /*8  fair quatre copie de c*/
                        LinkedList<Couple> c1=new LinkedList<Couple>();
                        LinkedList<Couple> c2=new LinkedList<Couple>();
                        LinkedList<Couple> c3=new LinkedList<Couple>();
                        LinkedList<Couple> c4=new LinkedList<Couple>();
                        
                        c1 = (LinkedList) c.clone();
                        c2 = (LinkedList) c.clone();
                        c3 = (LinkedList) c.clone();
                        c4 = (LinkedList) c.clone();
                        /*9*/
                        c1.push(new Couple(c.getFirst().getX()+1,c.getFirst().getY()));
                        c2.push(new Couple(c.getFirst().getX()-1,c.getFirst().getY()));
                        c3.push(new Couple(c.getFirst().getX(),c.getFirst().getY()+1));
                        c4.push(new Couple(c.getFirst().getX(),c.getFirst().getY()-1));
                        /*10*/
                        //System.out.println(1);
                        tabChemin.add(c1);
                        //System.out.println(1);
                        tabChemin.add(c2);
                        //System.out.println(1);
                        tabChemin.add(c3);
                        //System.out.println(1);
                        tabChemin.add(c4);
                        c.clear();
                   }
                }
            }
        
                   
                        
                    
                
            
            
            
        }   
        /*Initializes the game */
        for(int f=0;f<nb_world-1;f++)
                boxs.get(f).type = 'B';
        Player p = new Player();
        World w1=new World(taille,name,nb_boxs,subWorlds,cibles,boxs,lines);
        Board Bord1= new Board(w1,p,subWorlds,nb_world-1);
        int b=0;
        int nc=0;
        int nw=0;
        w1.initSubWorlds(subWorlds);
        //w1.AddToBoard(p,new Couple(2,2));
        for(i=0;i<taille+1;i++){
            for(int j=0;j<lines.get(i).length;j++){
                if(b<nb_boxs){
                    if((lines.get(i))[j]==boxs.get(b).getName()){
                        w1.AddToBoard(boxs.get(b),new Couple(i-1,j));  
                        b++;  
                    }
                }
                if(nc<cibles.size()){
                if((lines.get(i))[j]=='@'){
                    
                    w1.AddToBoard(cibles.get(nc),new Couple(i-1,j));
                    
                        nc++;
                    }
                }  
                if(nw<subWorlds.size()){
                if((lines.get(i))[j]==subWorlds.get(nw).getName()){
                    
                    System.out.println(subWorlds.get(nw));
                    w1.AddToBoard(subWorlds.get(nw),new Couple(i-1,j));
                    
                        nw++;
                    }
                } 
                
                if((lines.get(i))[j]== 'A'){

                    w1.AddToBoard( p, new Couple(i-1,j));
                    playerX=i-1;
                    playerY=j; 

                }

                if((lines.get(i))[j]=='#'){
                    w1.AddToBoard(new Wall(), new Couple(i-1,j));
                }
                if((lines.get(i))[j]==name){
                    w1.AddToBoard(w1, new Couple(i-1,j));
                }
            }
        }

        
        World w3=p.currentWorld;
        
        JFrame frame = new JFrame();

        frame.add(Bord1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        /*Timer */
        int minmin=0;
        int maxmax=10;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                numb=(int)Math.floor(Math.random() *(maxmax - minmin + 1) + minmin);   
                frame.repaint();
            }
        }, 0, 500);
      
        
        KeyListener listener = new KeyListener() {


            /*Movement : 
             * Press w to go up
             * Press s to go down
             * Press a to go left
             * Press d to go right
            */
            @Override
            public void keyPressed(KeyEvent event) {
                switch(event.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        if((p.tryMove(Direction.Up, null))==MoveBehavior.Move)
                            playerY--;
                        w1.printWorld();
                        System.out.print("\n");
                        System.out.print("\n");
                        subWorlds.get(0).printWorld();
                        frame.repaint();
                        //Go Up
                        break;
                    case KeyEvent.VK_DOWN:
                        if((p.tryMove(Direction.Down, null))==MoveBehavior.Move)
                            playerY++;
                        w1.printWorld();
                        System.out.print("\n");
                        System.out.print("\n");
                        subWorlds.get(0).printWorld();
                        frame.repaint();
                        //Go Down
                        break;
                    case KeyEvent.VK_LEFT:
                        if((p.tryMove(Direction.Left, null))==MoveBehavior.Move)
                            playerX--;
                        w1.printWorld();
                        System.out.print("\n");
                        System.out.print("\n");
                        subWorlds.get(0).printWorld();
                        frame.repaint();
                        //Go Left
                        break;
                    case KeyEvent.VK_RIGHT:
                        if((p.tryMove(Direction.Right, null))==MoveBehavior.Move)
                            playerX++;
                        w1.printWorld();
                        System.out.print("\n");
                        System.out.print("\n");
                        subWorlds.get(0).printWorld();
                        frame.repaint();
                        //Go Right
                        break;
                    default:
                    
                        switch(event.getKeyChar()){
                            case 'c':
                                if(CloneRemaining==1){
                                    if(p.currentWorld!=w1){
                                        System.out.println("Player can only be cloned in the main world !\n");
                                        break;
                                    }
                                    else{
                                        cloneX=playerX;
                                        cloneY=playerY;
                                        System.out.println(playerX+playerY+cloneX+cloneY);
                                        CloneWorld=p.currentWorld;
                                        CloneRemaining--;
                                        System.out.println("Cloning successful !\n");
                                        break;
                                    }
                                }
                                if(CloneRemaining==0){
                                    System.out.println("No clones remaining !\n");
                                    break;
                                }
                            case 'v':
                                if(CloneRemaining==1){
                                    System.out.println("No clones existing, cannot teleport !\n");
                                    break;
                                }
                                else{
                                    Cellule temp = CloneWorld.getBoard().get(new Couple(cloneX, cloneY));
                                    if(temp!=null){
                                        System.out.println("There is an object where the clone is, cannot teleport !\n");
                                        break;
                                    }
                                    else{
                                        playerX=cloneX;
                                        playerY=cloneY;
                                        p.currentWorld.removeFromBoard(p);
                                        CloneWorld.AddToBoard(p, new Couple(playerX,playerY));
                                        frame.repaint();
                                        break;
                                    }
                                }

                                

                            default:
                            //Do Nothing
                    
                        }
                    }
                

            }
            @Override
            public void keyReleased(KeyEvent event) {
                
                                
            }
            @Override
            public void keyTyped(KeyEvent event) {
            
            }
        };

        /*KeyListeners */

        Bord1.addKeyListener(listener);
        Bord1.requestFocus();
        frame.setVisible(true); 
        w1.printWorld();
        System.out.print("\n"); 
        System.out.print("\n");
        for(i=0;i<nb_world-1;i++){
            subWorlds.get(i).printWorld();
        }
        
        
                   
            /*Switches panel depending on which world p is currently in */

            
         
            while(true){ 
                /*Shows subworld*/
               for(i=0;i<nb_world-1;i++){
                if(p.currentWorld==subWorlds.get(i) && w3==w1){
                    frame.remove(Bord1);
                    Bord1 = new Board(subWorlds.get(i),p);
                    frame.add(Bord1);
                    Bord1.addKeyListener(listener);
                    Bord1.requestFocus();
                    frame.setVisible(true);
                    w3=subWorlds.get(i);
                }
                if(p.currentWorld==w1 && w3 == subWorlds.get(i)){
                    frame.remove(Bord1);
                    Bord1= new Board(w1,p,subWorlds,nb_world-1);
                    frame.add(Bord1);
                    Bord1.addKeyListener(listener);
                    Bord1.requestFocus();
                    frame.setVisible(true);
                    w3=w1;
                }
               }
                
                
                /*Shows main world*/
                
                    
                frame.repaint();
                /*Success */
               /* Si toutes les cibles sont remplit par des boite le joueur gange */
                int g=0;
                for(i=0;i<nb_cibles;i++){
                    if(cibles.get(i).isSuccess()){
                        g++;
                    }
                }
                if(g==nb_cibles){
                    System.out.println("CONGRATULATIONS");
                    System.exit(0);
                }
               
                
                
                
            }
    
        
        
    }
}



    
