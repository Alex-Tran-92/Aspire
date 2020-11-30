package aspire_auto.stepdefs.Pages;

import aspire_auto.pages.OnboardingPage;
import aspire_auto.pages.RegisterPage;
import aspire_auto.stepdefs.AbstractSteps;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class OnboardingSteps extends AbstractSteps implements En {
    @Autowired
    private OnboardingPage onboardingPage;

    @Autowired
    private RegisterPage registerPage;

    public OnboardingSteps() {
        When("the user choose \"(.*)\" button with registered business in Singapore with ACRA", (String buttonName) -> {
            registerPage.clickButton(buttonName);
        });

        When("the user choose get started with \"(.*)\"", (String optionStartedWith) -> {
            onboardingPage.chooseGetStartedWith(optionStartedWith);
        });

        When("the user select date of birth as below", (DataTable dt) -> {
            List<Map<String, String>> dataTableList = dt.asMaps(String.class, String.class);
            for (int i = 0; i < dataTableList.size(); i++) {
                String year = dataTableList.get(i).get("year");
                String month = dataTableList.get(i).get("month");
                String day = dataTableList.get(i).get("day");
                onboardingPage.chooseDateOfBirth(year, month, day);
            }
        });

        When("the user select \"(.*)\" is \"(.*)\"", (String title, String option) -> {
            onboardingPage.chooseValueForField(title, option);
        });

        When("the user fill the info for business detail as below", (DataTable dt) -> {
            List<Map<String, String>> dataTableList = dt.asMaps(String.class, String.class);
            for (int i = 0; i < dataTableList.size(); i++) {
                String businessName = dataTableList.get(i).get("businessName");
                String registrationType = dataTableList.get(i).get("registrationType");
                String industry = dataTableList.get(i).get("industry");
                String subIndustry = dataTableList.get(i).get("subIndustry");
                onboardingPage.fillInfoForBusinessDetail(businessName, registrationType, industry, subIndustry);
            }
        });
    }
}
