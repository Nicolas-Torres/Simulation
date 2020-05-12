package applicationV1.controleur;

import applicationV1.modele.Acteur;
import applicationV1.modele.Loup;
import applicationV1.modele.Mouton;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import applicationV1.modele.Environnement;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Environnement env;
    @FXML
    private RadioButton loup;
    @FXML
    private ToggleGroup Type;
    @FXML
    private RadioButton mouton;
    @FXML
    private TextField nbAnimal;
    @FXML
    private TextField nbrDeTour;
    @FXML
    private Pane ecran;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.env = new Environnement(500, 250);
        nbrDeTour.textProperty().bind(env.nbToursProperty().asString());
    }

    @FXML
    void ajouter(ActionEvent event) {

        if (loup.isSelected()) {
            if (Integer.parseInt(nbAnimal.getText()) > 0) {
                for (int i = 0; i < Integer.parseInt(nbAnimal.getText()); i++) {
                    Loup monLoup = new Loup(env);
                    env.ajouter(monLoup);
                    creerSprite(monLoup);
                }

            }
        } else if (mouton.isSelected()) {
            if (Integer.parseInt(nbAnimal.getText()) > 0) {
                for (int i = 0; i < Integer.parseInt(nbAnimal.getText()); i++) {
                    Mouton monMouton = new Mouton(env);
                    env.ajouter(monMouton);
                    creerSprite(monMouton);
                }
            }
        }
    }

    @FXML
    void faireDesTours(ActionEvent event) {
        System.out.println("faire des tours");
        int leNombreDeTour;
        leNombreDeTour = Integer.parseInt(nbrDeTour.getText());
        System.out.println(leNombreDeTour);

        for (int i = 0; i < leNombreDeTour; i++) {
            env.unTour();
        }
    }

    @FXML
    void unTour(ActionEvent event) {
        this.env.unTour();
    }

    void creerSprite(Acteur a) {
        Circle r = new Circle(3);
        if (a instanceof Loup)
            r.setFill(Color.RED);
        else
            r.setFill(Color.WHITE);
        r.translateXProperty().bind(a.getXProperty());
        r.translateYProperty().bind(a.getYProperty());
        r.setId(a.getId());

        ecran.getChildren().add(r);
        ecran.lookup("#" + a.getId());
    }

   /* void raffraichir() {
        for (Acteur a : this.env.getActeurs()) {
            Circle c = (Circle) this.ecran.lookup("#" + a.getId());
            // si c'est un nouveau né
            if (c == null) {
                creerSprite(a);
            } else {
                c.setTranslateX(a.getxProperty());
                c.setTranslateY(a.getyProperty());
            }
        }

        for (int i = ecran.getChildren().size() -1; i >= 0; i--) {
            if (env.getActeur(ecran.getChildren().get(i).getId()) == null) {
                ecran.getChildren().remove(i);
            }
        }
    } */
}