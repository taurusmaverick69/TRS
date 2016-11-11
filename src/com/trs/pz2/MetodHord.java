package com.trs.pz2;

public class MetodHord {

    public static void main(String[] args) {
        double x0 = 2;
        double x1 = 10;
        double e = 0.001;
        double x = method_chord(x0, x1, e);
        System.out.println(x);
    }

    public static double method_chord(double x_prev, double x_curr, double e) {

        long before = System.nanoTime();
        double x_next = 0;
        double tmp;
        do {
            tmp = x_next;
            x_next = x_curr - function(x_curr) * (x_prev - x_curr) / (function(x_prev) - function(x_curr));
            x_prev = x_curr;
            x_curr = tmp;
        } while (Math.abs(x_next - x_curr) > e);


        System.out.println("Method time: " + (System.nanoTime() - before));
        return x_next;
    }

    public static double function(double x) {
        return Math.pow(x, 3) - 7 * Math.pow(x, 2) + 36;
    }
}