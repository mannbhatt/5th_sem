import java.util.*;
public class pr4 {
    public static Random rand = new Random();
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            return new int[][]{{A[0][0] * B[0][0]}};
        }

        int newSize = n / 2;
        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];
        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        // Split
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + newSize];
                a21[i][j] = A[i + newSize][j];
                a22[i][j] = A[i + newSize][j + newSize];

                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + newSize];
                b21[i][j] = B[i + newSize][j];
                b22[i][j] = B[i + newSize][j + newSize];
            }
        }

        int[][] p1 = strassen(a11, subtract(b12, b22));
        int[][] p2 = strassen(add(a11, a12), b22);
        int[][] p3 = strassen(add(a21, a22), b11);
        int[][] p4 = strassen(a22, subtract(b21, b11));
        int[][] p5 = strassen(add(a11, a22), add(b11, b22));
        int[][] p6 = strassen(subtract(a12, a22), add(b21, b22));
        int[][] p7 = strassen(subtract(a11, a21), add(b11, b12));

        int[][] c11 = add(subtract(add(p5, p4), p2), p6);
        int[][] c12 = add(p1, p2);
        int[][] c21 = add(p3, p4);
        int[][] c22 = subtract(subtract(add(p1, p5), p3), p7);

        int[][] C = new int[n][n];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                C[i][j] = c11[i][j];
                C[i][j + newSize] = c12[i][j];
                C[i + newSize][j] = c21[i][j];
                C[i + newSize][j + newSize] = c22[i][j];
            }
        }
        return C;
    }

    public static int[][] randomMatrix(int r, int c) {
        int[][] M = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                M[i][j] = rand.nextInt(10); // values 0–9
        return M;
    }

    public static void printMatrix(int[][] M) {
        for (int[] row : M) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    public static int powerofTwo(int n){
        int pow=1;
        while (pow<n)pow *= 2;
            return pow;
    }

    public static int [][] Padding(int [][] M, int newSize){
        int [][] P = new int [newSize][newSize];
        for(int i = 0; i<M.length ; i++){
            for (int j = 0; j < M[0].length ; j++){
                P[i][j] = M[i][j];
            }
        }
        return P;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Matrix A
        System.out.println("");
        System.out.print("Enter rows and cols of A: ");
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        int[][] A = randomMatrix(r1, c1);

        // Matrix B
        System.out.print("Enter rows and cols of B: ");
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();
        int[][] B = randomMatrix(r2, c2);

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);

        if (c1 !=r2){
            System.out.println("Matrixs multiplication is not possible ");
            return ;
        }
        long start = System.nanoTime();
        int[][] C;

        int maxSize=Math.max(Math.max(r1,c1),Math.max(r2,c2));
        int newSize=powerofTwo(maxSize);

        int [][] A_Padding = Padding(A, newSize);
        int [][] B_padding = Padding(B,newSize);

        //System.out.println("Matrixs multiplication is :-  ");
        int [][] C_padding=strassen(A_Padding, B_padding);

        C=new int[r1][c2];
        for(int i = 0 ; i < r1; i++ ){
            for (int j = 0; j< c1 ; j++){
                C[i][j] = C_padding[i][j];

            }
        }
        long end = System.nanoTime(); 

        System.out.println("Resultant Matrix A*B :- ");
        printMatrix(C);
        double timeTaken = (end - start) / 1_000_000.0;
        System.out.println("");
        System.out.println("Time Taken: " + timeTaken + " ms");
        
        sc.close();
    }
}
