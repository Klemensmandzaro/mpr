package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        FileUtils.copyURLToFile(
                new URL("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                new File("dummy.pdf"),
                10000,
                10000);


    File file = new File("dummy.pdf");
    PDDocument doc = Loader.loadPDF(file);
    PDPage strona = doc.getPage(0);
    PDImageXObject obraz = PDImageXObject.createFromFile("C:\\Users\\s30277\\Desktop\\Capybara02.jpg", doc);
    PDPageContentStream kontent = new PDPageContentStream(doc, strona);
    kontent.drawImage(obraz, 70, 250);
    kontent.close();
    doc.save("C:\\Users\\s30277\\Desktop\\kapibara.pdf");
    doc.close();
    }
}