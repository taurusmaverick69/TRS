package com.trs;

import java.util.Random;

public class PZ2 {

    private final static int N = 16;
    private static int arr[] = new int[N];

    public static void fillArray() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(10);
        }
    }

    public static void printArray() {
        for (int i = 0; i < N; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    public static void batcherSort() {
        int p = N;
        while (p > 0) {
            int q = N, r = 0, d = p;
            boolean b;
            do {
                int nTo = N - d;
                for (int i = 0; i < nTo; i++)
                    if ((i & p) == r) {
                        if (arr[i] > arr[i + d]) {
                            int temp = arr[i];
                            arr[i] = arr[i + d];
                            arr[i + d] = temp;
                        }
                    }
                b = q != p;
                if (b) {
                    d = q - p;
                    q >>= 1;
                    r = p;
                }
            }
            while (b);
            p >>= 1;
        }
    }


    public static void main(String[] args) {
        fillArray();
        printArray();
        batcherSort();
        printArray();
    }

}







