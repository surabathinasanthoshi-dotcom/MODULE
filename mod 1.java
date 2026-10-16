
import java.util.Scanner;

public class HospitalTriage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String patientName;
        int age;
        int severity;

        System.out.println("HOSPITAL EMERGENCY ROOM");
        
        System.out.print("Enter patient name: ");
        patientName = sc.nextLine();

        System.out.print("Enter patient age: ");
        age = sc.nextInt();

        System.out.print("Enter severity level (1-5): ");
        severity = sc.nextInt();

        System.out.println("\n--- Patient Details ---");
        System.out.println("Name: " + patientName);
        System.out.println("Age: " + age);

        if (severity == 1) {
            System.out.println("Priority: Critical");
            System.out.println("Action: Immediate treatment");
        } else if (severity == 2) {
            System.out.println("Priority: High");
            System.out.println("Action: Urgent treatment");
        } else if (severity == 3) {
            System.out.println("Priority: Medium");
            System.out.println("Action: Early treatment");
        } else if (severity == 4 || severity == 5) {
            System.out.println("Priority: Low");
            System.out.println("Action: Wait for your turn");
        } else {
            System.out.println("Invalid severity level");
        }

        sc.close();
    }
}
