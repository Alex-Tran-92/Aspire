package aspire_auto.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import aspire_auto.hooks.WebDriverFactory;
import aspire_auto.testdata.CommonData;
import aspire_auto.utils.ScenarioContext;

import java.util.concurrent.TimeUnit;

@ContextConfiguration("file:src/test/resources/spring.xml")
public class CukesHook {
    @Autowired
    private World world;

    @Before
    public void setupCukesBeforeScenarioAndConfigSpringContext() {
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
        //Also , This is to Setup things before each Cucumber Scenario.
        world.scenarioContext = new ScenarioContext();
        world.webDriverFactory = new WebDriverFactory();
        world.webDriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().timeouts().pageLoadTimeout(CommonData.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().timeouts().setScriptTimeout(CommonData.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void cleanUpAfterScenario(Scenario scenario) {
        String FeatureName = scenario.getId().split(";")[0].replace("-", " ");
        if (scenario.isFailed()) {
            System.out.println("=====Scenario Failed==> " + scenario.getName() + "===FEATURE LOCATION: " + FeatureName);
        }
        world.webDriverFactory.getDriver().quit();
    }
}
