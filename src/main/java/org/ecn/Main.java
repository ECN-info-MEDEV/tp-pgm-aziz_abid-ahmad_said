package org.ecn;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
//            displayAllImageInImg();
            displaySingleImageByName("/img/brain.pgm");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void displayAllImageInImg() throws URISyntaxException {
        File ficherPath = new File(ResourcesHelper.getResourceAsURL("/img").toURI());
        for (File file : Objects.requireNonNull(ficherPath.listFiles())) {
            displaySingleImage(file);
        }
    }

    public static void displaySingleImageByName(String imageName) throws URISyntaxException {
        String fichierPgm = "/img/brain.pgm";
        File ficherPath = new File(ResourcesHelper.getResourceAsURL(fichierPgm).toURI());
        displaySingleImage(ficherPath);
    }

    public static void displaySingleImage(File imgFile) {
        PgmDataImage pgmDataImage = null;
        try {
            pgmDataImage = PgmReader.readImage(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to read image !" + imgFile);
            return;
        }
        DisplayImageInAwt displayImageInAwt = new DisplayImageInAwt(imgFile.getName(), pgmDataImage);
    }
}