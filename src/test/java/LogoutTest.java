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

public class LogoutTest {

    private WebDriver driver = new ChromeDriver();

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

        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);

        // регистрация и логин пользователя
        mainPage.clickSignInButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, password);
        loginPage.login(email, password);

        // переход в личный кабинет
        mainPage.clickPersonalAccountButton();
    }

    @Test
    @Description("Проверка выхода выхода по кнопке \"Выход\" в личном кабинете")
    public void logoutTest () {
        personalAccountPage.clickLogoutButton();

        String expectedResult = "Вход";
        String actualResult = loginPage.getTextLogin();

        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown () {
        driver.quit();
    }
}
