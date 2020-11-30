package aspire_auto.stepdefs.Pages;

import aspire_auto.pages.PersonalDetailPage;
import aspire_auto.stepdefs.AbstractSteps;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PersonalDetailSteps extends AbstractSteps implements En {
    @Autowired
    private PersonalDetailPage personalDetailPage;

    public PersonalDetailSteps() {

        When("the user select date of birth as below", (DataTable dt) -> {
            List<Map<String, String>> dataTableList = dt.asMaps(String.class, String.class);
            for (int i = 0; i < dataTableList.size(); i++) {
                String year = dataTableList.get(i).get("year");
                String month = dataTableList.get(i).get("month");
                String day = dataTableList.get(i).get("day");
                personalDetailPage.chooseDateOfBirth(year, month, day);
            }
        });

    }
}
