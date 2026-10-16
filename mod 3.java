
import java.util.Scanner;

public class HospitalTriage {

    // Method to display patient details
    static void displayPatient(String name, int age, int severity) {
        System.out.println("\nPatient Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Severity: " + severity);
        System.out.println("Priority: " + getPriority(severity));
    }

    // Method to return priority
    static String getPriority(int severity) {
        if (severity == 1)
            return "Critical";
        else if (severity == 2)
            return "High";
        else if (severity == 3)
            return "Medium";
        else
            return "Low";
    }

    // Recursive method to calculate factorial
    static int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    // Method to count critical patients
    static int countCritical(int[] severity, int n) {
        if (n == severity.length)
            return 0;

        int count = (severity[n] == 1) ? 1 : 0;
        return count + countCritical(severity, n + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of patients (1-5): ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n < 1 || n > 5) {
            System.out.println("Invalid number of patients.");
            sc.close();
            return;
        }

        String[] names = new String[n];
        int[] ages = new int[n];
        int[] severity = new int[n];

        // Two-dimensional array: patient age and severity
        int[][] details = new int[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details of patient " + (i + 1));

            System.out.print("Name: ");
            names[i] = sc.nextLine();

            System.out.print("Age: ");
            ages[i] = sc.nextInt();

            System.out.print("Severity (1-5): ");
            severity[i] = sc.nextInt();
            sc.nextLine();

            if (ages[i] < 0 || severity[i] < 1 || severity[i] > 5) {
                System.out.println("Invalid details. Please restart.");
                sc.close();
                return;
            }

            details[i][0] = ages[i];
            details[i][1] = severity[i];
        }

        System.out.println("\n--- PATIENT REPORT ---");

        for (int i = 0; i < n; i++) {
            displayPatient(names[i], ages[i], severity[i]);
        }

        System.out.println("\nCritical patients: "
                + countCritical(severity, 0));

        System.out.println("Factorial of patient count: "
                + factorial(n));

        sc.close();
    }
}
