package school.dao;

import school.model.Student;

import java.util.List;

public interface StudentDao extends IBaseDao<Student> {

    Student getStudentByRollNumber(int rollNo);

    List<Student> getStudentByGender(char gender);

    Student getStudentByFirstName(String firstName);

}
