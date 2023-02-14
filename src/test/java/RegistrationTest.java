import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {

    private WebDriver driver = new ChromeDriver();

    private RegistrationPage registrationPage;
    private MainPage mainPage;
    private LoginPage loginPage;

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
    }

    @Test
    @Description("Проверка успешной регистрации")
    public void successRegistrationTest () {
        registrationPage.registration(name, email, "123456");

        loginPage.waitForLoadTextLogin();

        String expectedResult = "Вход";
        String actualResult = loginPage.getTextLogin();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка регистрации с некорректным паролем (пароль меньше 6 символов)")
    public void registrationWithIncorrectPasswordTest () {
        registrationPage.registration(name, email, "12345");

        String expectedResult = "Некорректный пароль";
        String actualResult = registrationPage.getTextIncorrectPassword();

        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown () {
        driver.quit();
    }

}
