package school.client;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import school.connection.SessionUtils;
import school.model.*;
import school.utils.CRUDUtils;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
     try {
         cascadeInsert();
         cascadeUpdate();
         cascadeDelete();
         bulkInsertForStudents(10);
         bulkUpdateForStudents();
     } finally {
         SessionUtils.shutDown();
     }
    }

    public static void bulkUpdateForStudents() {
        Transaction tx = null;
        Session session = SessionUtils.getSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Student");
            List<Student> students = query.list();
            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));
            for(int i= 0; i<students.size(); i++) {
                students.get(i).setAge(students.get(i).getAge() + (i+5));
                session.update(students.get(i));
                if(i % 5 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));
            tx.commit();
        } catch (HibernateException he) {
            if(tx != null) tx.rollback();
            he.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void bulkInsertForStudents(int numberOfRecords) {
        Transaction txn = null;
        Session session = SessionUtils.getSession();
        try {
            txn = session.beginTransaction();
            for(int i = 1; i <= numberOfRecords; i++) {
                Student student = new Student(i+20, "TestUser"+i, "Test LastName"+i, 'M', 8);
                session.save(student);
                //batch size is configured to be 50 in the hibernate.cfg.xml
                if(i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            txn.commit();
        } catch (HibernateException he) {
            if(txn != null) txn.rollback();
            throw he;
        } finally {
            session.close();
        }
    }
    public static void cascadeUpdate() {
        Session session = SessionUtils.getSession();
        Query query1 = session.createQuery("from Student");
        List<Student> students = query1.list();
        students.stream().forEach(student -> student.setAge(student.getAge() + 10));

        Query query2 = session.createQuery("from Teacher");
        List<Teacher> teachers = query2.list();
        teachers.stream().forEach(teacher -> teacher.setAge(teacher.getAge() + 7));

        Query query3 = session.createQuery("from School");
        List<School> schools = query3.list();
        schools.get(0).setPhoneNo("8888888");

        CRUDUtils.update(session, schools.get(0));
        session.close();
    }

    public static void cascadeDelete() {
        Session session = SessionUtils.getSession();
        Query query = session.createQuery("from School where name = :name");
        query.setParameter("name", "KV");
        List schoolList = query.list();
        CRUDUtils.delete(session, schoolList.get(0));
        session.close();
    }

    public static void cascadeInsert() {
            School school = new School("KV", "555555");
            Student st1 = new Student(1,"Rahul", "Roy", 'M', 12);
            Student st2 = new Student(3, "Rohini", "Singh", 'F', 11);

            Teacher t1 = new Teacher("Rashmi", "Patil", 'F', 34);
            Teacher t2 = new Teacher("Fiza", "Shukla", 'F', 23);
            t1.setSchool(school);
            t2.setSchool(school);

            Principal p1 = new Principal("Tiara", "Mehta", 'F', 35, 12);
            p1.setSchool(school);

            Grade g1 = new Grade("Grade 1");
            Grade g2 = new Grade("Grade 2");

            g1.setTeacher(t2);
            g2.setTeacher(t1);

            st1.setGrade(g1);
            st1.setSchool(school);
            st2.setGrade(g2);
            st2.setSchool(school);

            t1.setGrades(Arrays.asList(g1, g2));
            Clerk clerk = new Clerk("Raj", "Huda", 'M', 34, "Accounts");
            clerk.setSchool(school);

            Board board1 = new Board("ICSE");
            board1.setSchools(Arrays.asList(school));

            school.setStudents(Arrays.asList(st1, st2));
            school.setBoard(board1);
            school.setPrincipal(p1);
            school.setClerks(Arrays.asList(clerk));
            school.setTeachers(Arrays.asList(t1, t2));

            clerk.setSchool(school);

            t1.setSchool(school);
            t2.setSchool(school);

            st1.setSchool(school);
            st2.setSchool(school);

            CRUDUtils.insert(board1);
    }
}
