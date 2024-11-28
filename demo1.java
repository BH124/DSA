/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author My PC
 */
  package asm1demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String studentName;
    private double studentScore;

    public Student(String studentId, String studentName, double studentScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentScore = studentScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getStudentScore() {
        return studentScore;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentScore(double studentScore) {
        this.studentScore = studentScore;
    }

    public String getRank() {
        if (studentScore < 5.0) return "Fail";
        else if (studentScore < 6.5) return "Medium";
        else if (studentScore < 7.5) return "Good";
        else if (studentScore < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + studentName + ", Score: " + studentScore + ", Rank: " + getRank();
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String id, String name, double score) {
        students.add(new Student(id, name, score));
        students.sort((s1, s2) -> s1.getStudentId().compareTo(s2.getStudentId())); // Keep list sorted by ID
    for (Student student : students) {
        if (student.getStudentId().equals(id)) {
            System.out.println("Error: Student with this ID already exists.");
            return;
        }
    }
    students.add(new Student(id, name, score));
    System.out.println("Student added successfully.");    
    }

    public void editStudent(String id, String newName, double newScore) {
        int index = binarySearch(id, 0, students.size() - 1);
        if (index != -1) {
            students.get(index).setStudentName(newName);
            students.get(index).setStudentScore(newScore);
            System.out.println("Student updated.");
        } else {
            System.out.println("No student found.");
        }
    }
    public void deleteStudent(String id) {
        int index = binarySearch(id, 0, students.size() - 1);
        if (index != -1) {
            students.remove(index);
            System.out.println("Student has been deleted.");
        } else {
            System.out.println("No student found.");
        }
    }

    public void sortStudentsByScore(boolean descending) {
        students.sort((s1, s2) -> {
            int scoreComparison = Double.compare(s2.getStudentScore(), s1.getStudentScore());
            return descending ? scoreComparison : -scoreComparison;
        });
        System.out.println("List of students sorted by score.");
    }

    public void searchStudentById(String id) {
        int index = binarySearch(id, 0, students.size() - 1);
        if (index != -1) {
            System.out.println(students.get(index));
        } else {
            System.out.println("No student found.");
        }
    }
    private int binarySearch(String id, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = students.get(mid).getStudentId().compareTo(id);

            if (comparison == 0) return mid;
            if (comparison < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // ID not found
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display. The list is empty.");
            return;
        }
        students.forEach(System.out::println);
    }
}

public class demo1 {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Score");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Display Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                System.out.print("Enter Student ID: ");
                String id = scanner.next();
                System.out.print("Enter Student Name: ");
                scanner.nextLine(); // Clear the buffer
                String name = scanner.nextLine();
                double score = validateScoreInput(scanner); // Validate the score input
                try {
                    // Example: Adding a student
                    sms.addStudent(id, name, score);
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
                break;

            case 2:
                System.out.print("Enter Student ID to Edit: ");
                id = scanner.next();
                System.out.print("Enter New Student Name: ");
                scanner.nextLine(); // Clear the buffer
                name = scanner.nextLine();
                score = validateScoreInput(scanner); // Validate the score input
                sms.editStudent(id, name, score);
                break;

                case 3:
                    System.out.print("Enter Student ID to Delete: ");
                    id = scanner.next();
                    sms.deleteStudent(id);
                    break;

                case 4:
                    // Sort students by score in descending order
                    sms.sortStudentsByScore(true);
                    System.out.println("Students sorted in descending order by score:");
                    sms.displayStudents(); // Display the sorted list
                    break;
                    

                case 5:
                    System.out.print("Enter Student ID to Search: ");
                    id = scanner.next();
                    sms.searchStudentById(id);
                    break;

                case 6:
                    sms.displayStudents();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 7);

        scanner.close();
    }
    private static String validateIdInput(Scanner scanner) {
        String id;
        while (true) {
            System.out.print("Enter Student ID: ");
            id = scanner.nextLine().trim();
            if (!id.isEmpty() && id.matches("[a-zA-Z0-9]+")) {
                break;
            } else {
                System.out.println("Invalid ID. Please enter a non-empty alphanumeric ID.");
            }
        }
        return id;
    }
        private static String validateNameInput(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty() && name.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Invalid name. Please enter alphabetic characters only.");
            }
        }
        return name;
    }

    private static double validateScoreInput(Scanner scanner) {
        double score;
        while (true) {
            System.out.print("Enter Student Score (0.0 to 10.0): ");
            if (scanner.hasNextDouble()) {
                score = scanner.nextDouble();
                if (score >= 0.0 && score <= 10.0) {
                    break;
                } else {
                    System.out.println("Invalid score. Enter a value between 0.0 and 10.0.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Clear invalid input
            }
        }
        return score;
    }
}
