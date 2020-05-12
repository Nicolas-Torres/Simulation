package applicationV1.modele;

import javafx.beans.property.*;

import java.util.Random;


public abstract class Acteur {

    private IntegerProperty xProperty, yProperty;
    private int v; // vitesse de deplacement
    private int dx,dy ;// direction
    protected Environnement env;
    public static int compteur=0;
    private String id;
    private int pv;
    public Acteur(int xProperty, int yProperty, int v, Environnement env, int pv) {
        this.pv=pv;
        this.xProperty = new SimpleIntegerProperty();
        this.yProperty = new SimpleIntegerProperty();
        this.xProperty.setValue(xProperty);
        this.yProperty.setValue(yProperty);
        this.v = v;
        this.env=env;
        this.id="A"+compteur;
        compteur++;
        this.tirerDirection();
    }

    public Acteur( int v, Environnement env, int pv) {
        this.pv=pv;
        Random random=new Random();
        this.xProperty = new SimpleIntegerProperty();
        this.yProperty = new SimpleIntegerProperty();
        int x = random.nextInt(env.getWidth()-1);
        int y=random.nextInt(env.getHeight()-1);
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
        this.v = v;
        this.env=env;
        this.id="A"+compteur;
        compteur++;
        this.tirerDirection();
        //System.out.println("y" + y + "x" +x);
    }

    public int getX() {
        return xProperty.getValue();
    }

    public int getY() {
        return yProperty.getValue();
    }

    public IntegerProperty getXProperty () {
        return this.xProperty;
    }

    public IntegerProperty getYProperty () {
        return this.yProperty;
    }

    public  void setxProperty(int n){
        xProperty.setValue(n);
    }

    public void setyProperty(int n){
        yProperty.setValue(n);
    }


    public String getId() {
        return id;
    }

    public void decrementerPv(int n) {
        this.pv-=n;
    }

    public void incrementerPv(int n) {
        this.pv+=n;
    }

    public int getPv () {
        return this.pv;
    }


    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt(){
        this.pv=0;
    }


    private void tirerDirection(){
        Random random=new Random();
        int randomInt = random.nextInt(3);
        dx=randomInt-1;
        if(dx==0){
            randomInt=random.nextInt(2)-1;
            if(randomInt==0){
                dy=-1;
            }
            else{
                dy=1;
            }
        }
        else{
            dy=random.nextInt(3)-1;
        }
    }
    //permet de savoir si une action probabiliste se réalise
    public static boolean reussitProba(double pourcent){
        double x= Math.random();
        double pp=pourcent/100;
        return (x<=pp);
    }


    public void seDeplace(){
        // 20% de chance de changer de direction
        // if(Math.random()*100< pourentageRepro )
        if(reussitProba(20)) {
            tirerDirection();
        }
        int nposX=this.getX()+(this.v*dx);
        int nposY=this.getY()+(this.v*dy);
        while(!env.dansTerrain(nposX, nposY)){
            tirerDirection();
            nposX=this.getX()+(this.v*dx);
            nposY=this.getX()+(this.v*dy);
        }
        this.xProperty.setValue(nposX);
        this.yProperty.setValue(nposY);
    }

    public IntegerProperty xPropertyProperty() {
        return xProperty;
    }

    public IntegerProperty yPropertyProperty() {
        return yProperty;
    }

    public abstract void agit();

    @Override
    public String toString() {
        return "x=" + xProperty + ", y=" + yProperty + ", id=" + id ;
    }


}