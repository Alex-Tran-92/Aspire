package aspire_auto.stepdefs.Pages;

import aspire_auto.pages.LoginPage;
import aspire_auto.stepdefs.AbstractSteps;
import aspire_auto.utils.Props;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSteps extends AbstractSteps implements En {
    @Autowired
    private LoginPage loginPage;

    public LoginSteps(){

        When("the user is on the login page", () -> {
            loginPage.open(Props.getProp("baseUrl"));
        });

        When("the user select register link", () -> {
            loginPage.selectRegister();
        });
    }
}
