package com.trs;

import java.util.Arrays;
import java.util.Random;

public class PZ2 {

    private final static int LENGTH = (int) Math.pow(2, 4);

    public static void fillArray(int arr[]) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
        }
    }

    public static void batcherSort(int[] arr) {

        for (int p = arr.length; p > 0; p >>= 1) {

            int q = arr.length, r = 0, d = p;
            boolean b;
            do {
                int nTo = arr.length - d;
                for (int i = 0; i < nTo; i++) {
                    if ((i & p) == r) {
                        if (arr[i] > arr[i + d]) {
                            int temp = arr[i];
                            arr[i] = arr[i + d];
                            arr[i + d] = temp;
                        }
                    }
                }

                b = q != p;
                if (b) {
                    d = q - p;
                    q >>= 1;
                    r = p;
                }
            } while (b);
        }


    }

    public static void bubbleSort(int[] arr) {
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1])   // change to > for ascending sort
                {
                    temp = arr[j];                //swap elements
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
    }


    public static void main(String[] args) {

        int[] batcherSingleArr = new int[LENGTH];
        int[] bubbleSingleArr = new int[LENGTH];
        int[] batcherMultipleArr = new int[LENGTH];
        int[] bubbleMultipleArr = new int[LENGTH];

        fillArray(batcherSingleArr);

        System.arraycopy(batcherSingleArr, 0, bubbleSingleArr, 0, batcherSingleArr.length);
        System.arraycopy(batcherSingleArr, 0, batcherMultipleArr, 0, batcherSingleArr.length);
        System.arraycopy(batcherSingleArr, 0, bubbleMultipleArr, 0, batcherSingleArr.length);

        System.out.println(Arrays.toString(batcherSingleArr));
        System.out.println(Arrays.toString(bubbleSingleArr));
        System.out.println(Arrays.toString(batcherMultipleArr));
        System.out.println(Arrays.toString(bubbleMultipleArr));

        long before = System.nanoTime();
        batcherSort(batcherSingleArr);
        System.out.println("Batcher single thread = " + (System.nanoTime() - before));

        before = System.nanoTime();
        bubbleSort(bubbleSingleArr);
        System.out.println("Bubble single thread = " + (System.nanoTime() - before));

        System.out.println(Arrays.toString(batcherSingleArr));
        System.out.println(Arrays.toString(bubbleSingleArr));


        Thread[] threads = new Thread[4];
        before = System.nanoTime();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                batcherSort(batcherMultipleArr);
            });
            threads[i].start();
        }
        System.out.println("Batcher multiple thread = " + (System.nanoTime() - before));

        System.out.println(Arrays.toString(batcherMultipleArr));


    }

}







