import java.util.*;

public class pr8_asu_krushkal {
    static int[] parent;
    static final int INF = 999999;
    static Random rand = new Random();

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
    static int[][] generateRandomAdjMatrix(int n, int maxWeight) {
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    cost[i][j] = 0; // no self-loop
                } else {
                    // Randomly decide if an edge exists (70% chance)
                    boolean hasEdge = rand.nextDouble() < 0.7;

                    if (hasEdge)
                        cost[i][j] = rand.nextInt(maxWeight) + 1; // weight between 1 and maxWeight
                    else
                        cost[i][j] = 0; // no edge
                }
            }
        }
        // Make it symmetric (undirected graph)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[j][i] = cost[i][j];
            }
        }
        return cost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(" ");
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter maximum edge weight: ");
        int maxWeight = sc.nextInt();

        int[][] cost = generateRandomAdjMatrix(n, maxWeight);
        parent = new int[n];
        int ne = 1, mincost = 0;

        System.out.println("\nGenerated Random Cost Adjacency Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((cost[i][j] == 0 ? 0 : cost[i][j]) + "\t");
            }
            System.out.println();
        }

        long startTime = System.nanoTime();
        System.out.println("\nEdges in the Minimum Cost Spanning Tree:");
        while (ne < n) {
            int min = INF, a = -1, b = -1, u = -1, v = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cost[i][j] < min && cost[i][j] != 0) {
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
