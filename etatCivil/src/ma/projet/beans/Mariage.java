/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author a
 */

@Entity
@Table(name = "mariage")
public class Mariage {

    @EmbeddedId
    private MariageId pk;

    @ManyToOne
    @JoinColumn(name = "homme", insertable = false, updatable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme", insertable = false, updatable = false)
    private Femme femme;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "nbr_enfants")
    private int nbrEnfants;

    // Constructeur par défaut
    public Mariage() {
    }

    // Constructeur avec paramètres
    public Mariage(Homme homme, Femme femme, Date dateDebut, Date dateFin, int nbrEnfants) {
        this.pk = new MariageId(homme, femme);  // Utilisation de MariageId comme clé composite
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
    }

    // Getters et Setters
    public MariageId getPk() {
        return pk;
    }

    public void setPk(MariageId pk) {
        this.pk = pk;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrEnfants() {
        return nbrEnfants;
    }

    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }
}
