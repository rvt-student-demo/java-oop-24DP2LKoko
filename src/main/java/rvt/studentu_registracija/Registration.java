package studentu_registracija;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Registration {

    private List<Student> students;
    private Scanner scanner;

    public Registration(Scanner scanner) {
        this.scanner = scanner;
        this.students = FileHandler.readStudents();
    }

    public void register() {
        System.out.println("\n--- Jauna studenta reģistrācija ---");

        String vards = "";
        while (!Validator.isValidName(vards)) {
            System.out.print("Vārds (min. 3 burti): ");
            vards = scanner.nextLine().trim();
            if (!Validator.isValidName(vards)) {
                System.out.println("Kļūda: Vārds var saturēt tikai burtus, minimums 3 simboli.");
            }
        }

        String uzvards = "";
        while (!Validator.isValidName(uzvards)) {
            System.out.print("Uzvārds (min. 3 burti): ");
            uzvards = scanner.nextLine().trim();
            if (!Validator.isValidName(uzvards)) {
                System.out.println("Kļūda: Uzvārds var saturēt tikai burtus, minimums 3 simboli.");
            }
        }

        String epasts = "";
        while (!Validator.isValidEmail(epasts)) {
            System.out.print("E-pasts: ");
            epasts = scanner.nextLine().trim();
            if (!Validator.isValidEmail(epasts)) {
                System.out.println("Kļūda: Nepareizs e-pasta formāts.");
            }
        }

        if (emailTaken(epasts)) {
            System.out.println("Kļūda: Šis e-pasts jau ir reģistrēts!");
            return;
        }

        String personasKods = "";
        while (!Validator.isValidPersonasKods(personasKods)) {
            System.out.print("Personas kods (piemērs: 123456-12345): ");
            personasKods = scanner.nextLine().trim();
            if (!Validator.isValidPersonasKods(personasKods)) {
                System.out.println("Kļūda: Personas kodam jābūt formātā XXXXXX-XXXXX.");
            }
        }

        if (personasKodsExists(personasKods)) {
            System.out.println("Kļūda: Šis personas kods jau ir reģistrēts!");
            return;
        }

        // Reģistrācijas datums tiek ņemts automātiski
        String datums = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        Student newStudent = new Student(vards, uzvards, epasts, personasKods, datums);
        students.add(newStudent);
        FileHandler.saveStudents(students);

        System.out.println("Students veiksmīgi reģistrēts!");
    }

    public void show() {
        System.out.println("\n--- Visi studenti ---");
        Display.printStudents(students);
    }

    public void remove() {
        System.out.print("\nIevadiet dzēšamā studenta personas kodu: ");
        String kods = scanner.nextLine().trim();

        Student found = findByPersonasKods(kods);

        if (found == null) {
            System.out.println("Students ar šādu personas kodu netika atrasts.");
            return;
        }

        students.remove(found);
        FileHandler.saveStudents(students);
        System.out.println("Students " + found.vards + " " + found.uzvards + " dzēsts.");
    }

    public void edit() {
        System.out.print("\nIevadiet rediģējamā studenta personas kodu: ");
        String kods = scanner.nextLine().trim();

        Student found = findByPersonasKods(kods);

        if (found == null) {
            System.out.println("Students ar šādu personas kodu netika atrasts.");
            return;
        }

        System.out.println("Rediģē: " + found.vards + " " + found.uzvards);
        System.out.println("(Nospiediet Enter, lai atstātu lauku nemainītu)");

        System.out.print("Jauns vārds [" + found.vards + "]: ");
        String newVards = scanner.nextLine().trim();
        if (!newVards.isEmpty()) {
            if (!Validator.isValidName(newVards)) {
                System.out.println("Kļūda: Nepareizs vārds. Izmaiņas netika saglabātas.");
                return;
            }
            found.vards = newVards;
        }

        System.out.print("Jauns uzvārds [" + found.uzvards + "]: ");
        String newUzvards = scanner.nextLine().trim();
        if (!newUzvards.isEmpty()) {
            if (!Validator.isValidName(newUzvards)) {
                System.out.println("Kļūda: Nepareizs uzvārds. Izmaiņas netika saglabātas.");
                return;
            }
            found.uzvards = newUzvards;
        }

        System.out.print("Jauns e-pasts [" + found.epasts + "]: ");
        String newEpasts = scanner.nextLine().trim();
        if (!newEpasts.isEmpty()) {
            if (!Validator.isValidEmail(newEpasts)) {
                System.out.println("Kļūda: Nepareizs e-pasta formāts. Izmaiņas netika saglabātas.");
                return;
            }
            if (!newEpasts.equals(found.epasts) && emailTaken(newEpasts)) {
                System.out.println("Kļūda: Šis e-pasts jau ir aizņemts. Izmaiņas netika saglabātas.");
                return;
            }
            found.epasts = newEpasts;
        }

        FileHandler.saveStudents(students);
        System.out.println("Dati veiksmīgi atjaunināti!");
    }

    // Pārbauda vai e-pasts jau eksistē sarakstā
    private boolean emailTaken(String email) {
        for (Student s : students) {
            if (s.epasts.equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    // Pārbauda vai personas kods jau eksistē sarakstā
    private boolean personasKodsExists(String kods) {
        for (Student s : students) {
            if (s.personasKods.equals(kods)) return true;
        }
        return false;
    }

    private Student findByPersonasKods(String kods) {
        for (Student s : students) {
            if (s.personasKods.equals(kods)) return s;
        }
        return null;
    }
}
