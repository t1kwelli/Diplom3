package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    // Текст "Логин" для ожидания загрузки страницы логина
    private By textLogin = By.xpath(".//h2[text()='Вход']");

    // Поле "Email"
    private By emailField = By.xpath(".//fieldset[1]//input");

    // Поле "Пароль"
    private By passwordField = By.xpath(".//fieldset[2]//input");

    // Кнопка "Войти"
    private By loginButton = By.xpath(".//button[text()='Войти']");

    // Кнопка "Зарегистрироваться"
    private By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");

    // Кнопка "Восстановить пароль"
    private By passwordRecoveryButton = By.xpath(".//a[text()='Восстановить пароль']");

    // Логотип "Stellar Burgers"
    private By logoStellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ввода Email'а")
    public void enterEmail (String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод ввода пароля")
    public void enterPassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Метод нажатия на кнопку \"Войти\"")
    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }

    @Step("Метод получения текста \"Вход\"")
    public String getTextLogin () {
        waitForLoadTextLogin();
        return driver.findElement(textLogin).getText();
    }

    @Step("Метод ожидания загрузки текста \"Вход\" на странице логина")
    public void waitForLoadTextLogin() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textLogin));
    }

    @Step("Общий метод логина")
    public void login (String email, String password) {
        waitForLoadTextLogin();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Метод нажатия на кнопку \"Зарегистрироваться\" на странице логина")
    public void clickRegistrationButton () {
        waitForLoadTextLogin();
        driver.findElement(registrationButton).click();
    }

    @Step("Метод нажатия на кнопку \"Восстановить пароль\" на странице логина")
    public void clickPasswordRecoveryButton () {
        waitForLoadTextLogin();
        driver.findElement(passwordRecoveryButton).click();
    }

    @Step("Метод нажатия на логотип \"Stellar Burgers\" для перехода на главную страницу")
    public void clickLogoStellarBurgers () {
        waitForLoadTextLogin();
        driver.findElement(logoStellarBurgers).click();
    }
}
