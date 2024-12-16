package pl.edu.pjatk.zaj2.serenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyPageViewAll {
    WebDriver webDriver;

    public MyPageViewAll(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public MyPageViewAll open() {
        webDriver.navigate().to("http://localhost:8080/view/all");
        return this;
    }
}
