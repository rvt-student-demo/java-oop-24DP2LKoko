import java.util.Scanner;

public class Conneticut100 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ievadiet skaitītāju: ");
            String numInput = scanner.nextLine();

            if (numInput.charAt(0) == 'q' || numInput.charAt(0) == 'Q') {
                break;
            }

            int numerator;
            try {
                numerator = Integer.parseInt(numInput);
            } catch (NumberFormatException e) {
                System.out.println("Jūs ievadījāt nepareizus datus.");
                System.out.println("Lūdzu, mēģiniet vēlreiz.");
                continue;
            }

            System.out.print("Ievadiet dalītāju: ");
            int divisor;
            try {
                divisor = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Jūs ievadījāt nepareizus datus.");
                System.out.println("Lūdzu, mēģiniet vēlreiz.");
                continue;
            }

            if (divisor == 0) {
                System.out.println("Jūs nevarat sadalīt " + numerator + " no 0");
            } else {
                System.out.println(numerator + " / " + divisor + " ir " + (numerator / divisor));
            }

            System.out.println();
        }

        scanner.close();
    }
}