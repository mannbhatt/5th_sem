import java.util.*;
public class pr7 {
    private int V; // Number of vertices
    // Constructor
    public pr7(int vertices) {
        this.V = vertices; // used because we don't have to write that agan and again that's why
    }

    // Function to find vertex with minimum key value
    private int minKey(int[] key, boolean[] inMST) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Function to print MST
    private void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            }
        }
    }

    // Prim’s Algorithm
    public void primMST(int[][] graph, int startVertex) {
        int[] parent = new int[V];      // store the minimum vertices in MST
        int[] key = new int[V];         
        boolean[] inMST = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);        // firstly we intialize the all the key as the infinate  
        key[startVertex] = 0;
        parent[startVertex] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, inMST);
            inMST[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    // Generate random graph
    public static int[][] generateRandomGraph(int V, int E) {
        int maxEdges = (V * (V - 1)) / 2;

        if (E > maxEdges || E < 0) {
            System.out.println("Error: Invalid number of edges! Maximum edges for " + V + " vertices is " + maxEdges);
            System.exit(0);
        }

        Random rand = new Random();
        int[][] graph = new int[V][V];
        int edgesAdded = 0;

        while (edgesAdded < E) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);

            if (u != v && graph[u][v] == 0) {
                int weight = rand.nextInt(50) + 1;
                graph[u][v] = weight;
                graph[v][u] = weight;
                edgesAdded++;
            }
        }
        return graph;
    }

    // Print graph
    public static void printGraph(int[][] graph) {
        int V=graph.length;
        System.out.println("\nAdjacency Matrix (Graph):");

    // Print column headers
    System.out.print("    ");
    for (int i = 0; i < V; i++) {
        System.out.print(i + "\t");
    }
    System.out.println();

    // Print separator line
    System.out.print("    ");
    for (int i = 0; i < V; i++) {
        System.out.print("--\t");
    }
    System.out.println();

    // Print rows with row index
    for (int i = 0; i < V; i++) {
        System.out.print(i + " | ");   // Row label
        for (int j = 0; j < V; j++) {
            System.out.print(graph[i][j] + "\t");
        }
        System.out.println();
    }
    }

    // Main function with menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int[][] graph = generateRandomGraph(V, E);
        printGraph(graph);

        PrimMST prim = new PrimMST(V);

        while (true) {
            System.out.println("\nEnter your choise:- ");
            System.out.println("1. Run Prim's MST for one vertex");
            System.out.println("2. Run Prim's MST for all vertices");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the starting vertex (0 to " + (V - 1) + "): ");
                    int startVertex = sc.nextInt();
                    if (startVertex >= 0 && startVertex < V) {
                        System.out.println("\nRunning Prim’s MST from vertex: " + startVertex);
                        long startTime = System.nanoTime();
                        prim.primMST(graph, startVertex);
                        long endTime = System.nanoTime();
                        System.out.println("Execution Time: " + (endTime - startTime)+ " ns");
                    } else {
                        System.out.println("Invalid vertex!");
                    }
                    break;

                case 2:
                    System.out.println("\nRunning Prim's MST for ALL vertices:");
                    for (int s = 0; s < V; s++) {
                        System.out.println("\nFrom vertex: " + s);
                        long startTime = System.nanoTime();
                        prim.primMST(graph, s);
                        long endTime = System.nanoTime();
                        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
                    }
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
