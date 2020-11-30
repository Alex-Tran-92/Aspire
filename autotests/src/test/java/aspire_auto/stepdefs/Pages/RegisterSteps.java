package aspire_auto.stepdefs.Pages;

import aspire_auto.stepdefs.AbstractSteps;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import aspire_auto.pages.RegisterPage;

import java.util.List;
import java.util.Map;

public class RegisterSteps extends AbstractSteps implements En {
    @Autowired
    private RegisterPage registerPage;

    public RegisterSteps() {

        When("the user fill the info to register as below", (DataTable dt) -> {
            List<Map<String, String>> dataTableList = dt.asMaps(String.class, String.class);
            for (int i = 0; i < dataTableList.size(); i++) {
                String roleInCompany = dataTableList.get(i).get("roleInCompany");
                String registerPersonHeardAbout = dataTableList.get(i).get("registerPersonHeardAbout");
                String promoCode = dataTableList.get(i).get("promoCode");
                registerPage.fillInfoToRegister(roleInCompany, registerPersonHeardAbout, promoCode);
            }
        });

        When("the user stick the privacy checkbox", () -> {
            registerPage.selectedPrivacyCheckbox();
        });

        When("the user click the \"(.*)\" button", (String buttonName) -> {
            registerPage.clickButton(buttonName);
        });

        When("the user fill the phone OTP", () -> {
            registerPage.fillOTP();
        });

        When("the user choose \"(.*)\" button with registered business in Singapore with ACRA", (String buttonName) -> {
            registerPage.clickButton(buttonName);
        });

        When("the user choose get started with \"(.*)\"", (String optionStartedWith) -> {
            registerPage.chooseGetStartedWith(optionStartedWith);
        });
    }
}
