package com.example.talktopdf.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class UploadFile {

    @PostMapping("/upload")
    public String uploadFile(@RequestBody String filePath){
        try {
            PDDocument document = PDDocument.load(new File(filePath));

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);

            document.close();
            log.info("Pdf content : {}", text);

            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading PDF file: " + e.getMessage();
        }
    }
}
