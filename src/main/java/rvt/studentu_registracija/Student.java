package studentu_registracija;

public class Student {

    public String vards;
    public String uzvards;
    public String epasts;
    public String personasKods;
    public String registracijasDatums;

    public Student(String vards, String uzvards, String epasts, String personasKods, String registracijasDatums) {
        this.vards = vards;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
        this.registracijasDatums = registracijasDatums;
    }

    // Pārveido studenta datus par vienu CSV rindu
    public String toCsvLine() {
        return vards + "," + uzvards + "," + epasts + "," + personasKods + "," + registracijasDatums;
    }

    // Nolasa vienu CSV rindu un atgriež Student objektu
    public static Student fromCsvLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return null;
        return new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}
