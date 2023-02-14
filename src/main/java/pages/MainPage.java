package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    // Текст "Соберите бургер" для ожидания загрузки главной страницы
    private By textAssembleBurger = By.xpath(".//h1[text()='Соберите бургер']");

    // Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    // Кнопка "Войти в аккаунт"
    private By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");

    // Кнопка для перехода в раздел "Булки"
    private By rollsButton = By.xpath(".//span[text()='Булки']");

    // Кнопка для перехода в раздел "Соусы"
    private By saucesButton = By.xpath(".//span[text()='Соусы']");

    // Кнопка для перехода в раздел "Начинки"
    private By fillingsButton = By.xpath(".//span[text()='Начинки']");

    // Текущий раздел на главной странице (Конструктор или Лента заказов)
    private By currentSectionOnMainPage = By.xpath(".//a[@class='AppHeader_header__link__3D_hX AppHeader_header__link_active__1IkJo']");

    // Текущий раздел в конструкторе (Булки или Соусы или Начинки)
    private By currentSectionInConstructor = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");


    @Step("Метод получения текста \"Соберите бургер\" на главной странице")
    public String getTextAssembleBurger () {
        return driver.findElement(textAssembleBurger).getText();
    }

    @Step("Метод ожидания загрузки текста \"Соберите бургер\" на главной странице")
    public void waitForLoadTextAssembleBurger () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textAssembleBurger));
    }

    @Step("Метод нажатия на кнопку \"Личный кабинет\"")
    public void clickPersonalAccountButton () {
        waitForLoadTextAssembleBurger();
        driver.findElement(personalAccountButton).click();
    }

    @Step("Метод нажатия на кнопку \"Войти в аккаунт\"")
    public void clickSignInButton () {
        waitForLoadTextAssembleBurger();
        driver.findElement(signInButton).click();
    }

    @Step("Метод перехода в раздел \"Булки\"")
    public void goToRolls () {
        waitForLoadTextAssembleBurger();
        driver.findElement(rollsButton).click();
    }

    @Step("Метод перехода в раздел \"Соусы\"")
    public void goToSauces () {
        waitForLoadTextAssembleBurger();
        driver.findElement(saucesButton).click();
    }

    @Step("Метод перехода в раздел \"Начинки\"")
    public void goToFillings () {
        waitForLoadTextAssembleBurger();
        driver.findElement(fillingsButton).click();
    }

    @Step("Метод определения текущего раздела в разделе \"Конструктор\"")
    public String getTextCurrentSectionInConstructor () {
        return driver.findElement(currentSectionInConstructor).getText();
    }

    @Step("Метод определения текущего раздела на главной странице")
    public String getTextCurrentSectionOnMainPage () {
        return driver.findElement(currentSectionOnMainPage).getText();
    }

}
