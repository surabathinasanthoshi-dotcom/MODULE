
import java.util.*;
import java.util.stream.Collectors;

class Patient {
    String name;
    int age;
    int severity;

    Patient(String name, int age, int severity) {
        this.name = name;
        this.age = age;
        this.severity = severity;
    }

    String getPriority() {
        if (severity == 1)
            return "Critical";
        else if (severity == 2)
            return "High";
        else if (severity == 3)
            return "Medium";
        else
            return "Low";
    }

    public String toString() {
        return name + " | Age: " + age
                + " | Priority: " + getPriority();
    }
}

public class HospitalTriage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ArrayList stores patient objects
        List<Patient> patients = new ArrayList<>();

        // HashMap counts patients by priority
        Map<String, Integer> priorityCount = new HashMap<>();

        // HashSet stores unique patient names
        Set<String> patientNames = new HashSet<>();

        System.out.print("Enter number of patients: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n < 1) {
            System.out.println("Invalid number of patients.");
            sc.close();
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nPatient " + (i + 1));

            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter age: ");
            int age = sc.nextInt();

            System.out.print("Enter severity (1-5): ");
            int severity = sc.nextInt();
            sc.nextLine();

            if (name.isEmpty() || age < 0
                    || severity < 1 || severity > 5) {
                System.out.println("Invalid details. Try again.");
                i--;
                continue;
            }

            patients.add(new Patient(name, age, severity));
            patientNames.add(name);

            Patient p = patients.get(patients.size() - 1);
            String priority = p.getPriority();

            priorityCount.put(priority,
                priorityCount.getOrDefault(priority, 0) + 1);
        }

        // Display all patients using forEach
        System.out.println("\n--- ALL PATIENTS ---");
        patients.forEach(System.out::println);

        // Stream: filter critical patients
        System.out.println("\n--- CRITICAL PATIENTS ---");
        patients.stream()
                .filter(p -> p.severity == 1)
                .forEach(System.out::println);

        // Stream: sort patients by severity
        System.out.println("\n--- SORTED BY SEVERITY ---");
        patients.stream()
                .sorted(Comparator.comparingInt(p -> p.severity))
                .forEach(System.out::println);

        // Count critical patients using a stream
        long criticalCount = patients.stream()
                .filter(p -> p.severity == 1)
                .count();

        System.out.println("\nCritical patient count: "
                + criticalCount);

        // Display priority summary
        System.out.println("\n--- PRIORITY SUMMARY ---");
        priorityCount.forEach((priority, count) ->
                System.out.println(priority + ": " + count));

        // Group patients by priority using Collectors
        Map<String, List<Patient>> groupedPatients =
                patients.stream().collect(
                    Collectors.groupingBy(Patient::getPriority));

        System.out.println("\n--- PATIENTS GROUPED BY PRIORITY ---");
        groupedPatients.forEach((priority, list) -> {
            System.out.println(priority + ": " + list.size());
        });

        // Optional: safely find the first critical patient
        Optional<Patient> firstCritical = patients.stream()
                .filter(p -> p.severity == 1)
                .findFirst();

        System.out.println("\n--- FIRST CRITICAL PATIENT ---");
        firstCritical.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No critical patients found.")
        );

        // Count unique patient names
        System.out.println("\nUnique patient names: "
                + patientNames.size());

        sc.close();
    }
}
