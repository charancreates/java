import java.util.Scanner;

public class RowColumnTransposition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("--- ROW COLUMN TRANSPOSTION ALGORITHM---");

        System.out.println("\nEnter the plain text:");
        String input = sc.nextLine();
        String plaintext = removeSpaces(input);
        int[] key = getKeyFromUser(sc);

        String ciphertext = encrypt(plaintext, key);
        System.out.println("\nCiphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("\nDecrypted text: " + decrypted);
        System.out.println("\n");
    }

    static String removeSpaces(String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ') {
                result += c;
            }
        }
        return result;
    }

    static int[] getKeyFromUser(Scanner sc) {
        int[] key = new int[100];
        int count, valid, i, j, num;
        while (1 == 1) {
            System.out.println("\nEnter the length of the key:");
            count = sc.nextInt();

            System.out.println("Enter the key digits one by one (total " + count + " digits):");
            for (i = 0; i < count; i++) {
                key[i] = sc.nextInt();
            }

            valid = 1;
            int[] freq = new int[count + 1];
            for (i = 0; i < count; i++) {
                if (key[i] < 1 || key[i] > count) {
                    valid = 0;
                    break;
                }
                freq[key[i]] = freq[key[i]] + 1;
            }
            for (i = 1; i <= count; i++) {
                if (freq[i] != 1) {
                    valid = 0;
                    break;
                }
            }

            if (valid == 1)
                break;
            System.out.println("Invalid key. Use unique integers from 1 to " + count + ". Try again.");
        }

        int[] finalKey = new int[count];
        for (i = 0; i < count; i++) {
            finalKey[i] = key[i];
        }
        return finalKey;
    }

    static String encrypt(String plaintext, int[] key) {
        int cols = key.length;
        int len = plaintext.length();
        int rows = len / cols;
        if (len % cols != 0)
            rows++;

        char[][] grid = new char[rows][cols];
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (idx < len) {
                    grid[i][j] = plaintext.charAt(idx++);
                } else {
                    grid[i][j] = 'X';
                }
            }
        }

        System.out.println("Encryption Grid:");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        String ciphertext = "";
        for (int k = 1; k <= cols; k++) {
            for (int j = 0; j < cols; j++) {
                if (key[j] == k) {
                    for (int i = 0; i < rows; i++) {
                        ciphertext += grid[i][j];
                    }
                    break;
                }
            }
        }
        return ciphertext;
    }

    static String decrypt(String ciphertext, int[] key) {
        int cols = key.length;
        int len = ciphertext.length();
        int rows = len / cols;

        char[][] grid = new char[rows][cols];
        int idx = 0;
        for (int k = 1; k <= cols; k++) {
            for (int j = 0; j < cols; j++) {
                if (key[j] == k) {
                    for (int i = 0; i < rows; i++) {
                        grid[i][j] = ciphertext.charAt(idx++);
                    }
                    break;
                }
            }
        }

        String decrypted = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                decrypted += grid[i][j];
            }
        }
        return decrypted;
    }
}