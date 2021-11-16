import java.math.BigInteger;
import java.security.SecureRandom;

public class Z1 {
    public static void main(String[] args) {
        System.out.println(testMR(3463)+"%");
    }

    public static int testMR(int n) {
        int persentage = 1;

        if (n == 2 || n == 3){
            return 100;
        }
        if (n <2 || n%2 ==0){
            return 0;
        }

        int r = (int)Math.log(n);

        int t = n-1;
        int s= 0;
        while (t%2==0){
            t/=2;
            s++;
        }

        System.out.println("s="+s+" t="+t);
        m: for (int i = 0; i < r; i++)
        {
            BigInteger a;
            SecureRandom random = new SecureRandom();
            byte bytes[];// = new byte[Integer.toBinaryString(n).length()];
            //random.nextBytes(bytes);
            do
            {
                bytes = random.generateSeed(s+1);
                a = new BigInteger(bytes);
                //System.out.println(a);
            }
            while (a.compareTo(BigInteger.valueOf(2)) <0 || a.compareTo(BigInteger.valueOf(n - 2))>0);
            System.out.println("a=" +a);
            BigInteger x = a.modPow(BigInteger.valueOf(t),BigInteger.valueOf(n));
            if (x.compareTo(BigInteger.valueOf(1)) == 0 || x.compareTo(BigInteger.valueOf(n - 1))==0) {
                persentage++;
                continue;
            }
            for (int j = 0; j < s - 1; j++)
            {
                x = x.modPow(BigInteger.valueOf(2),BigInteger.valueOf(n));
                if (x.compareTo(BigInteger.valueOf(1)) == 0)
                    return 0;
                if (x.compareTo(BigInteger.valueOf(n - 1))==0) {
                    persentage++;
                    break ;
                }
            }
            if (x.compareTo(BigInteger.valueOf(n - 1))!=0) {
                return  0;
            }
        }
        return (persentage-1)/r*100;
    }
}
