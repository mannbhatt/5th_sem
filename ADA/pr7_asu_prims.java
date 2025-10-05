import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class pr7_asu_prims {

    int minKey(int key[], Boolean mstSet[], int V) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    long printMST(int parent[], int graph[][], int V) {
        Instant start = Instant.now();
        int totalWeight = 0;
        System.out.println("\nEdge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
            totalWeight += graph[parent[i]][i];
        }
        System.out.println("Total Minimum Cost of MST = " + totalWeight);
        Instant end = Instant.now();
        Duration totaldiff = Duration.between(start, end);
        return totaldiff.toNanos();
    }

    long primMST(int graph[][], int V) {
        int parent[] = new int[V];
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        return printMST(parent, graph, V);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        pr7_asu_prims t = new pr7_asu_prims();

         System.out.print("Enter number of vertices: ");
            int V = sc.nextInt();
        while (true) {
            System.out.println("\n Menu");
            System.out.println("1. Directed Graph");
            System.out.println("2. Undirected Graph");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 3) {
                System.out.println("Exiting program.");
                break;
            }

            int graph[][] = new int[V][V];

            System.out.println("\nGenerated Random Adjacency Matrix:");

            
            System.out.print("     ");
            for (int i = 0; i < V; i++) {
                System.out.printf("%3d ", i);
            }
            System.out.println();
            System.out.println("    " + "----".repeat(V));

            
            for (int i = 0; i < V; i++) {
                System.out.printf("%3d |", i); // Row label
                for (int j = 0; j < V; j++) {
                    switch (choice) {
                        case 1 -> { 
                            if (i == j) {
                                graph[i][j] = 0;
                            } else {
                                graph[i][j] = (rand.nextInt(100) < 80) ? rand.nextInt(20) + 1 : 0;
                            }
                        }
                        case 2 -> {
                            if (i == j) {
                                graph[i][j] = 0;
                            } else if (j < i) {
                                graph[i][j] = graph[j][i]; // symmetry
                            } else {
                                graph[i][j] = (rand.nextInt(100) < 80) ? rand.nextInt(20) + 1 : 0;
                            }
                        }
                        default -> {
                            System.out.println("Invalid choice! Exiting.");
                            System.exit(0);
                        }
                    }
                    System.out.printf("%3d ", graph[i][j]);
                }
                System.out.println();
            }

            Instant start = Instant.now();
            t.primMST(graph, V);
            Instant end = Instant.now();
            double totalMillis = Duration.between(start, end).toNanos() / 1_000_000.0;
            System.out.printf("\nTotal Execution Time: %.4f milliseconds\n", totalMillis);
        }
        sc.close();
    }
}
