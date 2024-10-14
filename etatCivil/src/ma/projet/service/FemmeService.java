package ma.projet.service;

import java.util.Date;
import ma.projet.dao.IDao;
import ma.projet.beans.Femme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public Femme getById(int id) {
        Session session = null;
         Femme e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Femme) session.get(Femme.class, id);
            session.getTransaction().commit();
            return e;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return e;
    }

    @Override
    public List<Femme> getAll() {
       Session session = null;
        List<Femme>  pr = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            pr = session.createQuery("from Femme").list();
            session.getTransaction().commit();
            return pr;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return pr;
    }
   public int  countEnfantsBetweenDates(Femme femme, Date dateDebut, Date dateFin) {
    Session session = null;
     int nombreEnfants = 0;

    try {
        // Ouvrir la session
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Requête HQL pour récupérer le nombre d'enfants d'une femme entre deux dates
        nombreEnfants =  (int) session.createQuery( "Mariage.countEnfantsBetweenDates")
            .setParameter("femme", femme)
            .setParameter("dateDebut", dateDebut)
            .setParameter("dateFin", dateFin)
            .uniqueResult();

        // Affichage du nombre d'enfants
        System.out.println("Nombre d'enfants pour la femme " + femme.getNom() + " entre " + dateDebut + " et " + dateFin + ": " + nombreEnfants);

        // Commit des transactions
        session.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();  // Afficher les erreurs si elles surviennent
    } finally {
        if (session != null) {
            session.close();  // Fermer la session dans le bloc finally
        }
 

    return nombreEnfants;  // Retourner le nombre d'enfants
 }
     }
    public List<Femme>  findFemmesWithMultipleMariages() {
    Session session = null;
    List<Femme> femmes = null;

    try {
        // Ouvrir la session
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Requête HQL pour récupérer les femmes mariées deux fois ou plus
        femmes = session.createQuery(
            "SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2")
            .list();

        // Affichage des femmes mariées plusieurs fois
        System.out.println("Femmes mariées deux fois ou plus :");
        for (Femme femme : femmes) {
            System.out.printf("Nom: %s, Date de Naissance: %s%n", femme.getNom(), femme.getDateNaissance());
        }

        // Commit des transactions
        session.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();  // Afficher les erreurs si elles surviennent
    } finally {
        if (session != null) {
            session.close();  // Fermer la session dans le bloc finally
        }
    }

    return femmes;  // Retourner la liste des femmes mariées plusieurs fois
}

}
