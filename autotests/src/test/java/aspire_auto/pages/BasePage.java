package aspire_auto.pages;

import aspire_auto.stepdefs.World;
import aspire_auto.testdata.CommonData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasePage {

    @Autowired
    private World world;

    public void open(String url) {
        world.webDriverFactory.getDriver().get(url);
    }

    protected void setValue(By locator, String value) {
        waitElementToBeAppear(locator);
        WebElement elm = waitElementToBeClickable(locator);
        elm.sendKeys(value);
    }

    protected WebElement waitElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        waitElementToBeAppear(locator);
        WebElement elm = waitElementToBeClickable(locator);
        elm.click();
    }

    protected void clickElement(WebElement elm) {
        elm = waitElementToBeClickable(elm);
        elm.click();
    }

    protected void waitElementToBeDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeClickable(WebElement elm) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(elm));
    }

    protected WebElement waitElementToBeExist(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> waitElementsToBeExist(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<String> getTexts(By locator) {
        List<String> result = new ArrayList<>();
        List<WebElement> elms = waitElementsToBeExist(locator);
        for (WebElement elm : elms) {
            result.add(elm.getText());
        }
        return result;
    }

    protected void sendKeys(CharSequence... keysToSend) {
        world.webDriverFactory.getDriver().switchTo().activeElement().sendKeys(keysToSend);
    }
}
