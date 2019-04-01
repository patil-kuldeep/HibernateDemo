package school.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school.model.Principal;
import school.model.School;
import school.model.Student;
import school.model.Teacher;
import school.service.SchoolService;

import java.util.Arrays;

public class ApplicationTester {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SchoolService schoolService = (SchoolService) context.getBean("schoolServiceImpl");

        //Add Student
        /*Student s1 = new Student();
        s1.setFirstName("Rahul");
        s1.setLastName("Sharma");
        s1.setGender('M');
        s1.setAge(13);
        s1.setRollNo(10);

        Student s2 = new Student();
        s2.setFirstName("Priyanka");
        s2.setLastName("Shroff");
        s2.setGender('F');
        s2.setAge(16);
        s2.setRollNo(11);

        Student s3 = new Student();
        s3.setFirstName("Rohan");
        s3.setLastName("Sharma");
        s3.setGender('M');
        s3.setAge(14);
        s3.setRollNo(9);

        schoolService.insertAllStudents(Arrays.asList(s1, s2, s3));

        //Insert few teachers
        Teacher teacher = new Teacher();
        teacher.setFirstName("Radhika");
        teacher.setLastName("Kapoor");
        teacher.setGender('F');
        teacher.setAge(59);

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Sushant");
        teacher1.setLastName("Gandhi");
        teacher1.setAge(56);
        teacher1.setGender('M');

        schoolService.insertAllTeachers(Arrays.asList(teacher, teacher1));

        //Insert a school
        School school = new School();
        school.setName("Army Public School");
        school.setPhoneNo("CBSE");

        schoolService.insertSchool(school);

        Principal principal = new Principal();
        principal.setFirstName("Suchitra");
        principal.setLastName("Ghosh");
        principal.setYearsOfExperience(40);
        principal.setGender('F');
        principal.setAge(59);

        schoolService.insertPrincipal(principal);*/

        try {
            schoolService.addStudentToSchool(60, 1);
            schoolService.addMultipleStudentsToSchool(60, Arrays.asList(2,3));
            schoolService.addPrincipalToSchool(60, 7);
            schoolService.addTeachersToSchool(Arrays.asList(4,5), 60);

            Student student = schoolService.getStudentById(2);
            School school1 = schoolService.getSchoolById(60);


            System.out.println("Student got updated with School: " + student.getSchool().getName());
            System.out.println("School got updated with Student: " + school1.getStudents().get(0).getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
