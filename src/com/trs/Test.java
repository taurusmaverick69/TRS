package com.trs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("1.jpg"));
        WritableRaster raster = image.getRaster();

        long before = System.currentTimeMillis();
        f2(image, raster);
        System.out.println(System.currentTimeMillis() - before);

        Thread[] thread = new Thread[4];
        before = System.currentTimeMillis();
        for (int i = 0; i < thread.length; i++) {
            final int finalI = i;
            thread[i] = new Thread(() -> {
                try {
                    fForThread(raster, image, finalI * raster.getWidth(), (finalI + 1) * raster.getWidth());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread[i].start();
        }
        ImageIO.write(image, "JPG", new File("3.jpg"));
        System.out.println(System.currentTimeMillis() - before);
    }

    private static void f2(BufferedImage image, WritableRaster r) throws IOException {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++)
                try {
                    Double d[] = makeMatrix(r, x, y);
                    r.setPixel(x, y, new double[]{d[0], d[1], d[2]});
                } catch (ArrayIndexOutOfBoundsException e) {
                }
        }

        image.setData(r);
        ImageIO.write(image, "JPG", new File("2.jpg"));
    }

    private static void fForThread(WritableRaster raster, BufferedImage image, int fromX, int toX) throws IOException {

        for (int x = fromX; x < toX; x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Double d[] = makeMatrix(raster, x, y);
                raster.setPixel(x, y, new double[]{d[0], d[1], d[2]});
            }
        }

        image.setData(raster);
    }

    private static Double[] makeMatrix(WritableRaster r, int x, int y) {
        ArrayList<Double[]> list = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                double[] temp = new double[3];
                r.getPixel(i, j, temp);
                list.add(new Double[]{temp[0], temp[1], temp[2]});
            }
        }
        list.sort((o1, o2) -> (int) (((o1[0] + o1[1] + o1[2]) / 3) - ((o2[0] + o2[1] + o2[2]) / 3)));
        return list.get(4);
    }
}
