package pl.edu.pjatk.zaj2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;
import pl.edu.pjatk.zaj2.exception.ResorceNotExistException;
import pl.edu.pjatk.zaj2.service.LetterService;
import pl.edu.pjatk.zaj2.service.MyRestService;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RestClientTest
public class ServiceTest {
    private MyRestService service;
    MockServerRestClientCustomizer customizer = new MockServerRestClientCustomizer();
    RestClient.Builder builder = RestClient.builder();

    @BeforeEach
    public void setUp() {


        customizer.customize(builder);
        service = new MyRestService(builder.build());
    }

    @Test
    public void testFindAll_Zwierzeta() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getall"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "[{\"id\":\"null\",\"name\":\"Karol\",\"color\":\"szary\",\"identyfikator\":\"1074\"}]",
                        MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.findAll();
        Zwierze zw = new Zwierze("Karol", "szary");
        assertEquals(lista.getFirst().getId(), zw.getId());
        assertEquals(lista.getFirst().getName(), zw.getName());
        assertEquals(lista.getFirst().getColor(), zw.getColor());
        assertEquals(lista.getFirst().getIdentyfikator(), zw.getIdentyfikator());
    }

    @Test
    public void findAll_ReturnsEmptyList_WhenNoAnimalsExist() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getall"))
                .andRespond(MockRestResponseCreators.withSuccess("[]", MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.findAll();

        assertTrue(lista.isEmpty());
    }

    @Test
    public void findAll_ReturnsListOfAnimals_WhenAnimalsExist() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getall"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "[{\"id\":\"1\",\"name\":\"Karol\",\"color\":\"szary\",\"identyfikator\":\"1074\"}]",
                        MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.findAll();
        Zwierze zw = new Zwierze( "Karol", "szary");
        zw.setId(1L);

        assertEquals(1, lista.size());
        assertEquals(zw.getId(), lista.get(0).getId());
        assertEquals(zw.getName(), lista.get(0).getName());
        assertEquals(zw.getColor(), lista.get(0).getColor());
        assertEquals(zw.getIdentyfikator(), lista.get(0).getIdentyfikator());
    }

    @Test
    public void findAll_ThrowsException_WhenServerErrorOccurs() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getall"))
                .andRespond(MockRestResponseCreators.withServerError());

        assertThrows(RuntimeException.class, () -> service.findAll());
    }

    @Test
    public void add_AddsAnimalSuccessfully() {
        Zwierze zwierze = new Zwierze("Dog", "Brown");
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/addcos"))
                .andExpect(MockRestRequestMatchers.content().json("{\"name\":\"Dog\",\"color\":\"Brown\"}"))
                .andRespond(MockRestResponseCreators.withSuccess());

        service.add(zwierze);

        customizer.getServer().verify();
    }

    @Test
    public void getByColor_ReturnsAnimalsByColor() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getbycolor/Brown"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "[{\"id\":\"1\",\"name\":\"Dog\",\"color\":\"Brown\",\"identyfikator\":\"802\"}]",
                        MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.getByColor("Brown");
        Zwierze zw = new Zwierze("Dog", "Brown");
        zw.setId(1L);

        assertEquals(1, lista.size());
        assertEquals(zw.getId(), lista.get(0).getId());
        assertEquals(zw.getName(), lista.get(0).getName());
        assertEquals(zw.getColor(), lista.get(0).getColor());
        assertEquals(zw.getIdentyfikator(), lista.get(0).getIdentyfikator());
    }

    @Test
    public void zmien_UpdatesAnimalSuccessfully() {
        Zwierze zwierze = new Zwierze("Dog", "Brown");
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/putcos"))
                .andExpect(MockRestRequestMatchers.content().json("{\"name\":\"Dog\",\"color\":\"Brown\"}"))
                .andRespond(MockRestResponseCreators.withSuccess());

        service.zmien(zwierze);

        customizer.getServer().verify();
    }

    @Test
    public void getByName_ReturnsAnimalsByName() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/zwierze/name/Dog"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "[{\"id\":\"1\",\"name\":\"Dog\",\"color\":\"Brown\",\"identyfikator\":\"802\"}]",
                        MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.getByName("Dog");
        Zwierze zw = new Zwierze("Dog", "Brown");
        zw.setId(1L);

        assertEquals(1, lista.size());
        assertEquals(zw.getId(), lista.get(0).getId());
        assertEquals(zw.getName(), lista.get(0).getName());
        assertEquals(zw.getColor(), lista.get(0).getColor());
        assertEquals(zw.getIdentyfikator(), lista.get(0).getIdentyfikator());
    }

    @Test
    public void getById_ReturnsAnimalById() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/zwierze/id/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "{\"id\":\"1\",\"name\":\"Dog\",\"color\":\"Brown\",\"identyfikator\":\"1074\"}",
                        MediaType.APPLICATION_JSON));

        Zwierze zw = service.getById(1L);

        assertEquals(1L, zw.getId());
        assertEquals("Dog", zw.getName());
        assertEquals("Brown", zw.getColor());
        assertEquals(1074, zw.getIdentyfikator());
    }

    @Test
    public void findAlllower_ReturnsLowercaseAnimals() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/getalllower"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "[{\"id\":\"1\",\"name\":\"dog\",\"color\":\"brown\",\"identyfikator\":\"866\"}]",
                        MediaType.APPLICATION_JSON));

        List<Zwierze> lista = service.findAlllower();
        Zwierze zw = new Zwierze("dog", "brown");
        zw.setId(1L);

        assertEquals(1, lista.size());
        assertEquals(zw.getId(), lista.get(0).getId());
        assertEquals(zw.getName(), lista.get(0).getName());
        assertEquals(zw.getColor(), lista.get(0).getColor());
        assertEquals(zw.getIdentyfikator(), lista.get(0).getIdentyfikator());
    }

    @Test
    public void addupper_AddsUppercaseAnimalSuccessfully() {
        Zwierze zwierze = new Zwierze("DOG", "BROWN");
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/addcosupper"))
                .andExpect(MockRestRequestMatchers.content().json("{\"name\":\"DOG\",\"color\":\"BROWN\"}"))
                .andRespond(MockRestResponseCreators.withSuccess());

        service.addupper(zwierze);

        customizer.getServer().verify();
    }

    @Test
    public void zrobPdf_GeneratesPdfForValidId() throws IOException {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/zwierze/id/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "{\"id\":\"1\",\"name\":\"Karol\",\"color\":\"szary\",\"identyfikator\":\"1042\"}",
                        MediaType.APPLICATION_JSON));

        byte[] pdf = service.zrobPdf(1L);
        assertNotNull(pdf);
        assertTrue(pdf.length > 0);
    }

    @Test
    public void zrobPdf_ThrowsExceptionForInvalidId() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/zwierze/id/2"))
                .andRespond(MockRestResponseCreators.withSuccess("{}", MediaType.APPLICATION_JSON));

        assertThrows(ResorceNotExistException.class, () -> service.zrobPdf(2L));
    }

    @Test
    public void removeById_RemovesAnimalSuccessfully() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8081/delete/1"))
                .andRespond(MockRestResponseCreators.withSuccess());

        service.removeById(1L);

        customizer.getServer().verify();
    }
}
