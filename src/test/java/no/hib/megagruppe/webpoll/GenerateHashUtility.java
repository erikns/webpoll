package no.hib.megagruppe.webpoll;

import no.hib.megagruppe.webpoll.util.PasswordHasher;
import no.hib.megagruppe.webpoll.util.SecurePasswordHasher;

import java.util.Scanner;

/**
 * Small utility to generate hash values. Used for development purposes.
 */
public class GenerateHashUtility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Reenter password: ");
        String passwordRepeat = scanner.nextLine();

        if (password.equals(passwordRepeat)) {
            PasswordHasher hasher = new SecurePasswordHasher();
            String hash = hasher.hashPassword(password);
            System.out.println("Hash: " + hash);
        } else {
            System.out.println("Error: password mismatch");
            System.exit(1);
        }

        System.exit(0);
    }
}
