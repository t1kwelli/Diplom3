package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {

    private WebDriver driver;

    // Текст "В этом разделе вы можете изменить свои персональные данные"
    private By textAboutChangingPersonalData = By.xpath(".//p[@class='Account_text__fZAIn text text_type_main-default']");

    // Логотип "Stellar Burgers"
    private By logoStellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    // Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");

    // Кнопка "Выход"
    private By logoutButton = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    public PersonalAccountPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ожидания загрузки текста \"В этом разделе вы можете изменить свои персональные данные\" на странице личного кабинета")
    public void waitForLoadTextPersonalData() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textAboutChangingPersonalData));
    }

    @Step("Метод получения текста \"В этом разделе вы можете изменить свои персональные данные\"")
    public String getTextPersonalData () {
        waitForLoadTextPersonalData();
        return driver.findElement(textAboutChangingPersonalData).getText();
    }

    @Step("Метод нажатия на логотип \"Stellar Burgers\"")
    public void clickLogoStellarBurgers () {
        waitForLoadTextPersonalData();
        driver.findElement(logoStellarBurgers).click();
    }

    @Step("Метод нажатия на кнопку \"Конструктор\"")
    public void clickConstructorButton () {
        waitForLoadTextPersonalData();
        driver.findElement(constructorButton).click();
    }

    @Step("Метод нажатия на кнопку \"Выход\"")
    public void clickLogoutButton () {
        waitForLoadTextPersonalData();
        driver.findElement(logoutButton).click();
    }

}
