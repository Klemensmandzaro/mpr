package pl.edu.pjatk.zaj2.serenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyPage {
    WebDriver webDriver;

    @FindBy(id="addSubmit")
    private WebElement addSubmitButton;

    @FindBy(id="addName")
    private WebElement addNameInput;

    @FindBy(id="addColor")
    private WebElement addColorInput;

    @FindBy(id="editSubmit")
    private WebElement editSubmitButton;

    @FindBy(id="editId")
    private WebElement editIdInput;

    @FindBy(id="editName")
    private WebElement editNameInput;

    @FindBy(id="editColor")
    private WebElement editColorInput;

    @FindBy(id="deleteSubmit")
    private WebElement deleteSubmitButton;

    @FindBy(id="deleteId")
    private WebElement deleteIdInput;

    @FindBy(id="naglowek")
    private WebElement header;

    public MyPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public MyPage open(){
        webDriver.get("http://localhost:8080/view/form");
        return this;
    }

    public MyPage fillInAddInputs(String name, String color){
        addNameInput.sendKeys(name);
        addColorInput.sendKeys(color);
        addSubmitButton.click();
        return this;
    }

    public boolean isHeaderDisplayed(){
        return header.isDisplayed();
    }

    public MyPage fillInEditInputs(String id, String name, String color){
        editIdInput.sendKeys(id);
        editNameInput.sendKeys(name);
        editColorInput.sendKeys(color);
        editSubmitButton.click();
        return this;
    }

    public MyPage fillInDeleteInputs(String id){
        deleteIdInput.sendKeys(id);
        deleteSubmitButton.click();
        return this;
    }

    public MyPageViewAll viewAll(){
        return new MyPageViewAll(webDriver);
    }




}
