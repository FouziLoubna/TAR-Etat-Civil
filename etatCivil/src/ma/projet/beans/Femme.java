package ma.projet.beans;

import java.util.Date;
import javax.persistence.*;
import java.util.List;
@Entity
@NamedQuery(
    name = "Femme.findMariageMultiple",
    query = "SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2")
public class Femme extends Personne {
    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

    // Constructeur par défaut
    public Femme() {
    }

    // Constructeur avec paramètres
    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
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

