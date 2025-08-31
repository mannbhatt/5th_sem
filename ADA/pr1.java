import java.util.Random;
import java.util.Scanner;

class pr1 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int[] a = new int[10000];
        for (int i = 0; i <= 10000 - 1; i++) {
            a[i] = r.nextInt(10000);
        }
        System.out.println(
                "\n \n***enter your choice:***\n   insertion short =1 \n   bubble short =2 \n   selection sort =3\n  enhanced bubble short =4\n ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                insertion(a);
                break;
            case 2:
                bubble(a);
                break;
            case 3:
                selection(a);
                break;
            case 4:
                enhancedBubble(a);
                break;
            default:
                break;
        }

        sc.close();
    }

    // Insertion Short time complexity best case=o(n),worst case = o(n^2)

    static void insertion(int[] arr) {
        for (int j = 1; j < arr.length; j++) {
            int i = j - 1;
            int k = arr[j];
            while (i >= 0 && arr[i] > k) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = k;
        }
        System.out.println("Insertion Short \nTime Complexity best case is o(n) & worst case is o(n^2)");
        display(arr);
    }

    // Bubble short

    static void bubble(int arr[]) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 1; j < arr.length - i; j++) {

                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Bubble short : \nTime Complexity:-  best case & worst case is o(n^2)");
        display(arr);
    }

    static void enhancedBubble(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
                int flag=0;
            for (int j = 1; j < arr.length - i; j++) {

                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = 1;
                }
            }
            if(flag==0){
                break;
            }
        }
        System.out.println("Bubble short : \nTime Complexity:-  best case & worst case is o(n^2)");
        display(arr);
    }

    // Selection Short

    static void selection(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Selection Short \nTime Complexity best case & worst case is o(n^2)");
        display(arr);
    }

    // Displaying shorted array
 
    static void display(int arr[]) {
        System.out.println("Shorted array:");
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }
}