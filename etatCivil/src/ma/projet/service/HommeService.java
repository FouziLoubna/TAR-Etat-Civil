package ma.projet.service;

import java.util.Date;
import ma.projet.dao.IDao;
import ma.projet.beans.Homme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public Homme getById(int id) {
        Session session = null;
         Homme e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Homme) session.get(Homme.class, id);
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
    public List<Homme> getAll() {
       Session session = null;
        List<Homme>  pr = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            pr = session.createQuery("from Homme").list();
            session.getTransaction().commit();
            return pr;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return pr;
    }
    public List<Femme> findEpousesBetweenDates(Homme homme, Date dateDebut, Date dateFin) {
    Session session = null;
    List<Femme> epouses = null;

    try {
        // Ouvrir la session
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Requête HQL pour récupérer les épouses d'un homme entre deux dates
        epouses = session.createQuery(
            "SELECT m.femme FROM Mariage m WHERE m.homme = :homme AND m.dateDebut BETWEEN :dateDebut AND :dateFin")
            .setParameter("homme", homme)
            .setParameter("dateDebut", dateDebut)
            .setParameter("dateFin", dateFin)
            .list();

        // Affichage des épouses
        System.out.println("Épouses d'un homme entre " + dateDebut + " et " + dateFin + ":");
        for (Femme femme : epouses) {
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

    return epouses;  // Retourner la liste des épouses
}
public void afficherMariagesHomme(Homme homme) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    
    List<Mariage> mariagesEnCours = session.createQuery("FROM Mariage m WHERE m.homme = :homme AND m.dateFin IS NULL")
        .setParameter("homme", homme)
        .list();
    
    List<Mariage> mariagesEchoues = session.createQuery("FROM Mariage m WHERE m.homme = :homme AND m.dateFin IS NOT NULL")
        .setParameter("homme", homme)
        .list();

    System.out.println("Nom : " + homme.getNom());
    System.out.println("Mariages En Cours :");
    for (Mariage mariage : mariagesEnCours) {
        System.out.println("Femme : " + mariage.getFemme().getNom() + " Date Début : " + mariage.getDateDebut() + " Nbr Enfants : " + mariage.getNbrEnfants());
    }

    System.out.println("Mariages échoués :");
    for (Mariage mariage : mariagesEchoues) {
        System.out.println("Femme : " + mariage.getFemme().getNom() + " Date Début : " + mariage.getDateDebut() + " Date Fin : " + mariage.getDateFin() + " Nbr Enfants : " + mariage.getNbrEnfants());
    }
    
    session.close();
}

public Long countHommesMarriedByFourFemmesBetweenDates(Date dateDebut, Date dateFin) {
    Session session = null;
    Long result = 0L;

    try {
        // Ouvrir la session
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Créer une requête Criteria (ancienne méthode sans CriteriaBuilder)
        Criteria criteria = session.createCriteria(Mariage.class);

        // Appliquer les conditions sur la date de début et de fin
        criteria.add(Restrictions.between("dateDebut", dateDebut, dateFin));

        // Appliquer une projection pour compter le nombre d'hommes distincts ayant 4 femmes ou plus
        criteria.setProjection(Projections.projectionList()
            .add(Projections.groupProperty("homme"))
            .add(Projections.count("femme"))
        );

   

        // Exécuter la requête et récupérer le résultat
        List resultList = criteria.list();
        result = (long) resultList.size();  // Le nombre d'hommes

        // Commit des transactions
        session.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();  // Afficher les erreurs si elles surviennent
    } finally {
        if (session != null) {
            session.close();  // Fermer la session dans le bloc finally
        }
    }

    // Retourner le résultat du comptage
    return result;
}



}
