
import java.util.Scanner;

public class HospitalTriage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        int count = 0;
        int criticalCount = 0;

        do {
            System.out.println("\n--- HOSPITAL TRIAGE SYSTEM ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Show Critical Patient Count");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter patient age: ");
                    int age = sc.nextInt();

                    System.out.print("Enter severity (1-5): ");
                    int severity = sc.nextInt();

                    if (age < 0 || severity < 1 || severity > 5) {
                        System.out.println("Invalid patient details.");
                        continue;
                    } else if (severity == 1) {
                        System.out.println("Patient: " + name);
                        System.out.println("Priority: Critical");
                        System.out.println("Action: Immediate assessment");
                        criticalCount++;
                    } else if (severity == 2) {
                        System.out.println("Patient: " + name);
                        System.out.println("Priority: High");
                    } else if (severity == 3) {
                        System.out.println("Patient: " + name);
                        System.out.println("Priority: Medium");
                    } else {
                        System.out.println("Patient: " + name);
                        System.out.println("Priority: Low");
                    }

                    count++;
                    break;

                case 2:
                    System.out.println("Critical patients: " + criticalCount);
                    System.out.println("Valid registrations: " + count);
                    break;

                case 3:
                    System.out.println("Exiting Hospital Triage System.");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}
