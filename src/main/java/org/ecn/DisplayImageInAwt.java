package org.ecn;

import javax.swing.*;
import java.awt.*;
import java.awt.image.MemoryImageSource;

public class DisplayImageInAwt extends JFrame {

    // GUI elements
    FlowLayout experimentLayout = new FlowLayout();

    JButton widthResize = new JButton("Agrandir Width by 1.1");
    JButton heightResize = new JButton("Agrandir Height by 1.1");


    // data
    PgmDataImage pgmDataImage;

    // constructeur pour un argument Pixmap
    public DisplayImageInAwt(String name, PgmDataImage p) {
        super(name);
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

        compsToExperiment.add(generateImageFrame());

        //Process the Apply component orientation button press
        widthResize.addActionListener(e -> {
            pgmDataImage = pgmDataImage.aggrandirWidth(1.1);
            compsToExperiment.removeAll();
            compsToExperiment.add(generateImageFrame());
        });

        heightResize.addActionListener(e -> {
            pgmDataImage = pgmDataImage.aggrandirHeight(1.1);
            compsToExperiment.removeAll();
            compsToExperiment.add(generateImageFrame());
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
