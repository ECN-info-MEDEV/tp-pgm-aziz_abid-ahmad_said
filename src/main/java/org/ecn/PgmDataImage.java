package org.ecn;

import lombok.Data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Data
public class PgmDataImage {
    private int width, height, maxValue;
    private int[][] data;

    public int[] getPixels() {
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

    public PgmDataImage aggrandirWidth(double xRatio) {
        PgmDataImage resizedImage = new PgmDataImage();
        resizedImage.height = height;
        resizedImage.width = (int) (width * xRatio);
        resizedImage.maxValue = maxValue;
        resizedImage.data = new int[resizedImage.height][resizedImage.width];
        double widthToAdd = resizedImage.width - width;
        int addPixelWidthEvery = (int) (width / widthToAdd);
        for (int i = 0; i < getHeight(); i++) {
            int widthIndex = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (widthIndex >= resizedImage.width)
                    break;
                if (j % addPixelWidthEvery == 0) {
                    resizedImage.data[i][widthIndex++] = data[i][j];
                }
                if (widthIndex < resizedImage.width)
                    resizedImage.data[i][widthIndex++] = data[i][j];
            }
            while (widthIndex != resizedImage.width) {
                resizedImage.data[i][widthIndex++] = data[i][width - 1];
            }
        }
        return resizedImage;
    }

    public PgmDataImage aggrandirHeight(double yRatio) {
        PgmDataImage resizedImage = new PgmDataImage();
        resizedImage.height = (int) (height * yRatio);
        resizedImage.width = width;
        resizedImage.maxValue = maxValue;
        resizedImage.data = new int[resizedImage.height][resizedImage.width];
        double heightToAdd = resizedImage.height - height;
        int addRowPixelHeightEvery = (int) (height / heightToAdd);
        int rowIndex = 0;
        for (int i = 0; i < getHeight(); i++) {
            if (rowIndex >= resizedImage.height) break;
            System.arraycopy(data[i], 0, resizedImage.data[rowIndex++], 0, getWidth());
            if (rowIndex % addRowPixelHeightEvery == 0 && rowIndex < resizedImage.height) {
                System.arraycopy(data[i], 0, resizedImage.data[rowIndex++], 0, getWidth());
            }
        }
        return resizedImage;
    }

    public void saveToFile(OutputStream fileOutputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        StringBuilder headers = new StringBuilder();
        headers.append("P2\n");
        headers.append("# \n");
        headers.append(width).append("  ").append(height).append("\n");
        headers.append(maxValue).append("\n");
        bufferedWriter.write(headers.toString());

        int maxCharacterPerLine = 70;
        for (int i = 0; i < getHeight(); i++) {
            StringBuilder line = new StringBuilder();
            boolean isLineAdded = false;
            for (int j = 0; j < getWidth(); j++) {
                String value = String.valueOf(data[i][j]);
                if (line.length() + value.length()<= maxCharacterPerLine) {
                    line.append(value);
                    isLineAdded = false;
                }
                // add space, either 2 spaced either jump a line
                if (j+1 < getWidth() && line.length() + 2 + String.valueOf(data[i][j+1]).length()<= maxCharacterPerLine) {
                    line.append("  ");
                } else {
                    line.append("\n");
                    bufferedWriter.write(line.toString());
                    isLineAdded = true;
                    line = new StringBuilder();
                }
            }
            if (!isLineAdded) {
                bufferedWriter.write(line.toString());
            }
            bufferedWriter.write("\n");
        }

        bufferedWriter.flush();
        writer.close();
    }
}
