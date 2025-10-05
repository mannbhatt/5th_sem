import java.util.*;
public class pr5 {

    static class Item {
        int id;
        int value;
        int weight;
        double ratio;

        public Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    static class KnapsackResult {
        double totalValue;
        List<String> selectedItems;
        long executionTimeNs;

        public KnapsackResult(double totalValue, List<String> selectedItems, long executionTimeNs) {
            this.totalValue = totalValue;
            this.selectedItems = selectedItems;
            this.executionTimeNs = executionTimeNs;
        }
    }

    private static class ItemRatioComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            return Double.compare(b.ratio, a.ratio);
        }
    }

    public static KnapsackResult fractionalKnapsack(int W, Item[] items) {
        long startTime = System.nanoTime();

        Arrays.sort(items, new ItemRatioComparator());

        double totalValue = 0.0;
        int currentWeight = 0;
        List<String> selectedItems = new ArrayList<>();
        
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                selectedItems.add("Item " + item.id);
            } else {
                int remainingCapacity = W - currentWeight;
                double fraction = (double) remainingCapacity / item.weight;
                double valueAdded = item.value * fraction;
                
                totalValue += valueAdded;
                currentWeight = W;
                
                selectedItems.add("Item " + item.id);
                
                break;
            }
        }
        
        long endTime = System.nanoTime();
        return new KnapsackResult(totalValue, selectedItems, endTime - startTime);
    }
    
    public static KnapsackResult zeroOneKnapsackGreedy(int W, Item[] items) {
        long startTime = System.nanoTime();
        
        Arrays.sort(items, new ItemRatioComparator());

        int totalValue = 0;
        int currentWeight = 0;
        List<String> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.length && currentWeight < W; i++) {
            Item item = items[i];

            if (currentWeight + item.weight <= W) {
                totalValue += item.value;
                currentWeight += item.weight;
                selectedItems.add("Item " + item.id);
            }
        }
        
        long endTime = System.nanoTime();
        return new KnapsackResult(totalValue, selectedItems, endTime - startTime);
    }

    public static void displayResults(int capacity, Item[] generatedItems, KnapsackResult fractionalResult, KnapsackResult zeroOneResult) {
        final double NANOS_TO_MILLIS = 1_000_000.0; 
        double fractionalTimeMs = fractionalResult.executionTimeNs / NANOS_TO_MILLIS;
        double zeroOneTimeMs = zeroOneResult.executionTimeNs / NANOS_TO_MILLIS;


        System.out.println("\nGenerated Items");
        for (Item item : generatedItems) {
            System.out.printf("Item %d: Value=%d, Weight=%d, Ratio=%.2f\n", item.id, item.value, item.weight, item.ratio);
        }
        System.out.println();
        System.out.printf("KNAPSACK RESULTS (Capacity W: %d)\n", capacity);

        System.out.println("\nFractional Knapsack");
        System.out.printf("Max Value: %.2f\n", fractionalResult.totalValue);
        System.out.printf("Execution Time: %.6f ms\n", fractionalTimeMs);
        System.out.println("Selected Items: " + fractionalResult.selectedItems);
        
        System.out.println("\n0/1 Knapsack");
        System.out.printf("Value: %d\n", (int)zeroOneResult.totalValue);
        System.out.printf("Execution Time: %.6f ms\n", zeroOneTimeMs);
        System.out.println("Selected Items: " + zeroOneResult.selectedItems);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
		System.out.println();
        System.out.print("Enter the maximum knapsack capacity: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();
        
        final int MAX_VALUE = 100;
        final int MIN_WEIGHT = 5;
        final int MAX_WEIGHT = 30;
        
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < numItems; i++) {
            int value = random.nextInt(MAX_VALUE) + 1;
            int weight = random.nextInt(MAX_WEIGHT - MIN_WEIGHT + 1) + MIN_WEIGHT;
            
            Item newItem = new Item(i + 1, value, weight);
            itemsList.add(newItem);
        }

        scanner.close();
        Item[] availableItems = itemsList.toArray(new Item[0]);
        KnapsackResult fractionalResult = fractionalKnapsack(capacity, availableItems.clone());
        KnapsackResult zeroOneResult = zeroOneKnapsackGreedy(capacity, availableItems.clone());

        displayResults(capacity, availableItems, fractionalResult, zeroOneResult);
    }
}
