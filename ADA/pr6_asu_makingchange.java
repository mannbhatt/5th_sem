import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class pr6_asu_makingchange{

    public static int greedyChange(int amount, int[] coins) {
        Arrays.sort(coins);
        int n = coins.length;
        int[] sortedCoins = new int[n];
        for (int i = 0; i < n; i++) {
            sortedCoins[i] = coins[n - i - 1]; 
        }
        int remainingAmount = amount;
        int totalCoins = 0;
        List<Integer> coinList = new ArrayList<>();

        for (int coin : sortedCoins) {
            if (remainingAmount >= coin) {
                int count = remainingAmount / coin;
                remainingAmount -= count * coin;
                totalCoins += count;
                for (int i = 0; i < count; i++) {
                    coinList.add(coin);
                }
            }
        }
        if (remainingAmount > 0) {
            System.out.println("Change cannot be given exactly with the provided denominations.");
        } else {
            System.out.println("Total coins used: " + totalCoins);
        }

        System.out.println("Coins used as list: " + coinList);
        return totalCoins;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("\nEnter number of coin denominations: ");
                    int n = sc.nextInt();
                    int[] coins = new int[n];

                    System.out.println("\nGenerated coin denominations:");
                    for (int i = 0; i < n; i++) {
                        coins[i] = rand.nextInt(30) + 1; // Random denominations between 1 and 20
                        System.out.print(coins[i] + "  ");
                    }
        while (true) {
            System.out.println("\nCOIN CHANGE MENU ");
            System.out.println("1. Make Change (Greedy Method)");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {

                    System.out.print("\n\nEnter the amount to make change for: ");
                    int amount = sc.nextInt();

                    Instant start = Instant.now();
                    greedyChange(amount, coins);
                    Instant end = Instant.now();

                    Duration timeTaken = Duration.between(start, end);
                    System.out.printf("\nExecution Time: %.6f milliseconds%n", timeTaken.toNanos() / 1_000_000.0);
                    break;
                }
                case 2:
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        }
    }
}
