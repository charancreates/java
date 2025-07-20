import java.util.Scanner;

public class RailFenceCipher {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Program to demonstrate Rail Fence Cipher");

        System.out.println("Enter the plain text:");
        String plaintext = sc.nextLine();

        System.out.println("Enter the depth (number of rails):");
        int depth = sc.nextInt();

        char[] temp = new char[plaintext.length()];
        int count = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (ch != ' ') {
                temp[count] = ch;
                count++;
            }
        }

        char[] text = new char[count];
        for (int i = 0; i < count; i++) {
            text[i] = temp[i];
        }

        char[][] rails = new char[depth][text.length];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                rails[i][j] = '\n';
            }
        }

        int row = 0;
        int direction = +1;

        for (int i = 0; i < text.length; i++) {
            rails[row][i] = text[i];

            row = row + direction;

            if (row == depth) {
                row = depth - 2;
                direction = -1;
            } else if (row < 0) {
                row = 1;
                direction = +1;
            }
        }

        System.out.print("Ciphertext: ");
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                if (rails[i][j] != '\n') {
                    System.out.print(rails[i][j]);
                }
            }
        }

        System.out.println();

        char[] cipher = new char[text.length];
        int index = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                if (rails[i][j] != '\n') {
                    cipher[index] = rails[i][j];
                    index++;
                }
            }
        }

        char[][] decryptRails = new char[depth][text.length];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                decryptRails[i][j] = '\n';
            }
        }

        row = 0;
        direction = +1;
        for (int i = 0; i < text.length; i++) {
            decryptRails[row][i] = '*';

            row = row + direction;

            if (row == depth) {
                row = depth - 2;
                direction = -1;
            } else if (row < 0) {
                row = 1;
                direction = +1;
            }
        }

        index = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                if (decryptRails[i][j] == '*' && index < cipher.length) {
                    decryptRails[i][j] = cipher[index];
                    index++;
                }
            }
        }

        char[] decrypted = new char[text.length];
        row = 0;
        direction = +1;
        for (int i = 0; i < text.length; i++) {
            decrypted[i] = decryptRails[row][i];

            row = row + direction;

            if (row == depth) {
                row = depth - 2;
                direction = -1;
            } else if (row < 0) {
                row = 1;
                direction = +1;
            }
        }

        System.out.println();

        System.out.println("Rail Fence Pattern:");
        System.out.println();
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length; j++) {
                if (rails[i][j] != '\n') {
                    System.out.print(rails[i][j] + "  ");
                } else {
                    System.out.print("   "); // Two spaces for alignment
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < decrypted.length; i++) {
            System.out.print(decrypted[i]);
        }

        sc.close();
    }
}
