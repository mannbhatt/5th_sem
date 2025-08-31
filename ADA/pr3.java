import java.util.Random;
import java.util.Scanner;
public class pr3 {
    public static void main(String[] args) {
        
        
         Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int[] a = new int[10000];
        for (int i = 0; i <= 10000 - 1; i++) {
            a[i] = r.nextInt(10000);
        }
        int low=0;
        int high=a.length-1;
        System.out.println("enter the key that you want to search:");
        int key = sc.nextInt();
        
           System.out.println(
                "\n \n***enter your choice:***\n  linearsearch =1 \n  binarysearch =2 \n   ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                linearsearch(a,key);
                break;
            case 2:
                binarysearch(a, high, low, key);
                break;
            default:
                break;
        }
         
        sc.close();
      
    }
    static void linearsearch(int arr[],int key){
        int flag=0;
        int a=-1;
        for(int i =0;i<arr.length;i++){
            if(arr[i]==key){
                flag=1;
                a=i;
                break;
            }
        }
        if(flag==1){
            System.out.println("element is found at "+a+" index of array");
        }
        else{
            System.out.println("element is not found !");
        }
    }
    static void binarysearch(int arr[],int high,int low,int key){
        if(low>high){
            System.out.println("element is not found !");
        }
        else{
            int mid=(low+high)/2;
            if(arr[mid]==key){
                System.out.println("searched element is found at"+mid+"index of array");

            }
            else if(arr[mid]>key){
                binarysearch(arr,mid-1,low,key);
            }
            else{
                binarysearch(arr,high,mid+1,key);
            }
        }
    }
}
 
