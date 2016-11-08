package com.trs;

import java.util.Random;

public class PZ2 {

    private final static int LENGTH = 16;
    private static int arr[] = new int[LENGTH];

    public static void fillArray() {
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            arr[i] = random.nextInt(10);
        }
    }

    public static void printArray() {
        for (int i = 0; i < LENGTH; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }

    public static void batcherSort() {
        int p = LENGTH;
        while (p > 0) {
            int q = LENGTH, r = 0, d = p;
            boolean b;
            do {
                int nTo = LENGTH - d;
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
            } while (b);
            p >>= 1;
        }
    }


    public static void main(String[] args) {
        fillArray();
        printArray();

        Thread[] threads = new Thread[4];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {

            });
            threads[i].start();
        }

        batcherSort();




        printArray();
    }

}







