package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.TeacherDao;
import school.model.Grade;
import school.model.Teacher;

import java.util.List;

@Component
public class TeacherDaoImpl extends BaseDao<Teacher> implements TeacherDao {

    @Override
    public List<Grade> getGradesForTeacher(int teacherId) {
        Query query = currentSession().createQuery("from Teacher where id : teacherId");
        query.setParameter("teacherId", teacherId);
        Teacher t1 = (Teacher) query.list().get(0);

        return t1.getGrades();
    }
}
