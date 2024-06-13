package com.nb.tcgscanner.card;

import com.nb.tcgscanner.ProportionalBoundingBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CardType {
    POKEMON(
            new ProportionalBoundingBox("name", 0.181, 0.036, 0.456, 0.051),
            new ProportionalBoundingBox("hp", 0.746, 0.026, 0.13, 0.068),
            new ProportionalBoundingBox("attacks", 0.07,0.562,0.,0.342)
    ),
    ENERGY(
    ),
    TRAINER(
    ),
    STADIUM(

    );

    private final List<ProportionalBoundingBox> proportionalBoundingBoxes;

    CardType(ProportionalBoundingBox... proportionalBoundingBoxes) {
        this.proportionalBoundingBoxes = Arrays.asList(proportionalBoundingBoxes);
    }

    public List<ProportionalBoundingBox> getProportionalBoundingBoxes() {
        return proportionalBoundingBoxes;
    }

    public List<Rectangle> getBoundingBoxes(int imageWidth, int imageHeight) {
        List<Rectangle> rectangles = new ArrayList<>();
        for (ProportionalBoundingBox box : proportionalBoundingBoxes) {
            rectangles.add(box.toRectangle(imageWidth, imageHeight));
        }

        return rectangles;
    }
}
