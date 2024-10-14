package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Mariage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;

public class MariageService implements IDao<Mariage> {

    @Override
    public boolean create(Mariage o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public Mariage getById(int id) {
        Session session = null;
         Mariage e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Mariage) session.get(Mariage.class, id);
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
    public List<Mariage> getAll() {
       Session session = null;
        List<Mariage>  pr = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            pr = session.createQuery("from Mariage").list();
            session.getTransaction().commit();
            return pr;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return pr;
    }
}
