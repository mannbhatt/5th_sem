import java.util.*;

public class pr11_asu_CoinChangeDP {

    static int [][]C;
    public static int makeAChange(int[] d, int N) {
        int n = d.length;  
        C = new int[n + 1][N + 1];  
        for (int i = 0; i <= n; i++) {
            C[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j < d[i - 1]) {
                    C[i][j] = Integer.MAX_VALUE / 2; 
                } else if (i == 1) {
                    C[i][j] = 1 + C[1][j - d[i - 1]];
                } else if (j < d[i - 1]) {
                    C[i][j] = C[i - 1][j];
                } else {
                    C[i][j] = Math.min(C[i - 1][j], 1 + C[i][j - d[i - 1]]);
                }
            }
        }

        return C[n][N];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter number of coins which you want: ");
        int n = sc.nextInt();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = rand.nextInt(30) + 1;
        }
        System.out.print("Generated coins : ");
        for (int i = 0; i < n; i++) {
            System.out.print(d[i] + " ");
        }
        System.out.println();

        System.out.print("Enter amount to make change for: ");
        int N = sc.nextInt();

        long startTime = System.nanoTime();
        int result = makeAChange(d, N);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;  
        double durationInMillis = duration / 1_000_000.0; 

        if (result >= Integer.MAX_VALUE / 2) {
            System.out.println("Change cannot be made with given denominations.");
        } else {
            System.out.println("Minimum coins required: " + result);

            int i = n, j = N;
            System.out.print("Coins used: ");
            while (i > 0 && j > 0) {
                if (i > 1 && C[i][j] == C[i - 1][j]) {
                    i--; 
                } else {
                    System.out.print(d[i - 1] + " "); 
                    j -= d[i - 1];
                }
        }
        System.out.println();
        }
        System.out.printf("Time taken: %.5f ms%n", durationInMillis);
        sc.close();
    }
}
