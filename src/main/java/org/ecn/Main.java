package org.ecn;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fichierPgm = "/img/brain.pgm";
        PgmDataImage pgmDataImage = null;
        try {
            File ficherPath = new File(ResourcesHelper.getResourceAsURL(fichierPgm).toURI());
            pgmDataImage = PgmReader.readImage(ficherPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to read image !");
            System.exit(1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        DisplayImageInAwt displayImageInAwt = new DisplayImageInAwt(fichierPgm, pgmDataImage);
    }
}