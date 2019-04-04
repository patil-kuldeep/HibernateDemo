package school.service;

import school.model.*;

import java.util.List;
import java.util.Map;

public interface SchoolService {

    void insertStudent(Student person);

    void deleteStudent(String firstName);

    void insertAllStudents(List<Student> students);

    Student getStudentByRollNumber(int rollNo) throws Exception;

    List<Student> getAllStudents();

    List<Grade> listGradesForTeacherById(int id);

    List<Student> getStudentByGender(char gender);

    void insertAllTeachers(List<Teacher> teachers);

    void insertTeacher(Teacher teacher);

    void addTeachersToSchool(List<Integer> teacherIds, int schoolId) throws Exception;

    void insertSchool(School school);

    void addStudentToSchool(int schoolId, int studentId) throws Exception;

    void addMultipleStudentsToSchool(int schoolId, List<Integer> studentIds) throws Exception;

    Student getStudentById(int id);

    School getSchoolById(int id);

    void insertPrincipal(Principal principal);

    void removePrincipal(int principalId);

    void addPrincipalToSchool(int schoolId, int PrincipalId) throws Exception;

    void updatePrincipalOfSchool(int schoolId, int PrincipalId) throws Exception;

    void insertBoard(Board board);

    void removeBoard(int boardId) throws Exception;

    void addSchoolToBoard(int schoolId, int boardId) throws Exception;

    void addSchoolsToBoard(List<Integer> schoolId, int boardId);

    void insertParent(Parent parent);

    void removeParent(int parentId) throws Exception;

    void updateParent(Parent parent);

    void insertPTA(PTA pta);

    void deletePTA(int ptaId);

    void updatePTAWithSchools(int ptaId, List<Integer> schoolIds);

    void updateSchoolWithPTAs(int school_id, List<Integer> ptaIds);

    void updatePTAWithParents(int ptaId, List<Integer> parentIds);

    void deleteSchool(int schoolId);

    List<Student> findAllStudentsByAttribute(Map<String, Object> criteria) throws Exception;
}
