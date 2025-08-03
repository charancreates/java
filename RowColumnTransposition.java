import java.util.*;

public class RowColumnTransposition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Program to implement Row Column Transposition \n");

        System.out.print("Enter the plain text: ");
        String plaintext = sc.nextLine().replaceAll(" ", "");

        int[] key = getKey(sc);

        String ciphertext = encrypt(plaintext, key);
        System.out.println("\nCiphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("\nDecrypted text: " + decrypted.replaceAll("X", ""));
    }

    static int[] getKey(Scanner sc) {
        int count;
        int[] key;
        while (true) {
            System.out.print("\nEnter length of the key: ");
            count = sc.nextInt();
            key = new int[count];
            System.out.println("Enter key digits (unique integers from 1 to " + count + "):");
            Set<Integer> seen = new HashSet<>();
            boolean valid = true;
            for (int i = 0; i < count; i++) {
                key[i] = sc.nextInt();
                if (key[i] < 1 || key[i] > count || !seen.add(key[i])) {
                    valid = false;
                }
            }
            if (valid)
                break;
            System.out.println("Invalid key. Try again.");
        }
        return key;
    }

    static String encrypt(String plaintext, int[] key) {
        int cols = key.length;
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] grid = new char[rows][cols];
        int idx = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (idx < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(idx++);
                } else {
                    grid[i][j] = 'X';
                }

        // Print encryption grid
        System.out.println("\nEncryption Grid:");
        System.out.println("Key Order: " + Arrays.toString(key));
        for (char[] row : grid) {
            for (char ch : row)
                System.out.print(ch + " ");
            System.out.println();
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int k = 1; k <= cols; k++)
            for (int j = 0; j < cols; j++)
                if (key[j] == k)
                    for (int i = 0; i < rows; i++)
                        ciphertext.append(grid[i][j]);
        return ciphertext.toString();
    }

    static String decrypt(String ciphertext, int[] key) {
        int cols = key.length;
        int rows = ciphertext.length() / cols;
        char[][] grid = new char[rows][cols];
        int idx = 0;
        for (int k = 1; k <= cols; k++)
            for (int j = 0; j < cols; j++)
                if (key[j] == k)
                    for (int i = 0; i < rows; i++)
                        grid[i][j] = ciphertext.charAt(idx++);

        // Print decryption grid
        System.out.println("\nDecryption Grid:");
        System.out.println("Key Order: " + Arrays.toString(key));
        for (char[] row : grid) {
            for (char ch : row)
                System.out.print(ch + " ");
            System.out.println();
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                decrypted.append(grid[i][j]);
        return decrypted.toString();
    }
}
