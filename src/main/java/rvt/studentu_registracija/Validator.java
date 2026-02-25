package studentu_registracija;

public class Validator {

    // Vārds drīkst saturēt tikai burtus un minimums 3 simboli
    public static boolean isValidName(String name) {
        if (name == null || name.length() < 3) return false;
        return name.matches("[a-zA-ZĀāČčĒēĢģĪīĶķĻļŅņŠšŪūŽž]+");
    }

    // E-pastam jābūt standarta formātā: kaut.kas@kaut.kas
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");
    }

    // Personas kodam jābūt formātā: 123456-12345
    public static boolean isValidPersonasKods(String kods) {
        if (kods == null) return false;
        return kods.matches("\\d{6}-\\d{5}");
    }
}
