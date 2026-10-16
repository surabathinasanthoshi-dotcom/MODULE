
class Patient {
    private String name;
    private int age;
    private int severity;

    // Parameterized constructor
    Patient(String name, int age, int severity) {
        this.name = name;
        setAge(age);
        setSeverity(severity);
    }

    // Setters with validation
    public void setAge(int age) {
        if (age < 0)
            throw new IllegalArgumentException("Age cannot be negative");
        this.age = age;
    }

    public void setSeverity(int severity) {
        if (severity < 1 || severity > 5)
            throw new IllegalArgumentException("Severity must be 1-5");
        this.severity = severity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSeverity() {
        return severity;
    }

    public String getPriority() {
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
        return "Name: " + name +
               "\nAge: " + age +
               "\nPriority: " + getPriority();
    }
}

// Inheritance
class EmergencyPatient extends Patient {

    EmergencyPatient(String name, int age, int severity) {
        super(name, age, severity);
    }

    // Method overriding
    @Override
    public String getPriority() {
        if (getSeverity() == 1)
            return "Critical - Immediate assessment";
        return super.getPriority();
    }
}

public class HospitalTriage {
    public static void main(String[] args) {
        try {
            // Creating an object
            EmergencyPatient p1 =
                new EmergencyPatient("Ravi", 25, 1);

            System.out.println("--- HOSPITAL TRIAGE ---");
            System.out.println(p1);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Triage process completed.");
        }
    }
}
