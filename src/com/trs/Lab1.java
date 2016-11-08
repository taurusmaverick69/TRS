package com.trs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Lab1 {

    private static void singleThread(BufferedImage image, WritableRaster raster) throws IOException {
        editImage(image, raster);
        ImageIO.write(image, "JPG", new File("outputSingleThread.jpg"));
    }

    private static void multipleThreads(Thread[] threads, BufferedImage image, WritableRaster raster) throws IOException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                editImage(image, raster);
            });
            threads[i].start();
        }
        ImageIO.write(image, "JPG", new File("outputMultiThread.jpg"));
    }

    private static void editImage(BufferedImage image, WritableRaster raster) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++)
                try {
                    Double d[] = getMatrix(raster, x, y);
                    raster.setPixel(x, y, new double[]{d[0], d[1], d[2]});
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
        }
        image.setData(raster);
    }

    private static synchronized Double[] getMatrix(WritableRaster raster, int x, int y) {
        ArrayList<Double[]> list = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                double[] temp = new double[3];
                raster.getPixel(i, j, temp);
                list.add(new Double[]{temp[0], temp[1], temp[2]});
            }
        }
        list.sort((o1, o2) -> (int) (((o1[0] + o1[1] + o1[2]) / 3) - ((o2[0] + o2[1] + o2[2]) / 3)));
        return list.get(4);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("input.jpg"));
        WritableRaster raster = image.getRaster();

        long before = System.currentTimeMillis();
        singleThread(image, raster);
        System.out.println("Single Thread " + (System.currentTimeMillis() - before));


        before = System.currentTimeMillis();
        multipleThreads(new Thread[4], image, raster);
        System.out.println("Multiple Threads " + (System.currentTimeMillis() - before));
    }

}
