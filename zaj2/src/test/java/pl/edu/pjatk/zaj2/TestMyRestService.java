package pl.edu.pjatk.zaj2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.zaj2.repository.MyRestRepository;
import pl.edu.pjatk.zaj2.service.LetterService;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestMyRestService {
    MyRestRepository myRestRepository;
    LetterService letterService;
    MyRestService myRestService;
    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        myRestRepository = Mockito.mock(MyRestRepository.class);
        letterService= Mockito.mock(LetterService.class);
        myRestService = new MyRestService(myRestRepository, letterService);
    }

    @Test
    public void addChangesLettersToUpperCase(){
        Zwierze zw = new Zwierze("Kazik", "Szary");

        when(letterService.upper("Kazik")).thenReturn("KAZIK");
        when(letterService.upper("Szary")).thenReturn("SZARY");
        myRestService.addupper(zw);
        verify(letterService, times(2)).upper(any());
    }

    @Test
    public void addChangesLettersToLowerCase(){ //poprawic bo nie dziala
        when(myRestRepository.findAll()).thenReturn(List.of(new Zwierze("Kazik", "Szary"), new Zwierze("Kazik", "Czarny")));
        when(letterService.lower("Kazik")).thenReturn("Kazik");
        when(letterService.lower("Szary")).thenReturn("Szary");
        when(letterService.lower("Czarny")).thenReturn("Czarny");
        myRestService.findAlllower();
        verify(letterService, times(4)).lower(any());
    }


    @Test
    public void identyficatorHasExpectedValue(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));

        myRestService.add(zw);
        int powinno = 1042;
        verify(zw, times(1)).setIdentyfikator();
        assertEquals(powinno, zw.getIdentyfikator());

    }


}
