import java.util.Scanner;

public class Knapsack {

    public static void main(String args[]) {
        inputKanpsack();
    }

    public static int max(int a, int b) {
        if(a > b) {
            return a;
        }
        return b;
    }

    public static void inputKanpsack() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nType the number of items available: ");
        int n = input.nextInt();
        int val[] = new int[n];
        int wt[] = new int[n];
        System.out.print("\nType the maximum capacity: ");
        int W = input.nextInt();
        System.out.print("\nType the value of each item:\n");
        for (int i = 0; i < n; i++) {
            val[i] = input.nextInt();
        }
        System.out.print("\nType the weight of each item:\n");
        for (int i = 0; i < n; i++) {
            wt[i] = input.nextInt();
        }
        System.out.println("\nThe maximum value possible given by the items weight and the max capacity is: " + knapSack(W, wt, val, n));
    }

    public static int knapSack(int maxWeight, int weights[], int values[], int n) {
        int i, w;
        int K[][] = new int[n + 1][maxWeight + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0) {
                    K[i][w] = 0;
                }
                else if (weights[i - 1] <= w) {
                    K[i][w] = max(values[i - 1] + K[i - 1][w - weights[i - 1]], K[i - 1][w]);
                }
                else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }
        return K[n][maxWeight];
    }

}
