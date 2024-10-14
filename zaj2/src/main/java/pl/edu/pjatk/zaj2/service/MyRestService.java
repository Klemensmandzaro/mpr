package pl.edu.pjatk.zaj2.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.zaj2.repository.MyRestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {
    private MyRestRepository repository;

    public MyRestService(MyRestRepository repository) {
        this.repository = repository;
        repository.save(new Pies("KArol", "szary"));
        repository.save(new Kot("Stefan", "rudy"));
    }

    public List<Zwierze> findAll() {
        return this.repository.findAll();
    }

    public void add(Zwierze zw) {
        this.repository.save(new Zwierze(zw.name, zw.color));
    }

    public void remove(String name) {
        List<Zwierze> zwierze = this.repository.findByName(name);
        this.repository.deleteAll(zwierze);
    }

    public List<Zwierze> getByColor(String color) {
        return this.repository.findByColor(color);
    }

    public void zmien(Zwierze zw) {
        Long id = zw.getId();
        String name = zw.getName();
        String color = zw.getColor();
        Optional<Zwierze> zwierze = this.repository.findById(id);
        if (zwierze.isPresent()) {

            Zwierze zwi = zwierze.get();
            zwi.setColor(color);
            zwi.setIdentyfikator();
            this.repository.save(zwi);

        }
        else {
            System.out.println("Podaj dobre id");
        }

    }

    public List<Zwierze> getByName(String name) {
        return this.repository.findByName(name);
    }

    public Optional<Zwierze> getById(Long id) {
        return this.repository.findById(id);
    }
}
