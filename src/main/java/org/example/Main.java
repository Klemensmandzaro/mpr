package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();


        for (int i = 0; i < 10; i++) {
            document.addPage(new PDPage());
            if (i==0)
            {
                PDPageContentStream kontent = new PDPageContentStream(document, document.getPage(0));
                kontent.beginText();
                kontent.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 10);
                kontent.newLineAtOffset(20,750);
                kontent.showText("Hello World!!!");
                kontent.endText();
                kontent.close();
            }
            if (i==9)
            {
                PDPageContentStream kontent = new PDPageContentStream(document, document.getPage(9));
                kontent.beginText();
                kontent.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 10);
                kontent.newLineAtOffset(20,750);
                kontent.showText("Goodbye World!!!");
                kontent.endText();
                kontent.close();
            }
        }
        document.save("C:\\Users\\s30277\\Desktop\\1.pdf");
        document.close();

    }
}