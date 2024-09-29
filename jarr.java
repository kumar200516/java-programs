import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    // Constructor
    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

public class jarr {
    private List<Student> students;
    private Scanner scanner;

    // Constructor
    public jarr() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Add a student
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required. Please try again.");
            return;
        }

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    // Remove a student
    public void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        String rollNumber = scanner.nextLine();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber().equals(rollNumber)) {
                students.remove(i);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Search for a student
    public void searchStudent() {
        System.out.print("Enter roll number of the student to search: ");
        String rollNumber = scanner.nextLine();

        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Save students to a file
    public void saveStudentsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
            System.out.println("Students saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving students to file.");
        }
    }

    // Load students from a file
    public void loadStudentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Students loaded from file.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading students from file.");
        }
    }

    // Main method to run the system
    public static void main(String[] args) {
        jarr sms = new jarr();
        int choice;

        // Load students from file at the start
        sms.loadStudentsFromFile("students.txt");

        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Students to File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sms.scanner.nextInt();
            sms.scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    sms.addStudent();
                    break;
                case 2:
                    sms.removeStudent();
                    break;
                case 3:
                    sms.searchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.saveStudentsToFile("students.txt");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        sms.scanner.close();
    }
}
