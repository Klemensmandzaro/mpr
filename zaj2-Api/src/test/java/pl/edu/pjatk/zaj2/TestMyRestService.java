package pl.edu.pjatk.zaj2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.zaj2.exception.ChangeObjectWithNullValuesException;
import pl.edu.pjatk.zaj2.exception.IdentyfierAlreadyExistException;
import pl.edu.pjatk.zaj2.exception.ResorceNotExistException;
import pl.edu.pjatk.zaj2.repository.MyRestRepository;
import pl.edu.pjatk.zaj2.service.LetterService;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void addChangesLettersToUpperCaseWhenAlreadyExist(){
        Zwierze zw = new Zwierze("Kazik", "Szary");
        zw.setIdentyfikator();

        when(letterService.upper("Kazik")).thenReturn("KAZIK");
        when(letterService.upper("Szary")).thenReturn("SZARY");
        when(myRestRepository.findByIdentyfikator(787)).thenReturn(zw);
        assertThrows(IdentyfierAlreadyExistException.class, () -> {
            myRestService.addupper(zw);
        });

        verify(letterService, times(2)).upper(any());
    }

    @Test
    public void addChangesLettersToUpperCaseWhenDontExist(){
        Zwierze zw = new Zwierze("Kazik", "Szary");
        zw.setIdentyfikator();
        Zwierze zw2 = new Zwierze("Kazik", "Czarny");
        zw2.setIdentyfikator();

        when(letterService.upper("Kazik")).thenReturn("KAZIK");
        when(letterService.upper("Szary")).thenReturn("SZARY");
        when(myRestRepository.findByIdentyfikator(787)).thenReturn(zw2);
        myRestService.addupper(zw);
        verify(letterService, times(2)).upper(any());
    }

    @Test
    public void addChangesLettersToLowerCase(){
        when(myRestRepository.findAll()).thenReturn(List.of(new Zwierze("Kazik", "Szary"), new Zwierze("Kazik", "Czarny")));
        when(letterService.lower("Kazik")).thenReturn("Kazik");
        when(letterService.lower("Szary")).thenReturn("Szary");
        when(letterService.lower("Czarny")).thenReturn("Czarny");
        myRestService.findAlllower();
        verify(letterService, times(4)).lower(any());
    }


    @Test
    public void identyficatorHasExpectedValueButAlreadyExists(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        zw.setIdentyfikator();
        when(myRestRepository.findByIdentyfikator(1042)).thenReturn(zw);
        assertThrows(IdentyfierAlreadyExistException.class, () -> {
            myRestService.add(zw);
        });
        int powinno = 1042;
        verify(zw, times(2)).setIdentyfikator();
        assertEquals(powinno, zw.getIdentyfikator());

    }

    @Test
    public void identyficatorHasExpectedValueButDontExists(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        zw.setIdentyfikator();
        Zwierze zw2 = new Zwierze("Kazik", "Czarny");
        zw2.setIdentyfikator();
        when(myRestRepository.findByIdentyfikator(1042)).thenReturn(zw2);

            myRestService.add(zw);

        int powinno = 1042;
        verify(zw, times(2)).setIdentyfikator();
        assertEquals(powinno, zw.getIdentyfikator());

    }

    @Test
    public void removeObjectExist(){
        Zwierze zw = new Zwierze("Kazik", "Szary");
        List<Zwierze> zwierze = List.of(zw);
        when(myRestRepository.findByName("Kazik")).thenReturn(zwierze);
        myRestService.remove("Kazik");
        verify(myRestRepository, times(1)).deleteAll(zwierze);
    }

    @Test
    public void removeObjectNotExist(){
        List<Zwierze> zwierze = List.of();
        when(myRestRepository.findByName("Kazik")).thenReturn(zwierze);
        assertThrows(ResorceNotExistException.class, () ->
                myRestService.remove("Kazik"));
    }

    @Test
    public void changedObjectNotExistButHasGoodValues(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        List<Zwierze> zwierze = List.of();
        when(zw.getName()).thenReturn("Karol");
        when(zw.getId()).thenReturn(1L);
        when(zw.getColor()).thenReturn("Szary");
        when(myRestRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResorceNotExistException.class, () ->
                myRestService.zmien(zw));
    }

    @Test
    public void changedObjectExistButHasWrongValues(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        List<Zwierze> zwierze = List.of();
        when(zw.getName()).thenReturn("");
        when(zw.getId()).thenReturn(1L);
        when(zw.getColor()).thenReturn("Szary");
        assertThrows(ChangeObjectWithNullValuesException.class, () ->
                myRestService.zmien(zw));
    }

    @Test
    public void changedObjectNotExistAndHasWrongValues(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        List<Zwierze> zwierze = List.of();
        when(zw.getName()).thenReturn("");
        when(zw.getId()).thenReturn(1L);
        when(zw.getColor()).thenReturn("Szary");
        when(myRestRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ChangeObjectWithNullValuesException.class, () ->
                myRestService.zmien(zw));
//        assertThrows(ResorceNotExistException.class, () ->
//                myRestService.zmien(zw));
        //zapytać o to
    }

    @Test
    public void changedObjectExistAndHasGoodValues(){
        Zwierze zw = Mockito.spy(new Zwierze("Karol", "Szary"));
        when(zw.getName()).thenReturn("Karol");
        when(zw.getId()).thenReturn(1L);
        when(zw.getColor()).thenReturn("Szary");
        when(myRestRepository.findById(1L)).thenReturn(Optional.of(zw));
        myRestService.zmien(zw);
        verify(myRestRepository, times(1)).save(zw);

    }

    @Test
    public void pdfgeneratesuccess() throws IOException {
        Zwierze zw = new Zwierze("Karol", "szary");
        zw.setId(1L);
        zw.setIdentyfikator();
        when(myRestRepository.findById(1L)).thenReturn(Optional.of(zw));
        byte[] pdfBytes = myRestService.zrobPdf(1L);
        verify(myRestRepository, times(2)).findById(1L);
        byte[] bity ={37, 80, 68, 70, 45, 49, 46, 54, 10, 37, -10, -28, -4, -33, 10, 49, 32, 48, 32, 111, 98, 106, 10, 60, 60, 10, 47, 84, 121, 112, 101, 32, 47, 67, 97, 116, 97, 108, 111, 103, 10, 47, 86, 101, 114, 115, 105, 111, 110, 32, 47, 49, 46, 54, 10, 47, 80, 97, 103, 101, 115, 32, 50, 32, 48, 32, 82, 10, 62, 62, 10, 101, 110, 100, 111, 98, 106, 10, 55, 32, 48, 32, 111, 98, 106, 10, 60, 60, 10, 47, 76, 101, 110, 103, 116, 104, 32, 49, 48, 48, 10, 47, 70, 105, 108, 116, 101, 114, 32, 47, 70, 108, 97, 116, 101, 68, 101, 99, 111, 100, 101, 10, 62, 62, 10, 115, 116, 114, 101, 97, 109, 13, 10, 120, -100, 115, 10, -31, -46, 119, 51, 84, 48, 52, 80, 8, 73, -29, 50, 50, 80, 48, 55, 5, -78, 82, -72, 12, 77, -12, 76, 21, 66, 124, -72, 52, 50, 83, -84, 20, 12, 53, 21, 66, -78, -72, 66, -76, -72, 52, -110, -13, 115, -14, -117, -84, 20, -118, -85, 18, -117, 42, -31, -126, -103, -71, -103, -87, 86, 10, -34, -119, 69, -7, 57, 8, -79, -108, -44, -68, -110, -54, -76, -52, -20, -60, 18, -112, 6, 67, 3, 115, 19, -80, -100, 107, 8, 23, 0, 77, 4, 30, 1, 13, 10, 101, 110, 100, 115, 116, 114, 101, 97, 109, 10, 101, 110, 100, 111, 98, 106, 10, 56, 32, 48, 32, 111, 98, 106, 10, 60, 60, 10, 47, 76, 101, 110, 103, 116, 104, 32, 49, 56, 57, 10, 47, 84, 121, 112, 101, 32, 47, 79, 98, 106, 83, 116, 109, 10, 47, 78, 32, 53, 10, 47, 70, 105, 108, 116, 101, 114, 32, 47, 70, 108, 97, 116, 101, 68, 101, 99, 111, 100, 101, 10, 47, 70, 105, 114, 115, 116, 32, 50, 55, 10, 62, 62, 10, 115, 116, 114, 101, 97, 109, 13, 10, 120, -100, 85, -114, -51, 10, -125, 64, 12, -124, 95, 101, 94, -96, -115, -21, 47, -126, 20, -86, -76, -105, 82, 40, 86, -24, 65, 60, 88, 93, 100, 15, -18, 22, 87, -95, 125, -5, 70, -123, -2, 28, 18, -104, 47, -109, 100, 92, 56, -16, -32, -69, -16, 33, -68, 24, 1, 68, 16, 34, -124, -120, 4, -110, -124, -118, -41, 67, -126, 46, 117, 39, 45, -24, -92, 90, -117, -46, -29, -123, 28, 21, 40, 51, -109, 30, 33, -80, -37, -3, 59, 65, 103, -39, -86, 58, 53, 79, -108, -50, -42, -63, 92, -95, 112, -71, 71, -15, -36, -85, -39, 54, 72, -34, 117, -105, 83, -108, 75, 107, -90, -95, -31, 23, -2, 10, 50, -93, 71, -98, 91, 68, -117, 94, 31, 28, 25, 114, -68, 31, 32, 56, -25, 87, -82, 1, 22, 23, 93, -89, -5, -72, -56, 25, 10, 80, 90, 91, -71, 78, 10, -43, 75, -69, -55, 77, 95, 107, -48, 65, 55, -90, 85, -70, 3, -35, -108, -34, 107, -85, 62, -128, 79, -66, 1, -70, 36, 77, 100, 13, 10, 101, 110, 100, 115, 116, 114, 101, 97, 109, 10, 101, 110, 100, 111, 98, 106, 10, 57, 32, 48, 32, 111, 98, 106, 10, 60, 60, 10, 47, 76, 101, 110, 103, 116, 104, 32, 51, 51, 10, 47, 82, 111, 111, 116, 32, 49, 32, 48, 32, 82, 10, 47, 73, 68, 32, 91, 60, 54, 50, 69, 54, 55, 66, 56, 48, 70, 70, 51, 52, 49, 56, 56, 67, 68, 56, 51, 65, 53, 70, 48, 51, 66, 57, 70, 67, 50, 52, 67, 52, 62, 32, 60, 54, 50, 69, 54, 55, 66, 56, 48, 70, 70, 51, 52, 49, 56, 56, 67, 68, 56, 51, 65, 53, 70, 48, 51, 66, 57, 70, 67, 50, 52, 67, 52, 62, 93, 10, 47, 84, 121, 112, 101, 32, 47, 88, 82, 101, 102, 10, 47, 83, 105, 122, 101, 32, 49, 48, 10, 47, 73, 110, 100, 101, 120, 32, 91, 48, 32, 57, 93, 10, 47, 87, 32, 91, 49, 32, 49, 32, 49, 93, 10, 47, 70, 105, 108, 116, 101, 114, 32, 47, 70, 108, 97, 116, 101, 68, 101, 99, 111, 100, 101, 10, 62, 62, 10, 115, 116, 114, 101, 97, 109, 13, 10, 120, -100, 99, 96, -8, -49, -56, -49, -64, -60, 1, 68, -116, 76, 28, 76, 76, 28, -52, 76, 28, 44, -116, 126, 12, -116, 127, 24, 0, 33, 36, 2, -104, 13, 10, 101, 110, 100, 115, 116, 114, 101, 97, 109, 10, 101, 110, 100, 111, 98, 106, 10, 115, 116, 97, 114, 116, 120, 114, 101, 102, 10, 53, 52, 52, 10, 37, 37, 69, 79, 70, 10};
        assertArrayEquals(pdfBytes,bity);
        //naprawimy na nastepnych zajeciach
        //ogarnac to testami ui na zajeciach pozniejszych
    }

    @Test
    public void testZrobPdf_ResourceNotFoundException() {
        Long id = 1L;
        when(myRestRepository.findById(id)).thenReturn(Optional.empty());
        ResorceNotExistException exception = assertThrows(ResorceNotExistException.class, () -> {
            myRestService.zrobPdf(id);
        });

        assertEquals("Podany zasób nie istnieje", exception.getMessage());
    }

    @Test
    public void removeByIdNotExist(){
        Long id = 1L;
        when(myRestRepository.findById(id)).thenReturn(Optional.empty());
        ResorceNotExistException exception = assertThrows(ResorceNotExistException.class, () -> {
            myRestService.removeById(id);
        });

        assertEquals("Podany zasób nie istnieje", exception.getMessage());
    }

    @Test
    public void removeByIdExist(){
        Zwierze zw = new Zwierze("Karol", "szary");
        zw.setId(1L);
        zw.setIdentyfikator();
        when(myRestRepository.findById(1L)).thenReturn(Optional.of(zw));
        myRestService.removeById(zw.getId());
        verify(myRestRepository, times(1)).deleteById(1L);


    }






    //dodac testy do zmian i delete(remove)
}
