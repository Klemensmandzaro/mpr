package pl.edu.pjatk.zaj2.serenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.edu.pjatk.zaj2.exception.IdentyfierAlreadyExistException;

import static org.junit.jupiter.api.Assertions.*;

public class MyPageTest {
    WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        this.webDriver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.close();
    }


    @Test
    public void testAddInputs() throws InterruptedException {
        MyPage myPage = new MyPage(webDriver).open();
        assertTrue(myPage.isHeaderDisplayed());
        Thread.sleep(3000);
        myPage.fillInAddInputs("Kamil", "zielony");
        Thread.sleep(3000);
        MyPageViewAll viewAll = myPage.viewAll();
        assertTrue(webDriver.findElement(By.id("row-3-id")).isDisplayed());

    }

    @Test
    public void testEditInputs() throws InterruptedException {
        MyPage myPage = new MyPage(webDriver).open();
        Thread.sleep(3000);
        assertTrue(myPage.isHeaderDisplayed());
        myPage.fillInEditInputs("3","Kamil", "niebieski");
        Thread.sleep(3000);
        MyPageViewAll viewAll = myPage.viewAll();
        assertTrue("niebieski".contentEquals(webDriver.findElement(By.id("row-3-color")).getText()));


    }

    @Test
    public void testDeleteInputs() throws InterruptedException {
        MyPage myPage = new MyPage(webDriver).open();
        Thread.sleep(3000);
        assertTrue(myPage.isHeaderDisplayed());
        myPage.fillInDeleteInputs("3");
        Thread.sleep(3000);
        MyPageViewAll viewAll = myPage.viewAll();


        assertThrows(NoSuchElementException.class, () -> {
            webDriver.findElement(By.id("row-3-id"));
        });

    }
}
