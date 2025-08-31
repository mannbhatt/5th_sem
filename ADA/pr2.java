import java.util.Random;
import java.util.Scanner;

public class pr2 {
     public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int[] a = new int[100];
        for (int i = 0; i <= 100 - 1; i++) {
            a[i] = r.nextInt(100);
        }
        int low=0;
        int high=a.length-1;
        System.out.println(
                "\n \n***enter your choice:***\n   merge short =1 \n  quick short =2 \n   ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                merge_short(a, low,high);
                System.out.println("merge short output:");
                display(a);
                break;
            case 2:
                quick_sort(a, low, high);
                System.out.println("quick short output:");
                display(a);
                break;
            default:
                break;
        }
         
        sc.close();
    }
        static void combine(int arr[],int low,int mid, int high){
            int l1=mid-low+1;
            int l2=high-mid;
            int left[] = new int[l1];
            int right[] = new int[l2];
            for(int i=0;i<l1;i++){
                left[i]=arr[low+i];

            }
            for (int j = 0; j < l2; j++){
            right[j] = arr[mid +1 + j];
             }
             int i = 0, j = 0;

 
        int k = low;
        while (i < l1 && j < l2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < l1) {
            arr[k] = left[i];
            i++;
            k++;
        }

       
        while (j < l2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
        
         static void merge_short(int arr[],int low,int high){
            
             if (low < high) {   
            
           int mid=(low+high)/2;
                merge_short(arr,low,mid);
                merge_short(arr,mid+1,high);
                combine(arr,low,mid,high);
        }
            
        }

       static void display(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
       }

static int partition(int[] arr, int low, int high) {
        
        int pivot = arr[high];   
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);  
        return i + 1;
    }

    
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
    static void quick_sort(int[] arr, int low, int high) {
        if (low < high) {
            
           
            int pi = partition(arr, low, high);
            quick_sort(arr, low, pi - 1);
            quick_sort(arr, pi + 1, high);
        }
    }

   
}
