package pl.edu.pjatk.zaj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

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
    public List<Zwierze> Zwierze(){
        return myRestService.findAll();
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
        return myRestService.getByColor(color);
    }

    @PatchMapping("/putcos")
    public void putCos(@RequestBody Zwierze zw){
        myRestService.zmien(zw);
    }

    @GetMapping("zwierze/name/{name}")
    public List<Zwierze> findByName(@PathVariable String name){
        return this.myRestService.getByName(name);
    }

    @GetMapping("zwierze/id/{id}")
    public Optional<Zwierze> findById(@PathVariable Long id){
        return this.myRestService.getById(id);
    }

    @GetMapping("/getalllower")
    public List<Zwierze> Zwierzelower(){
        return myRestService.findAlllower();
    }

    @PostMapping("/addcosupper")
    public void addCosupper(@RequestBody Zwierze zw){
        myRestService.addupper(zw);
    }
}
