import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class GoToAndFromPersonalAccountTest {

    private WebDriver driver = new ChromeDriver();

    private RegistrationPage registrationPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    private static final Faker faker = new Faker();
    private final String name = faker.name().fullName();
    private final String email = faker.internet().emailAddress();
    private final String password = "123456";

    @Before
    public void setUp () {
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @Test
    @Description("Проверка перехода по клику на \"Личный кабинет\" если пользователь авторизован")
    public void goToPersonalAccountWithAuthorizationTest () {
        // регистрация и логин пользователя
        mainPage.clickSignInButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, password);
        loginPage.login(email, password);

        // переход по клику на "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        String expectedResult = "В этом разделе вы можете изменить свои персональные данные";
        String actualResult = personalAccountPage.getTextPersonalData();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка перехода по клику на \"Личный кабинет\" если пользователь не авторизован")
    public void goToPersonalAccountWithoutAuthorizationTest () {
        // переход по клику на "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        String expectedResult = "Вход";
        String actualResult = loginPage.getTextLogin();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка перехода из личного кабинета в конструктор по клику на логотип \"Stellar Burgers\"")
    public void goToConstructorByLogoStellarBurgers () {
        // регистрация и логин пользователя
        mainPage.clickSignInButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, password);
        loginPage.login(email, password);

        // переход в личный кабинет
        mainPage.clickPersonalAccountButton();

        // переход в конструктор из личного кабинета
        personalAccountPage.clickLogoStellarBurgers();

        String expectedResult = "Конструктор";
        String actualResult = mainPage.getTextCurrentSectionOnMainPage();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка перехода из личного кабинета в конструктор по клику на кнопку \"Конструктор\"")
    public void goToConstructorByConstructorButton () {
        // регистрация и логин пользователя
        mainPage.clickSignInButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, password);
        loginPage.login(email, password);

        // переход в личный кабинет
        mainPage.clickPersonalAccountButton();

        // переход в конструктор из личного кабинета
        personalAccountPage.clickConstructorButton();

        String expectedResult = "Конструктор";
        String actualResult = mainPage.getTextCurrentSectionOnMainPage();

        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown () {
        driver.quit();
    }
}
