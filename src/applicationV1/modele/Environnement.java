package applicationV1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Environnement {

    private int width,height;
    private ArrayList<Acteur> acteurs;
    private IntegerProperty nbToursProperty;

    public Environnement(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.nbToursProperty = new SimpleIntegerProperty();
        this.nbToursProperty.setValue(0);
        this.acteurs= new ArrayList<>();
    }

    public  int getNbTours(){
        return this.nbToursProperty.getValue();
    }

    public  void setNbTours(int n){
        this.nbToursProperty.setValue(n);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public Acteur getActeur(String id) {
        for(Acteur a:this.acteurs){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }


    public void ajouter(Acteur a){
        acteurs.add(a);
    }

    public boolean dansTerrain(double x, double y){
        return (0 <= x && x<this.width && 0<=y && y< this.height);
    }

    public void unTour(){
        System.out.println("tour " + this.nbToursProperty);
        for(int i=0;i<acteurs.size(); i++){
            Acteur a = acteurs.get(i);
            a.agit();
        }
        for(int i=acteurs.size()-1; i>=0;i--){
            Acteur a = acteurs.get(i);
            if(!a.estVivant()){
                System.out.println("mort de : " + a);
                acteurs.remove(i);
            }
        }
        this.nbToursProperty.setValue(this.getNbTours()+1);
    }


    public void init(){
        for(int i=0; i<50;i++){
            this.ajouter(new Loup(this));
        }
        for(int i=0; i<100;i++){
            this.ajouter(new Mouton(this));
        }
    }

    public IntegerProperty nbToursProperty() {
        return nbToursProperty;
    }
}