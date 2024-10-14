package ma.projet.beans;

import java.io.Serializable;
import javax.persistence.Embeddable;

// Classe clé primaire composite
@Embeddable
public class MariageId implements Serializable {
    private int homme;
    private int femme;

    public MariageId() {
    }

    public MariageId(int homme, int femme) {
        this.homme = homme;
        this.femme = femme;
    }

    MariageId(Homme homme, Femme femme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters et Setters
    public int getHomme() {
        return homme;
    }

    public void setHomme(int homme) {
        this.homme = homme;
    }

    public int getFemme() {
        return femme;
    }

    public void setFemme(int femme) {
        this.femme = femme;
    }
 }
    