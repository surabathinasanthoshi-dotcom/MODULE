
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class HospitalTriage {

    static ArrayList<String> patients = new ArrayList<>();
    static final String FILE_NAME = "patients.txt";

    // Add patient details
    static void addPatient(Scanner sc) {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter patient age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter severity (1-5): ");
        int severity = Integer.parseInt(sc.nextLine());

        if (name.isEmpty() || age < 0 ||
                severity < 1 || severity > 5) {
            System.out.println("Invalid patient details.");
            return;
        }

        String priority;
        if (severity == 1)
            priority = "Critical";
        else if (severity == 2)
            priority = "High";
        else if (severity == 3)
            priority = "Medium";
        else
            priority = "Low";

        String record = name + "," + age + ","
                + severity + "," + priority;

        patients.add(record);
        System.out.println("Patient added successfully.");
    }

    // Save records to a file
    static void savePatients() {
        try {
            Files.write(
                Path.of(FILE_NAME),
                patients
            );
            System.out.println("Patient records saved.");
        } catch (IOException e) {
            System.out.println("Error saving file: "
                    + e.getMessage());
        }
    }

    // Load records from a file
    static void loadPatients() {
        patients.clear();

        try {
            if (Files.exists(Path.of(FILE_NAME))) {
                List<String> lines =
                    Files.readAllLines(Path.of(FILE_NAME));

                for (String line : lines) {
                    String[] data = line.split(",", -1);

                    if (data.length == 4) {
                        try {
                            int age = Integer.parseInt(data[1]);
                            int severity = Integer.parseInt(data[2]);

                            if (!data[0].trim().isEmpty()
                                    && age >= 0
                                    && severity >= 1
                                    && severity <= 5) {
                                patients.add(line);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Skipping invalid record.");
                        }
                    } else {
                        System.out.println(
                            "Skipping malformed record.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: "
                    + e.getMessage());
        }
    }

    // Display patient records
    static void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patient records found.");
            return;
        }

        for (String patient : patients) {
            String[] data = patient.split(",", -1);

            System.out.println("Name: " + data[0]
                    + ", Age: " + data[1]
                    + ", Severity: " + data[2]
                    + ", Priority: " + data[3]);
        }
    }

    // Search by patient name
    static void searchPatient(Scanner sc) {
        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine().trim();

        boolean found = false;

        for (String patient : patients) {
            String[] data = patient.split(",", -1);

            if (data[0].equalsIgnoreCase(searchName)) {
                System.out.println(patient);
                found = true;
            }
        }

        if (!found)
            System.out.println("Patient not found.");
    }

    // Sort records alphabetically by name
    static void sortPatients() {
        patients.sort((a, b) -> {
            String name1 = a.split(",", -1)[0];
            String name2 = b.split(",", -1)[0];
            return name1.compareToIgnoreCase(name2);
        });

        System.out.println("Records sorted by name.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadPatients();

        int choice;

        do {
            System.out.println("\n--- HOSPITAL TRIAGE SYSTEM ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Sort Patients");
            System.out.println("5. Save Records");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            addPatient(sc);
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Age and severity must be numbers.");
                        }
                        break;
                    case 2:
                        displayPatients();
                        break;
                    case 3:
                        searchPatient(sc);
                        break;
                    case 4:
                        sortPatients();
                        break;
                    case 5:
                        savePatients();
                        break;
                    case 6:
                        savePatients();
                        System.out.println("Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Enter a valid menu number.");
            }

        } while (choice != 6);

        sc.close();
    }
}
