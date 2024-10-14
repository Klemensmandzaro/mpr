package pl.edu.pjatk.zaj2.service;

import jakarta.persistence.Entity;

@Entity
public class Pies extends Zwierze{

    public Pies(String name, String color) {
        super(name, color);

    }

    public Pies() {

    }
}
