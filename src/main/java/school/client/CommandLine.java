package school.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school.model.*;
import school.service.SchoolService;

import java.util.Arrays;
import java.util.List;

public class CommandLine {
    static final String ADD_STUDENT = "add_student";
    static final String SHOW_STUDENT = "show_student";
    static final String REMOVE_STUDENT = "remove_student";
    static final String ADD_SCHOOL = "add_school";
    static final String REMOVE_SCHOOL = "remove school";
    static final String ADD_TEACHER = "add_teacher";
    static final String ADD_TEACHER_TO_SCHOOL = "add_t_to_school";
    static final String ADD_STUDENT_TO_SCHOOL = "add_s_to_school";
    static final String ADD_PRINCIPAL = "add_principal";
    static final String ADD_PRINCIPAL_TO_SCHOOL = "add_p_to_school";
    static final String ADD_BOARD = "add_board";
    static final String UPDATE_SCHOOL_PRINCIPAL = "update_principal";
    static final String REMOVE_PRINCIPAL = "remove_principal";

    static final List<String> commands = Arrays.asList(REMOVE_PRINCIPAL, UPDATE_SCHOOL_PRINCIPAL, ADD_BOARD, ADD_PRINCIPAL_TO_SCHOOL, SHOW_STUDENT, ADD_STUDENT, REMOVE_STUDENT, ADD_TEACHER, ADD_TEACHER_TO_SCHOOL, ADD_STUDENT_TO_SCHOOL, REMOVE_SCHOOL, ADD_PRINCIPAL, ADD_SCHOOL);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SchoolService schoolService = (SchoolService) context.getBean("schoolServiceImpl");

        if (args.length == 0 || !commands.contains(args[0])) {
            printHelpMessage();
            return;
        }
        switch(args[0]) {
            case SHOW_STUDENT:
                showStudent(args, schoolService);
                break;
            case ADD_STUDENT:
                addStudent(args, schoolService);
                break;
            case REMOVE_STUDENT:
                removeStudent(args, schoolService);
                break;
            case ADD_SCHOOL:
                addSchool(args, schoolService);
                break;
            case ADD_TEACHER:
                addTeacher(args, schoolService);
                break;
            case ADD_TEACHER_TO_SCHOOL:
                addTeacherToSchool(args, schoolService);
                break;
            case ADD_STUDENT_TO_SCHOOL:
                addStudentToSchool(args, schoolService);
                break;
            case ADD_PRINCIPAL:
                addPrincipal(args, schoolService);
                break;
            case ADD_PRINCIPAL_TO_SCHOOL:
                addPrincipalToSchool(args, schoolService);
                break;
            case UPDATE_SCHOOL_PRINCIPAL:
                updatePrincipal(args, schoolService);
                break;
            case ADD_BOARD:
                addBoard(args, schoolService);
                break;
            case REMOVE_PRINCIPAL:
                removePrincipal(args, schoolService);
                break;
        }
    }

    private static void removePrincipal(String[] args, SchoolService schoolService) {
        if(args.length != 2) {
            printHelpMessage();
            return;
        }
        schoolService.removePrincipal(Integer.parseInt(args[1]));
    }

    private static void updatePrincipal(String[] args, SchoolService schoolService) {
        if(args.length != 3) {
            printHelpMessage();
            return;
        }
        try {
            schoolService.updatePrincipalOfSchool(Integer.parseInt(args[2]), Integer.parseInt(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addBoard(String[] args, SchoolService schoolService) {
        if(args.length != 2) {
            printHelpMessage();
            return;
        }
        Board board = new Board();
        board.setName(args[1]);
        schoolService.insertBoard(board);
    }

    private static void addPrincipalToSchool(String[] args, SchoolService schoolService) {
        if (args.length != 3) {
            printHelpMessage();
            return;
        }
        try {
            schoolService.addPrincipalToSchool(Integer.parseInt(args[2]), Integer.parseInt(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPrincipal(String[] args, SchoolService schoolService) {
        if (args.length != 6) {
            printHelpMessage();
            return;
        }
        Principal principal = new Principal();
        principal.setFirstName(args[1]);
        principal.setLastName(args[2]);
        principal.setYearsOfExperience(Integer.parseInt(args[3]));
        principal.setAge(Integer.parseInt(args[4]));
        principal.setGender(args[5].charAt(0));

        schoolService.insertPrincipal(principal);
    }


    private static void addStudentToSchool(String[] args, SchoolService schoolService) {
        if (args.length != 3) {
            printHelpMessage();
            return;
        }
        try {
            schoolService.addStudentToSchool(Integer.parseInt(args[2]), Integer.parseInt(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addTeacherToSchool(String[] args, SchoolService schoolService) {
        if (args.length != 3) {
            printHelpMessage();
            return;
        }
        try {
            schoolService.addTeachersToSchool(Arrays.asList(Integer.parseInt(args[1])), Integer.parseInt(args[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addTeacher(String[] args, SchoolService schoolService) {
        if (args.length != 5) {
            printHelpMessage();
            return;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(args[1]);
        teacher.setLastName(args[2]);
        teacher.setAge(Integer.parseInt(args[3]));
        teacher.setGender(args[4].charAt(0));

        schoolService.insertTeacher(teacher);
    }

    private static void addSchool(String[] args, SchoolService schoolService) {
        if (args.length != 3) {
            printHelpMessage();
            return;
        }
        String schoolName = args[1];
        String phoneNumber = args[2];

        School school = new School();
        school.setName(schoolName);
        school.setPhoneNo(phoneNumber);

        schoolService.insertSchool(school);
    }

    private static void showStudent(String[] args, SchoolService schoolService) {
        if (args.length != 2) {
            printHelpMessage();
            return;
        }
        int studentId = Integer.parseInt(args[1]);
        System.out.println(schoolService.getStudentById(studentId));

    }

    private static void removeStudent(String[] args, SchoolService schoolService) {
        if (args.length != 2) {
            printHelpMessage();
            return;
        }
        String name = args[1];
        schoolService.deleteStudent(name);
    }

    private static void addStudent(String[] args, SchoolService schoolService) {
        int roll_no = Integer.parseInt(args[1]);
        int age = Integer.parseInt(args[3]);
        String name = args[2];

        Student student = new Student();
        student.setFirstName(name);
        student.setRollNo(roll_no);
        student.setAge(age);
        student.setGender('M');

        schoolService.insertStudent(student);
    }

    private static void printHelpMessage() {
        System.out.println("Usage: java -jar <show|add|remove> <parameters>");
        System.out.println("\t\t show_student   <id>                       : Show a student");
        System.out.println("\t\t add_student    <rollno> <firstName> <age> : Add a student");
        System.out.println("\t\t remove_student <firstName>                : Remove a student");
        System.out.println("\t\t add_school <ame> <phone_number>           : Add a School");
        System.out.println("\t\t add_teacher <fName> <lName> <age> <gender>: Add a Teacher");
        System.out.println("\t\t add_teacher_to_school <t_id> <school_id>  : Add a Teacher to School");
        System.out.println("\t\t add_student_to_school <s_id> <school_id>  : Add a Student to School");
        System.out.println("\t\t add_principal <fName> <lName> <y_of_exp> <age> <gender>  : Add Principal");
        System.out.println("\t\t add_principal_to_school <p_id> <school_id> : Add Principal to School");
        System.out.println("\t\t add_board <board_name>                     : Add Board");
        System.out.println("\t\t update_principal <p_id> <s_id>             : Update School Principal");
        System.out.println("\t\t remove_principal <p_id>                    : Remove Principal");
    }
}
