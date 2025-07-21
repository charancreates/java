import java.util.*;

public class RowColumnTransposition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- ROW COLUMN TRANSPOSITION ALGORITHM ---\n");

        System.out.print("Enter the plain text: ");
        String input = sc.nextLine();
        String plaintext = input.replaceAll(" ", "");

        int[] key = getKeyFromUser(sc);

        String ciphertext = encrypt(plaintext, key);
        System.out.println("\nCiphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        decrypted = decrypted.replaceAll("X", ""); // Remove padding 'X'
        System.out.println("Decrypted text (without X padding): " + decrypted);
        System.out.println();
    }

    static int[] getKeyFromUser(Scanner sc) {
        while (true) {
            System.out.print("\nEnter the length of the key: ");
            int count = sc.nextInt();

            int[] key = new int[count];
            Set<Integer> seen = new HashSet<>();
            boolean valid = true;

            System.out.println("Enter the key digits one by one (1 to " + count + "):");

            for (int i = 0; i < count; i++) {
                int num = sc.nextInt();

                if (num < 1 || num > count || seen.contains(num)) {
                    valid = false;
                } else {
                    seen.add(num);
                    key[i] = num;
                }
            }

            if (valid) {
                return key;
            }

            System.out.println("Invalid key. Use unique integers from 1 to " + count + ". Try again.");
        }
    }

    static String encrypt(String plaintext, int[] key) {
        int cols = key.length;
        int len = plaintext.length();
        int rows = len / cols;
        if (len % cols != 0) {
            rows++;
        }

        char[][] grid = new char[rows][cols];
        int idx = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (idx < len) {
                    grid[i][j] = plaintext.charAt(idx);
                    idx++;
                } else {
                    grid[i][j] = 'X'; // padding
                }
            }
        }

        System.out.println("\nEncryption Grid:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int k = 1; k <= cols; k++) {
            for (int j = 0; j < cols; j++) {
                if (key[j] == k) {
                    for (int i = 0; i < rows; i++) {
                        ciphertext.append(grid[i][j]);
                    }
                    break;
                }
            }
        }

        return ciphertext.toString();
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
                        grid[i][j] = ciphertext.charAt(idx);
                        idx++;
                    }
                    break;
                }
            }
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                decrypted.append(grid[i][j]);
            }
        }

        return decrypted.toString();
    }
}
