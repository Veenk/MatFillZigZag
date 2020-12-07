import java.util.Arrays;
import java.util.Random;

public class MatFillZigZag {
    private int [][] mat;
    private int iter = 0;
    private int m, n = 0;
    public MatFillZigZag(int m, int n) {
        if(m > 0 && n > 0) {
            this.m = m;
            this.n = n;
            this.mat = new int[m][n];
            int [] A = new int[m*n];
            Random rnd = new Random();
            for (int i = 0; i < A.length;i++){
                A[i]= rnd.nextInt(n*m);
            }
            quickSort(A, 0, A.length-1);
            recFill(A,0, 0, 0);
        }
    }

    private void recFill(int[] B, int i_top, int j_left, int iteration) {
        int j_right;
        int i_bot;
        if (iteration > m + n - 1) {
            for (int row = 0; row < mat.length; row++) {
                for (int col = 0; col < mat[row].length; col++) {
                    System.out.printf("%d   ", mat[row][col]);
                }
                System.out.print("\n");
            }
        } else {

            if (iteration > n - 1) {
                i_top++;
                i_bot = Math.min(iteration, m - 1);
            } else i_bot = Math.min(iteration, m - 1);
            if (iteration > m - 1) {
                j_left++;
                j_right = Math.min(iteration, n - 1);
            } else j_right = Math.min(iteration, n - 1);

            if (iteration % 2 == 0) {
                for (int i = i_bot, j = j_left; i >= i_top && j <= j_right; i--, j++) {
                    mat[i][j] = B[iter];
                    iter++;
                }
            } else {
                for (int i = i_top, j = j_right; i <= i_bot && j >= j_left; i++, j--) {
                    mat[i][j] = B[iter];
                    iter++;
                }
            }

            iteration++;
            recFill(B, i_top, j_left, iteration);
        }
    }

    private void quickSort(int[] A,int low, int high){
         if (low <high+1){
             int p = partition(A, low, high);
             quickSort(A, low, p-1);
             quickSort(A, p+1, high);
         }
    }
    private void swap(int[] A, int index1, int index2){
        int tmp = A[index1];
        A[index1] = A[index2];
        A[index2] = tmp;
    }

    private int getPivot(int low, int high){
        Random rnd = new Random();
        return rnd.nextInt((high-low)+1)+low;
    }
     private int partition(int[]A, int low, int high){
        swap(A, low, getPivot(low, high));
        int border = low + 1;
        for (int i = border; i <= high; i++){
            if(A[i] < A[low]){
                swap(A,i,border++);
            }
        }
        swap(A,low,border-1);
        return border-1;
     }
}

