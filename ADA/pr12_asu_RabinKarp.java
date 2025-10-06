import java.util.Scanner;

public class pr12_asu_RabinKarp {

    public final static int d = 256;

    static void rabinKarpSearch(String pat, String txt, int q) {
        int m = pat.length();
        int n = txt.length();
        int i, j;
        int p = 0; 
        int t = 0; 
        int h = 1;
        for (i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }
        for (i = 0; i < m; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }
        for (i = 0; i <= n - m; i++) {
             if (p == t) {
                for (j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }
            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;

                if (t < 0) {
                    t = (t + q);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String txt = sc.nextLine();
        System.out.print("Enter the pattern to search: ");
        String pat = sc.nextLine();
        int q = 101; 

        long startTime = System.nanoTime();
        rabinKarpSearch(pat, txt, q);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Execution Time: " + executionTime + " ms");

        sc.close();
    }
}
