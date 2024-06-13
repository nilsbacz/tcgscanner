package com.nb.tcgscanner.service;

import com.nb.tcgscanner.ProportionalBoundingBox;
import com.nb.tcgscanner.card.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardTextService {

    private final OcrService ocrService;

    //todo depending on complexity either make
    // a private final CardTypeDeterminer cardTypeDeterminer; or determine cardtype in here

    @Autowired
    public CardTextService(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    public Map<String, String> processCard(MultipartFile file) throws IOException {
        //extract image resolution
        BufferedImage image = ImageIO.read(file.getInputStream());
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        //TEMPORARY: learing to create matrices

        int pixelBlau = image.getRGB(499,181);
        int pixelBlack = image.getRGB(593,208);
        int pixelSun = image.getRGB(750,1159);

        Color colorBlue = new Color(pixelBlau,true);
        Color colorBlack = new Color(pixelBlack,true);
        Color colorSun = new Color(pixelSun,true);

        //DEBUG: monitor heap size
//        Runtime runtime = Runtime.getRuntime();
//        long maxMemory = runtime.maxMemory();
//        long totalMemory = runtime.totalMemory();
//        long freeMemory = runtime.freeMemory();
//
//        System.out.println("Max Memory: " + maxMemory / (1024 * 1024) + " MB");
//        System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
//        System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");

        // Save the file to a temporary location
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        // todo Determine the card type using either the CardTypeDeterminer class or function here
        CardType cardType = CardType.POKEMON;

        // Get bounding boxes
        List<ProportionalBoundingBox> proportionalBoundingBoxes = cardType.getProportionalBoundingBoxes();
        Map<String, String> cardTexts = new HashMap<>();

        for (ProportionalBoundingBox box : proportionalBoundingBoxes) {
            Rectangle rectangle = box.toRectangle(imgWidth, imgHeight);
            String text = ocrService.extractTextFromImage(tempFile, rectangle);
            cardTexts.put(box.getLabel(), text);
        }

        // Clean up temporary file
        if (!tempFile.delete()) {
            throw new IOException("the temporary file could not be deleted. There might be a memory leak.");
        }

        return cardTexts;
    }
}
