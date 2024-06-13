package com.nb.tcgscanner.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;

@Service
public class OcrService {
    private int dpi;
    private String lang;

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String extractTextFromImage(File imageFile, Rectangle rect) {
        ITesseract tesseract = new Tesseract();

        // Set the path to the tessdata directory
        //TODO figure this out with env vars or smth
        tesseract.setDatapath("/home/nils/Documents/Projects/tcgscanner/src/main/resources/tessdata");
        tesseract.setLanguage(this.lang);
        tesseract.setVariable("user_defined_dpi", String.valueOf(this.dpi));

        try {
            return tesseract.doOCR(imageFile, rect);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}