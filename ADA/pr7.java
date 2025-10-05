import java.util.*;
public class pr7 {

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

    void printMST(int parent[], int graph[][], int V) {
        int totalWeight = 0;
        System.out.println("\nEdge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
            totalWeight += graph[parent[i]][i];
        }
        System.out.println("Total Minimum Cost of MST = " + totalWeight);
    }

    void primMST(int graph[][], int V) {
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

        printMST(parent, graph, V);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int graph[][] = new int[V][V];

        System.out.println("\nEnter the adjacency matrix (use 0 if no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        pr7 t = new pr7();
        t.primMST(graph, V);
    }
}

