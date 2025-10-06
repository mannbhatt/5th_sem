import java.util.*;

public class pr10_asu_MatrixChainMultiplication {

    // Function to find the minimum number of multiplications
    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1;  // number of matrices
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        // Initialization: m[i][i] = 0
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        // l = chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println("\nMinimum number of multiplications: " + m[1][n]);
        System.out.print("Optimal Parenthesization: ");
        printOptimalParens(s, 1, n);
        System.out.println();
    }

    // Function to print the optimal parenthesization
    public static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter number of matrices: ");
        int n = sc.nextInt();

        int[] p = new int[n + 1];
        System.out.println("Enter dimensions:");
        for (int i = 0; i <= n; i++) {
            p[i] = rand.nextInt(46);
        }
        System.out.print("Generated dimensions: ");
        for (int i = 0; i <= n; i++) {
            System.out.print(p[i] + " ");
        }
        System.out.println();
        
        long startTime = System.nanoTime();
        matrixChainOrder(p);
        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Execution Time: " + executionTime + " ms");

        sc.close();
    }
}
