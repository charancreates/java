import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class sha {

    // Method to encrypt input string using SHA-512
    public static String encryptThisString(String input) {
        try {
            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Calculate message digest of input string
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Pad with leading zeros to ensure 128-character length
            while (hashtext.length() < 128) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create Scanner object

        System.out.println("Program to calculate SHA-512 hash value");

        System.out.print("Enter a string to hash with SHA-512: ");
        String userInput = sc.nextLine(); // Read user input

        String hashed = encryptThisString(userInput); // Get SHA-512 hash

        System.out.println("\nSHA-512 Hash: " + hashed);

        sc.close(); // Close scanner
    }
}
