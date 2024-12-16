package pl.edu.pjatk.zaj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController

public class MyRestController {
    private MyRestService myRestService;
    @Autowired
    public MyRestController(MyRestService myRestService){
        this.myRestService=myRestService;
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Zwierze>> Zwierze(){
        return new ResponseEntity<>(myRestService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addcos")
    public ResponseEntity<Void> addCos(@RequestBody Zwierze zw){
        myRestService.add(zw);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deletezw/{name}")
    public ResponseEntity<Void> deleteZw(@PathVariable String name){
        myRestService.remove(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbycolor/{color}")
    public ResponseEntity<List<Zwierze>> getByColor(@PathVariable String color){
        return new ResponseEntity<>(myRestService.getByColor(color), HttpStatus.OK);
    }

    @PatchMapping("/putcos")
    public ResponseEntity<Void> putCos(@RequestBody Zwierze zw){
        myRestService.zmien(zw);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("zwierze/name/{name}")
    public ResponseEntity<List<Zwierze>> findByName(@PathVariable String name){
        return new ResponseEntity<>(this.myRestService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("zwierze/id/{id}")
    public ResponseEntity<Zwierze> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.myRestService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getalllower")
    public ResponseEntity<List<Zwierze>> Zwierzelower(){
        return new ResponseEntity<>(myRestService.findAlllower(), HttpStatus.OK);
    }

    @PostMapping("/addcosupper")
    public ResponseEntity<Void> addCosupper(@RequestBody Zwierze zw){
        myRestService.addupper(zw);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<ByteArrayResource> getPdf(@PathVariable Long id) throws IOException {
        byte[] pdfBytes = myRestService.zrobPdf(id);

        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=example.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        myRestService.removeById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
