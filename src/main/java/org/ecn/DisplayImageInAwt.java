package org.ecn;

import java.awt.*;
import java.awt.image.MemoryImageSource;

public class DisplayImageInAwt extends Frame {

    // constructeur pour un argument Pixmap
    public DisplayImageInAwt(String name, PgmDataImage p) {
        super(name);
        setLocation(50, 50);
        // construit une image avec ces pixels
        MemoryImageSource source = new MemoryImageSource(p.width, p.height, p.getPixels(), 0, p.width);
        Image img = Toolkit.getDefaultToolkit().createImage(source);
        add(new DisplayImage(img));
        pack();
        show();
    }
}
