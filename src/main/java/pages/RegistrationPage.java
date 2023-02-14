package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;

    // Текст "Регистрация" для ожидания загрузки страницы регистрации
    private By textRegistration = By.xpath(".//h2[text()='Регистрация']");

    // Текст "Некорректный пароль"
    private By textIncorrectPassword = By.xpath(".//fieldset[3]//p");

    // Поле "Имя"
    private By nameField = By.xpath(".//fieldset[1]//input");

    // Поле "Email"
    private By emailField = By.xpath(".//fieldset[2]//input");

    // Поле "Пароль"
    private By passwordField = By.xpath(".//fieldset[3]//input");

    // Кнопка "Зарегистрироваться"
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Кнопка "Войти"
    private By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ввода имени")
    public void enterName (String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Метод ввода email'а")
    public void enterEmail (String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод ввода пароля")
    public void enterPassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Метод нажатия на кнопку \"Зарегистрироваться\"")
    public void clickRegistrationButton () {
        driver.findElement(registrationButton).click();
    }

    @Step("Метод ожидания загрузки текста \"Регистрация\" на странице регистрации")
    public void waitForLoadTextRegistration () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textRegistration));
    }

    @Step("Метод получения текста \"Некорректный пароль\"")
    public String getTextIncorrectPassword () {
        return driver.findElement(textIncorrectPassword).getText();
    }

    @Step("Общий метод регистрации")
    public void registration (String name, String email, String password) {
        waitForLoadTextRegistration();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegistrationButton();
    }

    @Step("Метод нажатия на кнопку \"Войти\"")
    public void clickLoginButton () {
        waitForLoadTextRegistration();
        driver.findElement(loginButton).click();
    }
}
