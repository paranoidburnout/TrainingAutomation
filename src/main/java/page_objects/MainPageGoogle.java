package page_objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static enums.ValuesEnum.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageGoogle {
    private WebDriverWait webDriverWait;
    private Actions actions;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchForm;

    @FindBy(xpath = "//*[@id='logo']")
    private WebElement logo;

    @FindBy(xpath = "//h3[@class='LC20lb DKV0Md' and contains(. ,'Cognitive neuroscience')]")
    private List<WebElement> searchResults;

    @Step("Open Google URL")
    public void openUrl(String url, WebDriver driver) {
        driver.navigate().to(url);
    }

    @Step("Check Page Title")
    public void checkPageTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), GOOGLE_TITLE.text);
    }

    @Step("Check that Google Search Form is existing")
    public void checkSearchFormExistance(WebDriver driver) {
        webDriverWait = new WebDriverWait(driver, 3);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchForm));
        searchForm.isDisplayed();
    }

    @Step("Check that Search Form works correctly by making request")
    public void checkSearchFunctionality(String searchRequest) {
        searchForm.click();
        searchForm.sendKeys(searchRequest);
        searchForm.sendKeys(Keys.ENTER);

    }

    @Step("Check after request that current page is Results Page  ")
    public void checkResultsTitlePage(WebDriver driver) {
        assertEquals(driver.getTitle(), RESULTS_TITLE.text);
    }

    @Step("Check that all results is exist")
    public void checkResultsSection() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        searchResults.forEach(result -> assertTrue(result.isDisplayed()));
    }

    @Step("Check that Search Tooltip is exist")
    public void checkSearchTooltip(WebDriver driver) {
        actions = new Actions(driver);
        actions.moveToElement(logo).moveToElement(searchForm).build().perform();
        assertEquals(searchForm.getAttribute(TITLE.text), TOOLTIP_TEXT.text);
    }

    @Step("Check that Search Results Area is empty after click Google logo on the left top side")
    public void checkLeftTopLogoIsEmpty() {
        actions.moveToElement(logo).click().build().perform();
        assertTrue(searchResults.isEmpty());
    }
}
