package org.ecn;

import lombok.Data;

@Data
public class PgmDataImage {
    int width, height;
    int[][] data;

    public int [] getPixels() {
        // fabrication des pixels gris au format usuel AWT : ColorModel.RGBdefault
        int[] pixels = new int[getWidth() * getHeight()];
        int piIndex = 0;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                pixels[piIndex++] = 0xFF000000 + data[i][j] * 0x010101; // réplique l'octet 3 fois
            }
        }
        return pixels;
    }
}
