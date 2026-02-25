package studentu_registracija;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration(scanner);

        System.out.println("=== Studentu reģistrācijas sistēma ===");

        while (true) {
            System.out.println("\nPieejamās komandas: register | show | remove | edit | exit");
            System.out.print("Ievadiet komandu: ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("register")) {
                registration.register();

            } else if (command.equals("show")) {
                registration.show();

            } else if (command.equals("remove")) {
                registration.remove();

            } else if (command.equals("edit")) {
                registration.edit();

            } else if (command.equals("exit")) {
                System.out.println("Programma tiek aizvērta. Uz redzēšanos!");
                scanner.close();
                return;

            } else {
                System.out.println("Nezināma komanda. Lūdzu mēģiniet vēlreiz.");
            }
        }
    }
}
