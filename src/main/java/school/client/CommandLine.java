package school.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school.model.Student;
import school.service.SchoolService;

import java.util.Arrays;
import java.util.List;

public class CommandLine {
    static final String ADD_STUDENT = "add";
    static final String SHOW_STUDENT = "show";
    static final String REMOVE_STUDENT = "remove";
    static final List<String> commands = Arrays.asList(SHOW_STUDENT, ADD_STUDENT);

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
        }
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
        String rollno = args[1];
        // remove student
    }

    private static void addStudent(String[] args, SchoolService schoolService) {
        int roll_no = Integer.parseInt(args[1]);
        int age = Integer.parseInt(args[3]);
        String name = args[2];

        Student student = new Student();
        student.setFirstName(name);
        student.setRollNo(roll_no);
        student.setAge(age);

        schoolService.insertStudent(student);
    }

    private static void printHelpMessage() {
        System.out.println("Usage: java -jar <show|add|remove> <parameters>");
        System.out.println("\t\t show   <id>                       : Show a student");
        System.out.println("\t\t add    <rollno> <firstName> <age> : Add a student");
        System.out.println("\t\t remove <id>                       : Remove a student");
    }
}
