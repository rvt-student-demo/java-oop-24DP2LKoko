package studentu_registracija;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "studenti.csv";

    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                // Izlaist virsrakstu rindu
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                Student s = Student.fromCsvLine(line);
                if (s != null) students.add(s);
            }

        } catch (IOException e) {
            System.out.println("Kļūda lasot failu: " + e.getMessage());
        }

        return students;
    }

    public static void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, false))) {
            writer.println("Vards,Uzvards,Epasts,PersonasKods,RegistracijasDatums");
            for (Student s : students) {
                writer.println(s.toCsvLine());
            }
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot failu: " + e.getMessage());
        }
    }
}
