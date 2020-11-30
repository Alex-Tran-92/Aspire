package aspire_auto.stepdefs;

import org.springframework.stereotype.Component;
import aspire_auto.hooks.WebDriverFactory;
import aspire_auto.utils.ScenarioContext;

@Component
public class World {
    public ScenarioContext scenarioContext;
    public WebDriverFactory webDriverFactory;

}
