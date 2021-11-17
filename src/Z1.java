import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class Z1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println(testMR(sc.nextInt()) + "%");
        int[] incr = incrypt(sc.nextInt());
        System.out.println("incrypted: " + incr[0]);
        System.out.println("decrypted: " + decrypt(incr[0], incr[1], incr[2], incr[3]));

    }

    public static int testMR(int n) {
        int persentage = 1;

        if (n == 2 || n == 3) {
            return 100;
        }
        if (n < 2 || n % 2 == 0) {
            return 0;
        }

        int r = (int) Math.log(n);

        int t = n - 1;
        int s = 0;
        while (t % 2 == 0) {
            t /= 2;
            s++;
        }

        //System.out.println("s=" + s + " t=" + t);
        m:
        for (int i = 0; i < r; i++) {
            BigInteger a;
            SecureRandom random = new SecureRandom();
            byte bytes[];
            do {
                bytes = random.generateSeed(s);
                a = new BigInteger(bytes);
                //System.out.println(a);
            }
            while (a.compareTo(BigInteger.valueOf(2)) < 0 || a.compareTo(BigInteger.valueOf(n - 2)) > 0);
            //System.out.println("a=" + a);
            BigInteger x = a.modPow(BigInteger.valueOf(t), BigInteger.valueOf(n));
            if (x.compareTo(BigInteger.valueOf(1)) == 0 || x.compareTo(BigInteger.valueOf(n - 1)) == 0) {
                persentage++;
                continue;
            }
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(BigInteger.valueOf(2), BigInteger.valueOf(n));
                if (x.compareTo(BigInteger.valueOf(1)) == 0)
                    return 0;
                if (x.compareTo(BigInteger.valueOf(n - 1)) == 0) {
                    persentage++;
                    break;
                }
            }
            if (x.compareTo(BigInteger.valueOf(n - 1)) != 0) {
                return 0;
            }
        }
        return (persentage - 1) / r * 100;
    }

    public static int[] generete(int data) {

        int p = (int) (1 + Math.random() * data);
        p = (p % 2 == 0) ? p - 1 : p;
        while (testMR(p) == 0) {
            p = (int) (1 + Math.random() * data);
            p = (p % 2 == 0) ? p - 1 : p;
        }
        int q = (int) (1 + Math.random() * data);
        q = (q % 2 == 0) ? q - 1 : q;
        while (testMR(q) == 0 || p == q) {
            q = (int) (1 + Math.random() * data);
            q = (q % 2 == 0) ? q - 1 : q;
        }
        System.out.println("p=" + p + " q= " + q);
        int[] res = {p, q};
        return res;
    }

    public static int[] incrypt(int data) {
        int[] pq = generete(data);

        int n = pq[0] * pq[1];
        int z = (pq[0] - 1) * (pq[1] - 1);
        int e;
        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("the value of open key = " + e);
        int[] c = {(int) (Math.pow(data, e)) % n, e, pq[0], pq[1]};
        return c;
    }

    public static int decrypt(int data, int key, int p, int q) {
        int n = p * q;
        int z = (p - 1) * (q - 1);

        int i, e, d = 0;

        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            if (x % key == 0) {
                d = x / key;
                break;
            }
        }
        System.out.println("the value of hidden key = " + d);
        return (int) Math.pow(data, d) % n;
    }

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
}