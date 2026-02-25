package studentu_registracija;

import java.util.List;

public class Display {

    private static final int COL_WIDTH = 22;

    private static void printSeparator() {
        String line = ("+" + "-".repeat(COL_WIDTH)).repeat(5) + "+";
        System.out.println(line);
    }

    // Saīsina vai papildina tekstu, lai tas ietilptu kolonnā
    private static String formatCell(String text) {
        if (text.length() > COL_WIDTH) {
            text = text.substring(0, COL_WIDTH - 1);
        }
        return String.format("%-" + COL_WIDTH + "s", text);
    }

    public static void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Nav reģistrētu studentu.");
            return;
        }

        printSeparator();
        System.out.printf("|%s|%s|%s|%s|%s|%n",
                formatCell("Vards"),
                formatCell("Uzvards"),
                formatCell("Epasts"),
                formatCell("Personas kods"),
                formatCell("Registracijas datums"));
        printSeparator();

        for (Student s : students) {
            System.out.printf("|%s|%s|%s|%s|%s|%n",
                    formatCell(s.vards),
                    formatCell(s.uzvards),
                    formatCell(s.epasts),
                    formatCell(s.personasKods),
                    formatCell(s.registracijasDatums));
        }

        printSeparator();
    }
}
