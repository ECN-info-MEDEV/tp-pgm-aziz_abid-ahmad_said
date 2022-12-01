package org.ecn;

import javax.swing.*;
import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class DisplayImageInAwt extends JFrame {

    // GUI elements
    FlowLayout experimentLayout = new FlowLayout();

    JButton widthResize = new JButton("Agrandir Width by 1.1");

    double currentXRation = 1;
    JButton heightResize = new JButton("Agrandir Height by 1.1");
    double currentYRation = 1;
    JButton saveImageToFile = new JButton("Save To File");


    // data
    String fileName;
    PgmDataImage pgmDataImage;

    // constructeur pour un argument Pixmap
    public DisplayImageInAwt(String name, PgmDataImage p) {
        super(name);
        fileName = name;
        pgmDataImage = p;
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.TRAILING);
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.add(widthResize);
        controls.add(heightResize);
        controls.add(saveImageToFile);

        compsToExperiment.add(generateImageFrame());

        //Process the Apply component orientation button press
        widthResize.addActionListener(e -> {
            pgmDataImage = pgmDataImage.aggrandirWidth(1.1);
            currentXRation *= 1.1;
            compsToExperiment.removeAll();
            compsToExperiment.add(generateImageFrame());
        });

        heightResize.addActionListener(e -> {
            pgmDataImage = pgmDataImage.aggrandirHeight(1.1);
            currentYRation *= 1.1;
            compsToExperiment.removeAll();
            compsToExperiment.add(generateImageFrame());
        });
        saveImageToFile.addActionListener(e -> {
            try {
                pgmDataImage.saveToFile(Files.newOutputStream(new File(fileName+"_resized_"  + currentXRation + "_y" + currentYRation + ".pgm").toPath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pane.add(compsToExperiment, BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    public DisplayImage generateImageFrame() {
        // construit une image avec ces pixels
        MemoryImageSource source1 = new MemoryImageSource(pgmDataImage.getWidth(), pgmDataImage.getHeight(), pgmDataImage.getPixels(), 0, pgmDataImage.getWidth());
        Image img1 = Toolkit.getDefaultToolkit().createImage(source1);
        return new DisplayImage(img1);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        this.addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
    }
}
