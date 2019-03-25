package school.utils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import school.connection.SessionUtils;
import school.model.Student;

import java.util.List;


public class CRUDUtilsTest {
    @Test
    public void shouldThrowAConcurrentException() throws InterruptedException {
        Session session = SessionUtils.getSession();
        try {
            CRUDUtils.insert(new Student(1, "Student", "One",'M', 21));
            CRUDUtils.insert(new Student(2, "Kurt", "Weller", 'M', 19));

            Runnable runnable1 = () -> {
                try {
                    updateStudents(5);
                } catch (InterruptedException e) {
                    System.out.println("Got interrupted: " + Thread.currentThread().getName());
                    e.printStackTrace();
                }
            };

            Runnable runnable2 = () -> {
                try {
                    updateStudents(10);
                } catch (InterruptedException e) {
                    System.out.println("Got interrupted: " + Thread.currentThread().getName());
                    e.printStackTrace();
                }
            };

            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        } finally {
            session.close();
        }
    }

    public void updateStudents(int waitForSecs) throws InterruptedException {
        Session session = SessionUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            System.out.println("Updating students: " + Thread.currentThread().getName());

            Query query = session.createQuery("from Student");
            List<Student> students = query.list();
            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));

            for (Student s : students) {
                s.setAge(s.getAge() + 10);
                session.save(s);
            }

            System.out.println("Waiting for " + waitForSecs + " secs: " + Thread.currentThread().getName());

            Thread.sleep(waitForSecs * 1000);

            System.out.println("Committing students: " + Thread.currentThread().getName());

            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));
            tx.commit();
            System.out.println("Committed students: " + Thread.currentThread().getName());
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

}