package school.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import school.connection.SessionUtils;

public class CRUDUtils {

    public static void insert(Object object) {
        Session session = SessionUtils.getSession();

        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.save(object);
            txn.commit();
        } catch (HibernateException he) {
            if (txn != null) {
                txn.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    public static void delete(Session session, Object object) {
         Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.delete(object);
            txn.commit();
        } catch (HibernateException he) {
            if (txn != null) {
                txn.rollback();
            }
            throw he;
        }
    }

    public static void update(Session session, Object object) {
        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.update(object);
            txn.commit();
        } catch (HibernateException he) {
            if (txn != null) {
                txn.rollback();
            }
            throw he;
        }
    }
}
