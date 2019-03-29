package school.dao;

import school.model.Grade;
import school.model.Teacher;

import java.util.List;

public interface TeacherDao extends IBaseDao<Teacher> {

    List<Grade> getGradesForTeacher(int teacherId);
}
