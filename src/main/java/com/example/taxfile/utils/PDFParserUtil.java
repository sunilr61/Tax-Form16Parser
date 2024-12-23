package com.example.taxfile.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class PDFParserUtil {

    public static String extractText(MultipartFile file) throws IOException {
        // Convert MultipartFile to a temporary file
        File tempFile = File.createTempFile("uploaded-", ".pdf");
        file.transferTo(tempFile);

        try (PDDocument document = PDDocument.load(tempFile)) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            } else {
                throw new IllegalArgumentException("PDF is encrypted and cannot be processed.");
            }
        } finally {
            tempFile.delete(); // Cleanup temporary file
        }
    }
}
