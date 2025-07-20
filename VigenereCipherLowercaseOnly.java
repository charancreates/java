import java.util.Scanner;

public class VigenereCipherLowercaseOnly {

    static char[] alphabetLower = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    static int getCharIndex(char ch) {
        return ch - 'a';
    }

    static char getCharFromIndex(int index) {
        return (char) ('a' + index);
    }

    static String generateFullKey(String text, String key) {
        StringBuilder fullKey = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char k = key.charAt(keyIndex % key.length());
            fullKey.append(k);
            keyIndex++;
        }
        return fullKey.toString();
    }

    static String encrypt(String plaintext, String key) {
        String fullKey = generateFullKey(plaintext, key);
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            int pi = getCharIndex(plaintext.charAt(i));
            int ki = getCharIndex(fullKey.charAt(i));
            int ei = (pi + ki) % 26;
            ciphertext.append(getCharFromIndex(ei));
        }
        return ciphertext.toString();
    }

    static String decrypt(String ciphertext, String key) {
        String fullKey = generateFullKey(ciphertext, key);
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            int ei = getCharIndex(ciphertext.charAt(i));
            int ki = getCharIndex(fullKey.charAt(i));
            int pi = (ei - ki + 26) % 26;
            plaintext.append(getCharFromIndex(pi));
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Program to demonstrate Vigenere Cipher ");

        System.out.println("Enter plain text: ");
        String plaintext = sc.nextLine();

        System.out.println("Enter key: ");
        String key = sc.nextLine();

        // Print Vigenere Matrix
        System.out.println("\n\nVigenere Matrix (a-z):");
        System.out.print("  ");
        for (int i = 0; i < 26; i++) {
            System.out.print(" " + (char) ('a' + i));
        }
        System.out.println();
        for (int row = 0; row < 26; row++) {
            System.out.print((char) ('a' + row) + "  ");
            for (int col = 0; col < 26; col++) {
                System.out.print((char) (((row + col) % 26) + 'a') + " ");
            }
            System.out.println();
        }

        String ciphertext = encrypt(plaintext, key);
        System.out.println("\nEncrypted text: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decrypted);
    }
}
