package school.dao.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import school.dao.StudentDao;
import school.model.Student;

import java.util.List;

@ContextConfiguration(locations = "classpath:spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoImplTest {

    @Autowired
    StudentDao studentDao;

    @Test
    @Transactional
    @Rollback
    public void shouldCreateStudent() {
        Student student = new Student();
        student.setFirstName("Test2");
        student.setLastName("User2");
        student.setAge(12);
        student.setGender('M');
        student.setRollNo(2);

        List<Student> students = studentDao.getAll();
        int size = students.size();

        studentDao.save(student);

        Assert.assertEquals(size+1, studentDao.getAll().size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void shouldNotCreateStudentWithDuplicateRollNo() {
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(1);

        studentDao.save(studentX);

        Student student1 = new Student();
        student1.setFirstName("Test3");
        student1.setLastName("User3");
        student1.setAge(15);
        student1.setGender('M');
        student1.setRollNo(1);

        studentDao.save(student1);

       studentDao.getCurrentSession().flush();
    }

    @Test
    @Transactional
    public void shouldDeleteStudentById() {
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(1);

        int id = (int) studentDao.save(studentX);

        studentDao.delete(studentDao.get(id));

        Assert.assertNull(studentDao.get(id));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void shouldThrowExceptionForDeleteNonExistentStudent() {
        //delete() does not throw exception if the entity passed to it is not persistent. It handles the delete for transient objects too.
        //So, it was not throwing any exception. Thus, used a non-existent entity fetch to form the negative test case for delete.
        final int NE_STUDENT_ID = 7;
        studentDao.delete(studentDao.get(NE_STUDENT_ID));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void shouldThrowExceptionForDeletedStudent() {
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(1);

        int id = (int) studentDao.save(studentX);

        studentDao.delete(studentDao.get(id));

        studentDao.delete(studentDao.get(id));
    }

    @Test
    @Transactional
    public void shouldUpdateLastNameOfStudent() {
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(1);

        int id = (int) studentDao.save(studentX);

        studentDao.getCurrentSession().flush();

        Student studentToUpdate = studentDao.get(id);

        studentToUpdate.setLastName("Updated");
        studentDao.update(studentToUpdate);
        studentDao.getCurrentSession().flush();
        Student updatedStudent = studentDao.get(id);

        Assert.assertEquals("Updated", updatedStudent.getLastName());
    }

    @Test
    @Transactional
    public void shouldFailToUpdateRollNumberOfStudent() {
        //Update for roll number attribute should silently fail as it is marked as updatable=false
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(2);

        int id = (int) studentDao.save(studentX);

        studentDao.getCurrentSession().flush();

        Student studentToUpdate = studentDao.get(id);

        studentToUpdate.setRollNo(3);
        studentDao.save(studentToUpdate);
        studentDao.getCurrentSession().flush();
        studentDao.getCurrentSession().clear();
        Assert.assertEquals(2, studentDao.get(id).getRollNo());
    }

    @Test
    @Transactional
    public void shouldGetAllStudentsPresent() {
        Student studentX = new Student();
        studentX.setFirstName("Test1");
        studentX.setLastName("User1");
        studentX.setAge(12);
        studentX.setGender('M');
        studentX.setRollNo(1);

        studentDao.save(studentX);

        Student studentXI = new Student();
        studentXI.setFirstName("Test2");
        studentXI.setLastName("User2");
        studentXI.setAge(13);
        studentXI.setGender('F');
        studentXI.setRollNo(2);

        studentDao.save(studentXI);

        Student studentXII = new Student();
        studentXII.setFirstName("Test3");
        studentXII.setLastName("User3");
        studentXII.setAge(14);
        studentXII.setGender('M');
        studentXII.setRollNo(3);

        studentDao.save(studentXII);

        studentDao.getCurrentSession().flush();
        studentDao.getCurrentSession().clear();

        Assert.assertEquals(3, studentDao.getAll().size());
    }
}