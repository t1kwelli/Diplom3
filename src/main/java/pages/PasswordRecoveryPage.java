package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {

    private WebDriver driver;

    // Текст "Восстановление пароля" для ожидания загрузки страницы восстановления пароля
    private By textPasswordRecovery = By.xpath("//h2[text()='Восстановление пароля']");

    // Поле "Email"
    private By emailField = By.xpath(".//label[text()='Email']");

    // Кнопка "Восстановить"
    private By recoveryButton = By.xpath(".//button[text()='Восстановить']");

    // Кнопка "Войти"
    private By loginButton = By.xpath(".//a[text()='Войти']");

    public PasswordRecoveryPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ввода Email'а")
    public void enterEmail (String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод нажатия на кнопку \"Восстановить\"")
    public void clickRecoveryButton () {
        driver.findElement(recoveryButton).click();
    }

    @Step("Метод ожидания загрузки текста \"Восстановление пароля\"")
    public void waitForLoadTextPasswordRecovery() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textPasswordRecovery));
    }

    @Step("Общий метод восстановления пароля")
    public void RecoveryPassword (String email) {
        waitForLoadTextPasswordRecovery();
        enterEmail(email);
        clickRecoveryButton();
    }

    @Step("Метод нажатия на кнопку \"Войти\"")
    public void clickLoginButton () {
        waitForLoadTextPasswordRecovery();
        driver.findElement(loginButton).click();
    }
}
