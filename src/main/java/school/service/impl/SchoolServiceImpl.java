package school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.dao.*;
import school.dao.PTADao;
import school.model.*;
import school.service.SchoolService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    PrincipalDao principalDao;

    @Autowired
    BoardDao boardDao;

    @Autowired
    PTADao ptaDao;

    @Autowired
    ParentDao parentDao;

    @Override
    @Transactional
    public void insertStudent(Student student) {
        Student s1 = studentDao.getStudentByRollNumber(student.getRollNo());
        if(s1 == null) {
            studentDao.save(student);
            System.out.println("Student saved successfully!");
        } else {
            System.out.println("Student already exists!");
        }
    }

    @Override
    @Transactional
    public void deleteStudent(String firstName) {
        Student s1 = studentDao.getStudentByFirstName(firstName);
        if(s1 != null) {
            studentDao.delete(s1);
            System.out.println("Student with name: "+ firstName + " deleted successfully!");
        } else {
            System.out.println("Student does not exists!");
        }
    }

    @Override
    @Transactional
    public void insertAllStudents(List<Student> students) {
        studentDao.saveAll(students);
        System.out.println("All the students successfully registered!");
    }

    @Override
    @Transactional
    public void insertAllTeachers(List<Teacher> teachers) {
        teacherDao.saveAll(teachers);
        System.out.println("All the teachers successfully registered!");
    }

    @Override
    @Transactional
    public void insertTeacher(Teacher teacher) {
        teacherDao.save(teacher);
        System.out.println("Teacher inserted successfully!");
    }

    @Override
    @Transactional
    public void addTeachersToSchool(List<Integer> teacherIds, int schoolId) throws Exception {
        School school = schoolDao.get(schoolId);
        List<Teacher> teachers = new ArrayList<>();
        if(school != null) {
            for(int tId : teacherIds) {
                Teacher teacher = teacherDao.get(tId);
                if(teacher != null) {
                    teacher.setSchool(school);
                    teachers.add(teacher);
                }
            }
            school.setTeachers(teachers);
            schoolDao.update(school);
        } else {
            System.out.println("School with Id: " + schoolId + " is invalid!");
            throw new Exception("School invalid");
        }
    }

    @Override
    @Transactional
    public void insertSchool(School school) {
        schoolDao.save(school);
        System.out.println("School inserted successfully!");
    }

    @Override
    @Transactional
    public void addStudentToSchool(int schoolId, int studentId) throws Exception {
        Student student = studentDao.get(studentId);
        if(student == null) {
            System.out.println("Student with Id: " + studentId + "does not exists!");
            throw new Exception("Student not found");
        }
        School school = schoolDao.get(schoolId);
        if(school == null) {
            System.out.println("School with Id: " + schoolId + "does not exists!");
            throw new Exception("School doesnt exists.");
        }
        student.setSchool(school);
        school.setStudents(Arrays.asList(student));

        //Only had to update school and not the student because it
        // gets updated automatically due to cascading
        schoolDao.update(school);
    }

    @Override
    @Transactional
    public void addMultipleStudentsToSchool(int schoolId, List<Integer> studentIds) throws Exception {
        List<Student> students = new ArrayList<>();
        School school = getSchoolById(schoolId);

        if(school == null) {
            System.out.println("School with Id: " + schoolId + "does not exists!");
            throw  new Exception("School is invalid.");
        }
        for(int sId : studentIds) {
            Student student = getStudentById(sId);
            if(student == null) {
                System.out.println("Student with Id: " + sId + "does not exists.");
                throw new Exception("One or more students in the list are not found. Aborting addition!");
            }
            student.setSchool(school);
            students.add(student);
        }
        school.setStudents(students);
        schoolDao.update(school);
    }

    @Override
    @Transactional
    public Student getStudentById(int id) {
        return studentDao.get(id);
    }

    @Override
    @Transactional
    //TODO: Do we have to write @Transactional for getters also? Getting something from database doesnt need transaction. right?
    public School getSchoolById(int id) {
        return schoolDao.get(id);
    }

    @Override
    @Transactional
    public void insertPrincipal(Principal principal) {
        principalDao.save(principal);
        System.out.println("Principal inserted successfully!");
    }

    @Override
    @Transactional
    public void removePrincipal(int principalId) {
        //TODO: Check by adding a principal in school and then deleting it from db.
        // Does it remove the association with school too because of cascading? It should.
        Principal p1 = principalDao.get(principalId);
        if(p1 == null) {
            System.out.println("Principal to remove does not exists!");
        }
        principalDao.delete(p1);
        System.out.println("Principal deleted successfully!");
    }

    @Override
    @Transactional
    //TODO: difference between javax transactional and spring.hibernate transactional
    public void addPrincipalToSchool(int schoolId, int principalId) throws Exception {
        //Check if principal exists
        Principal principal = principalDao.get(principalId);
        if(principal == null) {
            System.out.println("Principal with Id: " + principalId + " does not exists.");
            throw new Exception("Principal to add does not exists");
        }
        //Check if the school exists
        School school = schoolDao.get(schoolId);
        if(school == null) {
            System.out.println("School with Id: " + schoolId + "does not exists.");
            throw new Exception("School invalid");
        }
        //Check if school already has a principal
        Principal schoolP = school.getPrincipal();
        if(schoolP != null) {
            System.out.println("This school already has a principal. Cannot add another. Use update.");
        } else {
         school.setPrincipal(principal);
         principal.setSchool(school);
         schoolDao.update(school);
         System.out.println("Principal added to school");
        }
    }

    @Override
    @Transactional
    public void updatePrincipalOfSchool(int schoolId, int principalId) throws Exception {
        Principal principal = principalDao.get(principalId);
        if(principal == null) {
            System.out.println("Principal with Id: " + principalId + " does not exists.");
            throw new Exception("Principal to update does not exists");
        }
        //Check if the school exists
        School school = schoolDao.get(schoolId);
        if(school == null) {
            System.out.println("School with Id: " + schoolId + "does not exists.");
            throw new Exception("School with Id not present");
        }
        school.setPrincipal(principal);
        principal.setSchool(school);
        schoolDao.update(school);
        System.out.println("Principal updated successfully!");
    }

    @Override
    @Transactional
    public void insertBoard(Board board) {
        boardDao.save(board);
        System.out.println("Board inserted successfully!");
    }

    @Override
    @Transactional
    public void removeBoard(int boardId) throws Exception {
        Board board = boardDao.get(boardId);
        if(board != null) {
            boardDao.delete(board);
            System.out.println("Board deleted successfully!");
        } else {
            System.out.println("Board with this Id not found. Cannot delete");
            throw new Exception("Board not valid");
        }
    }

    @Override
    @Transactional
    public void addSchoolToBoard(int schoolId, int boardId) throws Exception {
        School school = schoolDao.get(schoolId);
        if(school == null) {
            System.out.println("School doesnt exists");
            throw new Exception("School doesnt exists");
        }
        Board board = boardDao.get(boardId);
        if(board == null) {
            System.out.println("Board doesnt exists");
            throw new Exception("Board doesnt exists");
        }
        school.setBoard(board);
        board.getSchools().add(school);

        //TODO: Check if this throws NPE as the board doesnt have any schools
        //This should update the school too
        boardDao.update(board);
    }

    @Override
    @Transactional
    public void addSchoolsToBoard(List<Integer> schoolIds, int boardId) {
        Board board = boardDao.get(boardId);
        List<School> schools = new ArrayList<>();
        for(int schoolId : schoolIds) {
            School school = schoolDao.get(schoolId);
            school.setBoard(board);
            schools.add(school);
        }
        board.setSchools(schools);
        boardDao.update(board);
    }

    @Override
    @Transactional
    public void insertParent(Parent parent) {
        parentDao.save(parent);
        System.out.println("Parent inserted successfully!");
    }

    @Override
    @Transactional
    public void removeParent(int parentId) throws Exception {
        Parent parent = parentDao.get(parentId);
        if(parent == null) {
            System.out.println("Parent to add doesnt exists");
            throw new Exception("Parent doesnt exists");
        }
        parentDao.delete(parent);
    }

    @Override
    @Transactional
    public void updateParent(Parent parent) {
        Parent p1 = parentDao.get(parent.getId());
        if(p1 != null) {
            parentDao.update(parent);
        } else {
            System.out.println("Parent doesnt exists to update");
        }
    }

    @Override
    @Transactional
    public void insertPTA(PTA pta) {
        ptaDao.save(pta);
        System.out.println("PTA inserted successfully!");
    }

    @Override
    @Transactional
    public void deletePTA(int ptaId) {
        PTA pta = ptaDao.get(ptaId);
        if(pta != null) {
            ptaDao.delete(pta);
            System.out.println("PTA deleted successfully");
        } else {
            System.out.println("PTA not found");
        }
    }

    @Override
    @Transactional
    public void updatePTAWithSchools(int ptaId, List<Integer> schoolIds) {
        PTA pta = ptaDao.get(ptaId);
        List<School> schools = new ArrayList<>();
        for(int schoolId : schoolIds) {
            School school = schoolDao.get(schoolId);
            schools.add(school);
            school.setPtas(Arrays.asList(pta));
            schoolDao.update(school);
        }
        pta.setSchools(schools);
        ptaDao.update(pta);
        System.out.println("PTA updated with schools successfully!");
    }

    @Override
    @Transactional
    public void updatePTAWithParents(int ptaId, List<Integer> parentIds) {
        PTA pta = ptaDao.get(ptaId);
        List<Parent> parents = new ArrayList<>();
        for(int parentId : parentIds) {
            Parent parent = parentDao.get(parentId);
            parents.add(parent);
            parent.setPta(pta);
        }
        pta.setMembers(parents);
        ptaDao.update(pta);
        System.out.println("PTA updated with parents!");
    }

    @Override
    @Transactional
    public List<Grade> listGradesForTeacherById(int id) {
        List<Grade> grades = teacherDao.getGradesForTeacher(id);
        if(grades.size()==0) {
            System.out.println("This teacher does not have any grades assigned!");
        } else {
            System.out.println("Grades for this teacher with Id: " + id + grades);
            return grades;
        }
        return null;
    }

    @Override
    @Transactional
    public List<Student> getStudentByGender(char gender) {
        List<Student> students = studentDao.getStudentByGender(gender);
        if(students.size() > 0) {

            System.out.println("Student details: ");
            for(Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("Students with this gender do not exist!");
        }
        return students;
    }
}
