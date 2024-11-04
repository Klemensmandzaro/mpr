package pl.edu.pjatk.zaj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.zaj2.exception.ChangeObjectWithNullValuesException;
import pl.edu.pjatk.zaj2.exception.IdentyfierAlreadyExistException;
import pl.edu.pjatk.zaj2.exception.ResorceNotExistException;
import pl.edu.pjatk.zaj2.repository.MyRestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {
    private MyRestRepository repository;
    @Autowired
    private LetterService letterService;



    public MyRestService(MyRestRepository repository, LetterService letterService) {
        this.repository = repository;
        repository.save(new Pies("KArol", "szary"));
        repository.save(new Kot("Stefan", "rudy"));
        this.letterService=letterService;
    }

    public List<Zwierze> findAll() {
        return this.repository.findAll();
    }

    public void add(Zwierze zw) {

        zw.setIdentyfikator();
        if (zw.identyfikator==this.repository.findByIdentyfikator(zw.getIdentyfikator()).getIdentyfikator()) {
            throw new IdentyfierAlreadyExistException();
        }
        this.repository.save(zw);

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
        if (name.isEmpty() || color.isEmpty()) {
            throw new ChangeObjectWithNullValuesException();
        }
        Optional<Zwierze> zwierze = this.repository.findById(id);
        if (zwierze.isEmpty())
        {
            throw new ResorceNotExistException();
        }


            Zwierze zwi = zwierze.get();
            zwi.setColor(color);
            zwi.setIdentyfikator();
            this.repository.save(zwi);



    }

    public List<Zwierze> getByName(String name) {
        return this.repository.findByName(name);
    }

    public Zwierze getById(Long id) {
        Optional<Zwierze> zw = this.repository.findById(id);
        if (zw.isEmpty())
        {
            throw new ResorceNotExistException();
        }
        return zw.get();
    }

    public List<Zwierze> findAlllower() {
        for (Zwierze zw : this.repository.findAll()) {
            zw.setName(letterService.lower(zw.getName()));
            zw.setColor(letterService.lower(zw.getColor()));
            zw.setIdentyfikator();
            repository.save(zw);

        }
        return this.repository.findAll();
    }

    public void addupper(Zwierze zw) {
        zw.setName(letterService.upper(zw.getName()));
        zw.setColor(letterService.upper(zw.getColor()));
        zw.setIdentyfikator();
        if (zw.getIdentyfikator()==this.repository.findByIdentyfikator(zw.getIdentyfikator()).getIdentyfikator()) {
            throw new IdentyfierAlreadyExistException();
        }
        this.repository.save(zw);

    }
}
