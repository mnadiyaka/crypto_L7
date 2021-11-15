import java.util.concurrent.ThreadLocalRandom;

public class Z1 {
    public static void main(String[] args) {
        System.out.println(testMR(3547)+"%");
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
            int a = ThreadLocalRandom.current().nextInt(2, (n-2) + 1);;
            System.out.println("a=" +a);
            int x = ((int) Math.pow(a, t))%n;
            if (x == 1 || x == n - 1) {
                persentage++;
                continue;
            }
            for (int j = 0; j < s - 1; j++)
            {
                x = (x*x)%n;
                if (x == 1)
                    return 0;
                if (x == n - 1) {
                    persentage++;
                    break ;
                }
            }
            if (x != n-1)
                return 0;
        }
        return (persentage-1)/r*100;
    }
}
