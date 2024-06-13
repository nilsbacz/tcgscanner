package com.nb.tcgscanner;

import java.awt.*;

public class ProportionalBoundingBox {
    private final double proportionalX;
    private final double proportionalY;
    private final double proportionalWidth;
    private final double proportionalHeight;
    private final String label;

    /**
     * Constructs a ProportionalBoundingBox with proportional values.
     * @throws IllegalArgumentException a proportion is not a value between 0 and 1
     */
    public ProportionalBoundingBox(
            String label,
            double proportionalX,
            double proportionalY,
            double proportionalWidth,
            double proportionalHeight) {

        this.label = label;
        this.proportionalX = validateProportion(proportionalX, "proportionalX");
        this.proportionalY = validateProportion(proportionalY, "proportionalY");
        this.proportionalWidth = validateProportion(proportionalWidth, "proportionalWidth");
        this.proportionalHeight = validateProportion(proportionalHeight, "proportionalHeight");
    }

    /**
     * Converts the proportional bounding box to a Rectangle based on image dimensions.
     */
    public Rectangle toRectangle(int imageWidth, int imageHeight) {
        int x = (int) (proportionalX * imageWidth);
        int y = (int) (proportionalY * imageHeight);
        int width = (int) (proportionalWidth * imageWidth);
        int height = (int) (proportionalHeight * imageHeight);
        return new Rectangle(x, y, width, height);
    }

    public String getLabel() {
        return label;
    }

    /**
     * @throws IllegalArgumentException value not between 0 and 1
     * @param value value of the proportion
     * @param proportionName name of the proportion
     */
    private double validateProportion(double value, String proportionName) {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException(proportionName + " must be between 0 and 1. Provided value: " + value);
        }
        return value;
    }
}
