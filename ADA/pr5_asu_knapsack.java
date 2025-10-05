import java.util.*;

public class pr5_asu_knapsack {

    static class Item {
        int id;
        int value;
        int weight;
        double ratio;

        Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    public static double fractionalKnapsack(int W, Item[] items, List<Integer> selected) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                selected.add(item.id);
            } else {
                int remain = W - currentWeight;
                double fraction = (double) remain / item.weight;
                totalValue += item.value * fraction;
                selected.add(item.id);
                break;
            }
        }
        return totalValue;
    }

    public static int zeroOneKnapsackGreedy(int W, Item[] items, List<Integer> selected) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int totalValue = 0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                selected.add(item.id);
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter the maximum knapsack capacity: ");
        int W = sc.nextInt();

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("\nItems:-");
        for (int i = 0; i < n; i++) {
            int value = 10 + rand.nextInt(20);
            int weight = 5 + rand.nextInt(25);
            items[i] = new Item(i + 1, value, weight);
            System.out.printf("Item %d: Value=%d, Weight=%d, Ratio=%.2f\n",
                    i + 1, value, weight, items[i].ratio);
        }

        while (true) {
            System.out.println("\nChoose Method:");
            System.out.println("1. Fractional Knapsack");
            System.out.println("2. 0/1 Knapsack (Greedy Approximation)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    List<Integer> selected = new ArrayList<>();
                    long start = System.nanoTime();
                    double maxValue = fractionalKnapsack(W, items, selected);
                    long end = System.nanoTime();
                    double time = (end - start) / 1_000_000.0;

                    System.out.println("\nKNAPSACK RESULTS (Capacity W: " + W + ")");
                    System.out.println("\nFractional Knapsack");
                    System.out.printf("Max Value: %.2f\n", maxValue);
                    System.out.printf("Execution Time: %.6f ms\n", time);
                    System.out.println("Selected Items: " + selected);
                    break;
                }
                case 2: {
                    List<Integer> selected = new ArrayList<>();
                    long start = System.nanoTime();
                    int value = zeroOneKnapsackGreedy(W, items, selected);
                    long end = System.nanoTime();
                    double time = (end - start) / 1_000_000.0;

                    System.out.println("\nKNAPSACK RESULTS (Capacity W: " + W + ")");
                    System.out.println("\n0/1 Knapsack");
                    System.out.println("Value: " + value);
                    System.out.printf("Execution Time: %.6f ms\n", time);
                    System.out.println("Selected Items: " + selected);
                    break;
                }
                case 3:
                    System.out.println("Exiting program");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}
