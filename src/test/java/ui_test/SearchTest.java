package ui_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.MainPageGoogle;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

import static enums.ValuesEnum.GOOGLE_URL;
import static enums.ValuesEnum.SEARCH_REQUEST;

@Features({"Google Test Suite"})
@Stories({"\"Main Page Search\" tests"})
public class SearchTest {
    private WebDriver driver;
    private MainPageGoogle mainPageGoogle;

    @Step("Starting environment")
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver1.exe");
        driver = new ChromeDriver();
        mainPageGoogle = PageFactory.initElements(driver, MainPageGoogle.class);
        driver.manage().window().maximize();
    }

    @Step("Close driver")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    @Test
    public void checkMainPageSearch() {
        mainPageGoogle.openUrl(GOOGLE_URL.text, driver);
        mainPageGoogle.checkPageTitle(driver);
        mainPageGoogle.checkSearchFormExistance(driver);
        mainPageGoogle.checkSearchFunctionality(SEARCH_REQUEST.text);
        mainPageGoogle.checkResultsTitlePage(driver);
        mainPageGoogle.checkResultsSection();
        mainPageGoogle.checkSearchTooltip(driver);
        mainPageGoogle.checkLeftTopLogoIsEmpty();
    }
}