package pl.edu.pjatk.zaj2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.zaj2.services.MyRestService;
import pl.edu.pjatk.zaj2.services.Zwierze;

import java.util.List;

@RestController

public class MyRestController {
    private MyRestService myRestService;
    @Autowired
    public MyRestController(MyRestService myRestService){
        this.myRestService=myRestService;
    }
    @GetMapping("/getall")
    public List<Zwierze> Zwierze(){
        return myRestService.getZwierzeta();
    }

    @PostMapping("/addcos")
    public void addCos(@RequestBody Zwierze zw){
        myRestService.add(zw);
    }

    @DeleteMapping("/deletezw/{name}")
    public void deleteZw(@PathVariable String name){
        myRestService.remove(name);
    }

    @GetMapping("/getbycolor/{color}")
    public List<Zwierze> getByColor(@PathVariable String color){
        return myRestService.getColor(color);
    }

    @PutMapping("/putcos")
    public void putCos(@RequestBody Zwierze zw){
        myRestService.zmien(zw);
    }

}
