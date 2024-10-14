package ma.projet.beans;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
public class Homme extends Personne {
    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

    // Constructeur par défaut
    public Homme() {
    }

    // Constructeur avec paramètres
    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }

    // Getters et Setters
    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}

