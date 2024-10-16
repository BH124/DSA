

import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;

class Student {
    private int id;
    private String name;
    private double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
// Method to get the student's ranking based on their score
    public String getRanking() {
        if (score < 5.0) {
            return "Fail";
        } else if (score < 6.5) {
            return "Average";
        } else if (score < 7.5) {
            return "Good";
        } else if (score < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }
// Method to get the student's ID
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

class StudentManagerSystem {
    private LinkedList<Student> students;

    public StudentManagerSystem() {
        students = new LinkedList<>();
    }

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                System.out.println("Student with the same ID already exists.");
                return;
            }
        }
        students.add(student);
    }

    public void editStudent(int id, String name, double score) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setScore(score);
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void deleteStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void sortStudents() {
        students.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
    }

    public Student searchStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Score: " + student.getScore() + ", Ranking: " + student.getRanking());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagerSystem manager = new StudentManagerSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Sort students");
            System.out.println("5. Search student");
            System.out.println("6. Display students");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1://addstudent
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student score: ");
                    double score = scanner.nextDouble();
                    Student student = new Student(id, name, score);
                    manager.addStudent(student);
                    break;
                case 2:// Edit student information
                    System.out.print("Enter student ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline
                    System.out.print("Enter student name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter student score: ");
                    score = scanner.nextDouble();
                    manager.editStudent(id, name, score);
                    break;
                case 3://deletestudent
                    System.out.print("Enter student ID: ");
                    id = scanner.nextInt();
                    manager.deleteStudent(id);
                    break;
                case 4:// Sort students by score
                    manager.sortStudents();
                    System.out.println("Students sorted by score.");
                    break;
                case 5:// Search student by ID
                    System.out.print("Enter student ID: ");
                    try {
                        id = scanner.nextInt();
                        Student foundStudent = manager.searchStudent(id);
                        if (foundStudent != null) {
                            System.out.println("ID: " + foundStudent.getId() + ", Name: " + foundStudent.getName() + ", Score: " + foundStudent.getScore() + ", Ranking: " + foundStudent.getRanking());
                        } else {
                            System.out.println("Student not found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid student ID.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;
                case 6:// Display
                    manager.displayStudents();
                    break;
                case 7:// Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:// Handle the case where the user enters an invalid choice
                    System.out.println("Invalid choice. Please select a number from 1 to 7.");
            }
        }
    }
}
