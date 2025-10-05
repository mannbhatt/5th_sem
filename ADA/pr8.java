import java.util.*;
public class pr8 {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight; 
        }
    }
    static int[][] generateMatrix(int V, int E) {
        Random rand = new Random();
        int[][] matrix = new int[V][V];
        int count = 0;
        while (count < E) {
            int src = rand.nextInt(V);
            int dest = rand.nextInt(V);

            if (src == dest) continue; 
            if (matrix[src][dest] != 0) continue; 
            int weight = rand.nextInt(20) + 1; 
            matrix[src][dest] = weight;
            matrix[dest][src] = weight;
            count++;
        }
        return matrix;
    }

    static void printMatrix(int[][] matrix, int V) {
        System.out.println("\nAdjacency Matrix:");
        System.out.print("    ");
        for (int i = 0; i < V; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j < V; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    static ArrayList<Edge> matrixToEdges(int[][] matrix, int V) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (matrix[i][j] != 0) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        return edges;
    }

    static void printEdges(ArrayList<Edge> edges) {
        System.out.println("\nStarting Adjacency Edges (src -- dest == weight):");
        for (Edge e : edges) {
            System.out.println(e.src + " -- " + e.dest + " == " + e.weight);
        }
    }
    static int findParent(int parent[], int i) {
        if (parent[i] == i) return i;
        return parent[i] = findParent(parent, parent[i]);
    }
    static void union(int parent[], int rank[], int x, int y) {
        int xroot = findParent(parent, x);
        int yroot = findParent(parent, y);

        if (rank[xroot] < rank[yroot])
            parent[xroot] = yroot;
        else if (rank[xroot] > rank[yroot])
            parent[yroot] = xroot;
        else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }
    static void kruskalMST(ArrayList<Edge> edges, int V) {
        Collections.sort(edges);

        Edge result[] = new Edge[V - 1];
        int parent[] = new int[V];
        int rank[] = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int e = 0, i = 0;
        while (e < V - 1 && i < edges.size()) {
            Edge next = edges.get(i++);
            int x = findParent(parent, next.src);
            int y = findParent(parent, next.dest);

            if (x != y) {
                result[e++] = next;
                union(parent, rank, x, y);
            }
        }

 
        System.out.println("\nEdges in MST:");
        int totalWeight = 0;
        for (int j = 0; j < e; j++) {
            System.out.println(result[j].src + " -- " + result[j].dest + " == " + result[j].weight);
            totalWeight += result[j].weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int maxEdges = V * (V - 1) / 2;
        if (E > maxEdges) {
            System.out.println("\n Maximum possible edges for " + V + " vertices is " + maxEdges);
            return;
        }
        if (E < V - 1) {
            System.out.println("\n  Minimum edges required for MST with " + V + " vertices is " + (V - 1));
            return;
        }

        int[][] matrix = generateMatrix(V, E);

        printMatrix(matrix, V);

        ArrayList<Edge> edges = matrixToEdges(matrix, V);

        printEdges(edges);
        long start = System.nanoTime();
        kruskalMST(edges, V);
        long end = System.nanoTime();

        System.out.println("Executing time is : - " + (end - start) + " ns");

        sc.close();
    }
}
