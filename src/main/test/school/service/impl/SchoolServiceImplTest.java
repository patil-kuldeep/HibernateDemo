package school.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import school.dao.impl.StudentDaoImpl;
import school.model.Student;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchoolServiceImplTest {

    @InjectMocks
    SchoolServiceImpl schoolService;

    @Mock
    StudentDaoImpl studentDao;


    @Test
    public void shouldInsertStudentWithDetails() {
        Student student1 = new Student();
        student1.setFirstName("Rock");
        student1.setLastName("Walter");
        student1.setAge(12);
        student1.setGender('M');
        student1.setRollNo(4);

        schoolService.insertStudent(student1);

        //Verify that service layer calls DAO layer save() method once with
        // student object that was given
        verify(studentDao, times(1)).save(student1);
    }

    @Test
    public void shouldInsertMultipleStudentsWithDetails() {

        List<Student> students = createStudentEntity(2);

        schoolService.insertAllStudents(students);

        verify(studentDao, times(1)).saveAll(students);
    }

    @Test
    public void shouldGetAllTheStudents(){

        List<Student> students = createStudentEntity(3);

        when(studentDao.getAll()).thenReturn(students);

        List<Student> studentList = schoolService.getAllStudents();

        Assert.assertEquals(students.size(), studentList.size());
        Assert.assertEquals(students, studentList);
    }

    @Test
    public void shouldGetStudentsByRollNumber() throws Exception {
        List<Student> students = createStudentEntity(3);

        when(studentDao.getStudentByRollNumber(1)).thenReturn(students.get(0));
        when(studentDao.getStudentByRollNumber(2)).thenReturn(students.get(1));
        when(studentDao.getStudentByRollNumber(3)).thenReturn(students.get(2));

        Student student1 = schoolService.getStudentByRollNumber(2);
        Assert.assertEquals(students.get(1).getAge(), student1.getAge());

        Student student2 = schoolService.getStudentByRollNumber(1);
        Assert.assertEquals(students.get(0).getAge(), student2.getAge());

        Student student3 = schoolService.getStudentByRollNumber(3);
        Assert.assertEquals(students.get(2).getAge(), student3.getAge());
    }

    @Test
    public void shouldGetStudentsByAge() throws Exception {
        List<Student> students = createStudentEntity(3);

        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("age", 12);

        when(studentDao.findAllByAttribute(criteriaMap)).thenReturn(Arrays.asList(students.get(2)));

        List<Student> studentList = schoolService.findAllStudentsByAttribute(criteriaMap);
        Assert.assertEquals(1, studentList.size());
        Assert.assertEquals("Test21", studentList.get(0).getFirstName());
        verify(studentDao, times(1)).findAllByAttribute(criteriaMap);
    }

    @Test
    public void deleteStudent() {
        List<Student> students = createStudentEntity(1);

        when(studentDao.getStudentByFirstName("Test01")).thenReturn(students.get(0));

        schoolService.deleteStudent("Test01");

        verify(studentDao, times(1)).getStudentByFirstName("Test01");
        verify(studentDao, times(1)).delete(students.get(0));
    }

    private List<Student> createStudentEntity(int numberOfRecordsToBeCreated) {
        List<Student> students = new ArrayList<>();

        for(int i=0;i < numberOfRecordsToBeCreated; i++) {
            Student student = new Student();
            student.setFirstName("Test"+i+1);
            student.setLastName("User"+i+1);
            student.setRollNo(i+1);
            student.setGender('M');
            student.setAge(10+i);

            students.add(student);
        }
        return students;
    }
}