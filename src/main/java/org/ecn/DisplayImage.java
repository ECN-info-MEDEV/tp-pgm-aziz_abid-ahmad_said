package org.ecn;

import java.awt.*;

class DisplayImage extends Canvas {

    Image img;

    public DisplayImage(Image img) {
        this.img = img;
        setSize(img.getWidth(this), img.getHeight(this));
    }

    public void paint(Graphics gr) {
        gr.drawImage(img, 0, 0, this);
    }

}