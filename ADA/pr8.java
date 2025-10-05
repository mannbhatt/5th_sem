import java.util.*;
public class pr8_man {
    static int[] parent;
    static final int INF = 999999;
    static int find(int i) {
        while (parent[i] != 0)
            i = parent[i];
        return i;
    }
    static int union(int i, int j) {
        if (i != j) {
            parent[j] = i;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        parent = new int[n];
        int ne = 1, mincost = 0;

        System.out.println("\nEnter the cost adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                if (cost[i][j] == 0)
                    cost[i][j] = INF;
            }
        }

        long startTime = System.nanoTime();
        System.out.println("\nEdges in the Minimum Cost Spanning Tree:");
        while (ne < n) {
            int min = INF, a = -1, b = -1, u = -1, v = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cost[i][j] < min) {
                        min = cost[i][j];
                        a = u = i;
                        b = v = j;
                    }
                }
            }

            u = find(u);
            v = find(v);

            if (union(u, v) != 0) {
                System.out.println("Edge " + ne + ": (" + a + " -> " + b + ") cost = " + min);
                mincost += min;
                ne++;
            }

            cost[a][b] = cost[b][a] = INF;
        }
         long endTime = System.nanoTime();
          double timeTaken = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nMinimum cost of spanning tree = " + mincost);
        System.out.printf("Execution Time: %.4f milliseconds\n", timeTaken);
        sc.close();
    }
}
