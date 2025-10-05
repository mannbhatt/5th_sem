import java.util.*;

public class pr5 {

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


    public static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                System.out.println("Took full item " + item.id);
            } else {
                int remain = W - currentWeight;
                double fraction = (double) remain / item.weight;
                totalValue += item.value * fraction;
                System.out.println("Took " + (fraction * 100) + "% of item " + item.id);
                break;
            }
        }

        return totalValue;
    }

    public static int zeroOneKnapsackGreedy(int W, Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int totalValue = 0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                System.out.println("Selected item " + item.id);
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Knapsack Capacity: ");
        int W = sc.nextInt();

        System.out.print("Enter Number of Items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight of item " + (i + 1) + ": ");
            int v = sc.nextInt();
            int w = sc.nextInt();
            items[i] = new Item(i + 1, v, w);
        }

        System.out.println("\nChoose Method:");
        System.out.println("1. Fractional Knapsack (Greedy Optimal)");
        System.out.println("2. 0/1 Knapsack (Greedy Approximation)");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            double result = fractionalKnapsack(W, items);
            System.out.printf("\nMaximum value (Fractional) = %.2f\n", result);
        } else if (choice == 2) {
            int result = zeroOneKnapsackGreedy(W, items);
            System.out.println("\nApproximate value (0/1 Greedy) = " + result);
        } else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
