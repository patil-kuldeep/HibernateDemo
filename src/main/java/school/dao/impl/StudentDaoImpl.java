package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.StudentDao;
import school.model.Student;

import java.util.List;

@Component
public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {
    @Override
    public Student getStudentByRollNumber(int rollNo) {
        Query query = currentSession().createQuery("from Student where rollNo = :rollN");
        query.setParameter("rollN", rollNo);
        List<Student> students = query.list();
        if(students.size() != 0) {
            return students.get(0);
        }
        return null;
    }

    @Override
    public List<Student> getStudentByGender(char gender) {
        Query query = currentSession().createQuery("from Student where gender = :gender");
        query.setParameter("gender", gender);
        List<Student> students = query.list();
        return students;
    }

    @Override
    public Student getStudentByFirstName(String firstName) {
        Query query = currentSession().createQuery("from Student where firstName = :firstName");
        query.setParameter("firstName", firstName);
        return (Student) query.list().get(0);
    }
}
