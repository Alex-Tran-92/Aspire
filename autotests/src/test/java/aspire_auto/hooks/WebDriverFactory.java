package aspire_auto.hooks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import aspire_auto.configuration.AspireConfiguration;

public class WebDriverFactory {

    private WebDriver webDriver;

    public WebDriverFactory() {
        this.webDriver = setWebDriver();
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    private WebDriver setWebDriver() {
        String browser = AspireConfiguration.getBrowser();
        switch (browser) {
            case ("firefox"):
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("marionette", true);
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case ("chromeHeadless"):
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeHeadless = new ChromeOptions();
                chromeHeadless.setHeadless(true);
                webDriver = new ChromeDriver(chromeHeadless);
                break;
            case ("edge"):
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chrome = new ChromeOptions();
                webDriver = new ChromeDriver(chrome);
                break;
        }
        return webDriver;
    }
}