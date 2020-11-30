package aspire_auto.pages;

import aspire_auto.utils.ObjectMap;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class LoginPage  extends BasePage {
    private ObjectMap objectmap = new ObjectMap("./src/test/resources/locators.properties");

    public void selectRegister() {
        By registerLink = objectmap.getLocator("loginPage.registerLink");
        clickElement(registerLink);
    }
}
