package org.ecn;
import java.io.*;

/**
 * classe pour lire le fichier baboon.pgm
 */
public class FileReader {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream("src/main/resources/org.ecn/img/baboon.pgm");
        boolean wasComment = false;
        byte b;
        int [][] data;

        while ((b = (byte) f.read()) != -1) {
            if (b == '\n') {
                System.out.println(" ");
                wasComment = false;
                continue;
            }
            if (b == '#') {
                System.out.println("#");
                wasComment = true;
                continue;
            }
            if (wasComment) {
                continue;
            }
            if (Character.isWhitespace(b)) {
                System.out.print(" ");
                continue;
            }
            System.out.print(b + " ");
        }
    }
}
