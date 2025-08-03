import java.util.*;

class diffie {

    private static long power(long a, long b, long p) {
        if (b == 1)
            return a;
        else
            return (((long) Math.pow(a, b)) % p);
    }

    private static long findPrimitiveRoot(long p) {
        long phi = p - 1;

        Set<Long> factors = new HashSet<>();
        for (long i = 2; i <= phi; i++)
            if (phi % i == 0)
                factors.add(i);

        for (long r = 2; r <= phi; r++) {
            boolean ok = true;
            for (long f : factors)
                if (power(r, phi / f, p) == 1)
                    ok = false;
            if (ok)
                return r;
        }
        return -1;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----PROGRAM TO IMPLEMENT DIFFIEE-HELLMAN's KEY EXCHANGE ALGORITHM-----");
        System.out.print("\nEnter a prime number P: ");
        long P = scanner.nextLong();

        long G = findPrimitiveRoot(P);
        System.out.println("Primitive root of " + P + " is: " + G);

        System.out.print("\nEnter the Value of X less than " + G + ": ");
        long a = scanner.nextLong();
        long x = power(G, a, P);
        System.out.println("Secret key ( R1 ) : " + x);

        System.out.print("\nEnter the Value of Y less than " + G + ": ");
        long b = scanner.nextLong();
        long y = power(G, b, P);
        System.out.println("Secret key ( R2 ) : " + y);

        long ka = power(y, a, P);
        long kb = power(x, b, P);

        System.out.println("\nThe Key Calculated at the Sender's Side: " + ka);
        System.out.println("The Key Calculated at the Receiver's Side: " + kb);

        if (ka == kb) {
            System.out.println("\nDiffie Hellman Secret key was Calculated ");
        } else {
            System.out.println("\nDiffie Hellman Secret key was not Calculated");
        }
    }
}
