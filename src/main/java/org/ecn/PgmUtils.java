package org.ecn;
import lombok.Data;
import java.util.HashMap;

public class PgmUtils {

    public static int[] pixelMapHistogram(PgmDataImage pgmDataImage) {
        int[] pixels = new int[1000];
        for (int i = 0; i < pgmDataImage.getHeight(); i++) {
            for (int j = 0; j < pgmDataImage.getWidth(); j++) {
                pixels[ pgmDataImage.data[i][j]] += 1;
            }
        }

        return pixels;
    }

}
