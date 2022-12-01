package org.ecn;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatchi thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
//                displaySingleImageByName("/img/baboon.pgm").createAndShowGUI();
                for (DisplayImageInAwt displayImageInAwt : displayAllImageInImg()) {
                    displayImageInAwt.createAndShowGUI();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }


    public static List<DisplayImageInAwt> displayAllImageInImg() throws URISyntaxException {
        File ficherPath = new File(ResourcesHelper.getResourceAsURL("/img").toURI());
        List<DisplayImageInAwt> displayImageInAwts = new ArrayList<>();
        for (File file : Objects.requireNonNull(ficherPath.listFiles())) {
            displayImageInAwts.add(displaySingleImage(file));
        }
        return displayImageInAwts;
    }

    public static DisplayImageInAwt displaySingleImageByName(String imageName) throws URISyntaxException {
        File ficherPath = new File(ResourcesHelper.getResourceAsURL(imageName).toURI());
        return displaySingleImage(ficherPath);
    }

    public static DisplayImageInAwt displaySingleImage(File imgFile) {
        PgmDataImage pgmDataImage = null;
        try {
            pgmDataImage = PgmReader.readImage(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to read image !" + imgFile);
            return null;
        }
        return new DisplayImageInAwt(imgFile.getName().replaceAll(".pgm",""), pgmDataImage);
    }
}