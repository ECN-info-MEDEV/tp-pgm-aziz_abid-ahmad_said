package org.ecn;

import java.io.*;
import java.util.Scanner;

public class PgmReader {

    public static PgmDataImage readImage(File imgFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(imgFile);
        Scanner scan = new Scanner(fileInputStream);
        // Discard the magic number
        scan.nextLine();
        // Discard the comment line
        scan.nextLine();
        // Read pic width, height and max value
        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();
        int maxvalue = scan.nextInt();

        fileInputStream.close();

        // Now parse the file as binary data
        fileInputStream = new FileInputStream(imgFile);
        Scanner dis = new Scanner(fileInputStream);

        // look for 4 lines (i.e.: the header) and discard them
        int numnewlines = 4;
        while (numnewlines > 0) {
            dis.nextLine();
            numnewlines--;
        }

        // read the image data
        int[][] data2D = new int[picHeight][picWidth];
        for (int row = 0; row < picHeight; row++) {
            for (int col = 0; col < picWidth; col++) {
                data2D[row][col] = dis.nextInt();
            }
        }
        PgmDataImage pgmDataImage = new PgmDataImage();
        pgmDataImage.setWidth(picWidth);
        pgmDataImage.setHeight(picHeight);
        pgmDataImage.setData(data2D);
        return pgmDataImage;
    }
}
