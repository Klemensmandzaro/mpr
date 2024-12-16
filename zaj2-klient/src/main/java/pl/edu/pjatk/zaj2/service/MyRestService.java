package pl.edu.pjatk.zaj2.service;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.edu.pjatk.zaj2.exception.ChangeObjectWithNullValuesException;
import pl.edu.pjatk.zaj2.exception.IdentyfierAlreadyExistException;
import pl.edu.pjatk.zaj2.exception.ResorceNotExistException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {
    @Autowired
    private RestClient restClient;




    public MyRestService(RestClient restClient) {
        this.restClient = restClient;
    }



    public List<Zwierze> findAll() {
        List<Zwierze> zwierzeList = restClient.get()
                .uri("http://localhost:8081/getall")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return zwierzeList;
    }

    public void add(Zwierze zw) {
        ResponseEntity<Void> responseEntity = restClient.post()
                .uri("http://localhost:8081/addcos")
                .body(zw)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();

    }

    public List<Zwierze> getByColor(String color) {
        List<Zwierze> zwierzeList = restClient.get()
                .uri("http://localhost:8081/getbycolor/"+color)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return zwierzeList;
    }

    public void zmien(Zwierze zw) {
        ResponseEntity<Void> responseEntity = restClient.patch()
                .uri("http://localhost:8081/putcos")
                .body(zw)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }

    public List<Zwierze> getByName(String name) {
        List<Zwierze> zwierzeList = restClient.get()
                .uri("http://localhost:8081/zwierze/name/"+name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return zwierzeList;
    }

    public Zwierze getById(Long id) {
        Zwierze zwierze = restClient.get()
                .uri("http://localhost:8081/zwierze/id/"+id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return zwierze;
    }

    public List<Zwierze> findAlllower() {
        List<Zwierze> zwierzeList = restClient.get()
                .uri("http://localhost:8081/getalllower")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return zwierzeList;
    }

    public void addupper(Zwierze zw) {
        ResponseEntity<Void> responseEntity = restClient.post()
                .uri("http://localhost:8081/addcosupper")
                .body(zw)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();

    }

    public byte[] zrobPdf(Long id) throws IOException {
        MyRestService myRestService = new MyRestService(restClient);
        Zwierze zw = restClient.get()
                .uri("http://localhost:8081/zwierze/id/"+id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (zw.getId() != null) {


            PDDocument document = new PDDocument();
            document.addPage(new PDPage());
            PDPageContentStream kontent = new PDPageContentStream(document, document.getPage(0));
            kontent.beginText();
            kontent.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 10);
            kontent.newLineAtOffset(20, 750);
            kontent.setLeading(14.5f);
            kontent.showText("id: " + zw.getId());
            kontent.newLine();
            kontent.showText("color: " + zw.getColor());
            kontent.newLine();
            kontent.showText("imie: " + zw.getName());
            kontent.newLine();
            kontent.showText("identyfikator: " + zw.getIdentyfikator());
            kontent.endText();
            kontent.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();
            System.out.println(Arrays.toString(baos.toByteArray()));
            return baos.toByteArray();
        } else {
            throw new ResorceNotExistException();
        }
    }

    public void removeById(Long id)
    {
        ResponseEntity<Void> responseEntity = restClient.post()
                .uri("http://localhost:8081/delete/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }


}
