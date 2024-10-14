package pl.edu.pjatk.zaj2.service;

import jakarta.persistence.Entity;

@Entity
public class Kot extends Zwierze {
    public Kot(String name, String color) {
        super(name, color);
    }


    public Kot() {

    }
}
