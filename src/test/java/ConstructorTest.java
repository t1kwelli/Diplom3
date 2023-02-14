import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {

    private WebDriver driver = new ChromeDriver();

    private MainPage mainPage;

    @Before
    public void setUp () {
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
    }

    @Test
    @Description("Проверка перехода в раздел \"Соусы\" в разделе \"Конструктор\"")
    public void goToSaucesSectionTest () {
        mainPage.goToSauces();

        String expectedResult = "Соусы";
        String actualResult = mainPage.getTextCurrentSectionInConstructor();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка перехода в раздел \"Начинки\" в разделе \"Конструктор\"")
    public void goToFillingsSectionTest () {
        mainPage.goToFillings();

        String expectedResult = "Начинки";
        String actualResult = mainPage.getTextCurrentSectionInConstructor();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Description("Проверка перехода в раздел \"Булки\" в разделе \"Конструктор\"")
    public void goToRollsSectionTest () {
        mainPage.goToFillings();
        mainPage.goToRolls();

        String expectedResult = "Булки";
        String actualResult = mainPage.getTextCurrentSectionInConstructor();

        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown () {
        driver.quit();
    }

}
