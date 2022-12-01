package org.ecn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.MemoryImageSource;

public class DisplayImageInAwt extends JFrame {

    // GUI elements
    FlowLayout experimentLayout = new FlowLayout();

    JButton applyButton = new JButton("Agrandir * 1.1");


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
        controls.add(applyButton);

        // construit une image avec ces pixels
        MemoryImageSource source = new MemoryImageSource(pgmDataImage.width, pgmDataImage.height, pgmDataImage.getPixels(), 0, pgmDataImage.width);
        Image img = Toolkit.getDefaultToolkit().createImage(source);
        compsToExperiment.add(new DisplayImage(img));

        //Process the Apply component orientation button press
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("i must agrandir l'image");
                compsToExperiment.removeAll();
                MemoryImageSource source = new MemoryImageSource(pgmDataImage.width, pgmDataImage.height, pgmDataImage.getPixels(), 0, pgmDataImage.width);
                Image img = Toolkit.getDefaultToolkit().createImage(source);
                compsToExperiment.add(new DisplayImage(img));

                compsToExperiment.validate();
                compsToExperiment.repaint();
            }
        });
        pane.add(compsToExperiment, BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
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
