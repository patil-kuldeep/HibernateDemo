package school.client;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import school.connection.SessionUtils;
import school.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Session session = SessionUtils.getSession();

        try {
            addEntities(session);
            updateEntities(session);
            deleteEntries(session);
        } finally {
            session.close();
            SessionUtils.shutDown();
        }
    }

    private  static void deleteEntries(Session session) {
        Transaction txn = null;
        try {
            txn = session.beginTransaction();

            Query query = session.createQuery("from Student where firstName = 'Kishor'");
            Student student = (Student) query.list().get(0);

            School school = student.getSchool();
            Grade grade = student.getGrade();

            List<Student> studentList = new ArrayList<>(school.getStudents());
            studentList.remove(student);
            school.setStudents(studentList);


            studentList = new ArrayList<>(grade.getStudents());
            studentList.remove(student);
            grade.setStudents(studentList);

            session.save(school);
            session.save(grade);

            session.delete(student);

            txn.commit();
        }  catch (Exception e){
            if(txn != null) txn.rollback();
            throw e;
        }
    }

    private static void updateEntities(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Student");
            List<Student> students = query.list();
            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));

            students.stream().forEach(student -> student.setAge(student.getAge() + 10));
            students.stream().forEach(student -> session.update(student));

            System.out.println("======");

            students.stream().forEach(student -> System.out.println(student.getFirstName() + " : " + student.getAge()));
            tx.commit();
        } catch (HibernateException he) {
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
    }

    private static Student createStudent(int rollNumber, String firstName, char gender, int age) {
        Student student = new Student();
        student.setRollNo(rollNumber);
        student.setFirstName(firstName);
        student.setGender(gender);
        student.setAge(age);
        return student;
    }

    public static void addEntities(Session session) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student student1 = createStudent(21, "Rafi", 'M', 8);
            Student student2 = createStudent(19, "Kishor", 'M', 9);
            Student student3 = createStudent(17, "Mohit", 'M', 8);
//
//            session.save(student1);
//            session.save(student2);
//            session.save(student3);

            Grade grade1 = new Grade();
            grade1.setName("Third Grade");
            grade1.setStudents(Arrays.asList(student1, student2));

            Grade grade2 = new Grade();
            grade2.setName("Second Grade");
            grade2.setStudents(Arrays.asList(student3));

            student1.setGrade(grade1);
            student2.setGrade(grade1);
            student3.setGrade(grade2);

            session.save(grade1);
            session.save(grade2);

            Clerk cl1 = new Clerk();
            cl1.setFirstName("Ramesh");
            cl1.setGender('M');
            cl1.setAge(39);
            cl1.setDepartment("Accounts");

            Clerk cl2 = new Clerk();
            cl2.setFirstName("Kamal");
            cl2.setGender('M');
            cl2.setAge(47);
            cl2.setDepartment("Cultural");

            List<Clerk> clerks = new ArrayList<>();
            clerks.add(cl1);
            clerks.add(cl2);


            Teacher teacher1 = new Teacher();
            teacher1.setFirstName("Vritika");
            teacher1.setAge(29);
            teacher1.setGender('F');
            teacher1.setLastName("Gosh");
            teacher1.setGrades(Arrays.asList(grade1));

            Teacher teacher2 = new Teacher();
            teacher2.setFirstName("Rishabh");
            teacher2.setAge(31);
            teacher2.setGender('M');
            teacher2.setLastName("Mehta");
            teacher2.setGrades(Arrays.asList(grade2));


            List<Teacher> teachers = new ArrayList<>();
            teachers.add(teacher1);
            teachers.add(teacher2);

            session.save(cl1);
            session.save(cl2);
            session.save(teacher1);
            session.save(teacher2);

            Principal principal = new Principal();
            principal.setAge(57);
            principal.setGender('F');
            principal.setFirstName("Akriti");
            principal.setLastName("Chauhan");
            principal.setYearsOfExperience(24);

            session.save(principal);

            School school = new School();
            school.setName("Army Public School");
            school.setPhoneNo("020-471-8989");
            school.setStudents(Arrays.asList(student1, student2, student3));
            school.setTeachers(teachers);
            school.setPrincipal(principal);
            school.setClerks(clerks);

            student1.setSchool(school);
            student2.setSchool(school);
            student3.setSchool(school);

            session.save(school);
            session.save(student1);
            session.save(student2);
            session.save(student3);
            tx.commit();
            System.out.println("Finished Execution!");

        } catch(HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        }
    }
}
