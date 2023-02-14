import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver = new ChromeDriver();

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    private static final Faker faker = new Faker();
    private String name = faker.name().fullName();
    private String email = faker.internet().emailAddress();

    @Before
    public void setUp () {
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.clickSignInButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, "123456");
    }

    @Test
    @Description("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void loginBySignInButtonTest() {
        // переходим на главную страницу
        loginPage.clickLogoStellarBurgers();
        // проверяем вход по кнопке "Войти в аккаунт"
        mainPage.clickSignInButton();
        loginPage.login(email, "123456");
        mainPage.waitForLoadTextAssembleBurger();

        String expectedResult = "Соберите бургер";
        String actualResult = mainPage.getTextAssembleBurger();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка входа через кнопку \"Личный кабинет\"")
    public void loginByPersonalAreaButton () {
        // переходим на главную страницу
        loginPage.clickLogoStellarBurgers();
        // проверяем вход по кнопке "Личный кабинет"
        mainPage.clickPersonalAccountButton();
        loginPage.login(email, "123456");
        mainPage.waitForLoadTextAssembleBurger();

        String expectedResult = "Соберите бургер";
        String actualResult = mainPage.getTextAssembleBurger();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка входа через кнопку в форме регистрации")
    public void loginFromRegistrationPage () {
        // переходим на страницу регистрации
        loginPage.clickRegistrationButton();
        // проверяем вход через кнопку в форме регистрации
        registrationPage.clickLoginButton();
        loginPage.login(email, "123456");
        mainPage.waitForLoadTextAssembleBurger();

        String expectedResult = "Соберите бургер";
        String actualResult = mainPage.getTextAssembleBurger();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка входа через кнопку в форме восстановления пароля")
    public void loginFromPasswordRecoveryPage () {
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);

        // переходим на страницу восстановления пароля
        loginPage.clickPasswordRecoveryButton();
        // проверяем вход через кнопку в форме восстановления пароля
        passwordRecoveryPage.clickLoginButton();
        loginPage.login(email, "123456");
        mainPage.waitForLoadTextAssembleBurger();

        String expectedResult = "Соберите бургер";
        String actualResult = mainPage.getTextAssembleBurger();

        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown () {
        driver.quit();
    }

}
