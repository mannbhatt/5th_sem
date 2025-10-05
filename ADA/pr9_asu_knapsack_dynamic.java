import java.util.*;

public class pr9_asu_knapsack_dynamic {

    // Function for Binary Knapsack
    public static int knapsack(int W, int[] w, int[] v, int n, int[][] V) {
        // Build table V[][] in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int weight = 0; weight <= W; weight++) {
                if (w[i - 1] <= weight) {
                    V[i][weight] = Math.max(v[i - 1] + V[i - 1][weight - w[i - 1]], V[i - 1][weight]);
                } else {
                    V[i][weight] = V[i - 1][weight];
                }
            }
        }
        return V[n][W];
    }

    // Random item generator
    public static void generateRandomItems(int[] weight, int[] value, int maxWeight, int maxValue) {
        Random rand = new Random();
        for (int i = 0; i < weight.length; i++) {
            weight[i] = rand.nextInt(maxWeight) + 1;
            value[i] = rand.nextInt(maxValue) + 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();
        System.out.print("Enter maximum possible item weight: ");
        int maxWeight = sc.nextInt();
        System.out.print("Enter maximum possible item value: ");
        int maxValue = sc.nextInt();

        int[] value = new int[n];
        int[] weight = new int[n];
        int[][] V = new int[n + 1][W + 1]; // DP table

        generateRandomItems(weight, value, maxWeight, maxValue);

        System.out.println("\nGenerated Items:");
        System.out.println("Item\tWeight\tValue");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + weight[i] + "\t" + value[i]);
        }

        long startTime = System.nanoTime();
        int maxVal = knapsack(W, weight, value, n, V);
        long endTime = System.nanoTime();

        System.out.println("\nMaximum value in knapsack = " + maxVal);

        double timeTaken = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Execution Time: %.4f milliseconds\n", timeTaken);

        // ---- BACKTRACKING STEP: Find selected items ----
        System.out.println("\nSelected items:");
        int res = maxVal;
        int wCap = W;
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != V[i - 1][wCap]) {  // Item was included
                System.out.println("Item " + i + " (Weight: " + weight[i - 1] + ", Value: " + value[i - 1] + ")");
                res -= value[i - 1];
                wCap -= weight[i - 1];
            }
        }

        sc.close();
    }
}
