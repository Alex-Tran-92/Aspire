package aspire_auto.pages;

import aspire_auto.utils.ObjectMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class PersonalDetailPage extends BasePage {
    private ObjectMap objectmap = new ObjectMap("./src/test/resources/locators.properties");

    public void selectDateOfBirth() {
        By dateOfBirthInput = objectmap.getLocator("personalDetailPage.dateOfBirthInput");
        clickElement(dateOfBirthInput);
        By calendar = objectmap.getLocator("personalDetailPage.calendar");
        waitElementToBeAppear(calendar);
    }

    public void selectYearOfBirth(String yearOfBirth, String currentYear) {
        By yearTitle = objectmap.getLocatorAndFormats("personalDetailPage.calendarMonthYear", currentYear);
        clickElement(yearTitle);
        By yearOption = objectmap.getLocator("personalDetailPage.calendar.yearOptions");
        By iconLeft = objectmap.getLocator("personalDetailPage.calendarLeftIcon");
        for (int i = 0; i < 10; i++) {
            if (getTexts(yearOption).contains(yearOfBirth)) {
                List<WebElement> elements = waitElementsToBeExist(yearOption);
                for (WebElement elm : elements) {
                    if (elm.getText().equals(yearOfBirth)) {
                        clickElement(elm);
                        waitElementToBeDisappear(yearOption);
                        break;
                    }
                }
            } else {
                clickElement(iconLeft);
            }
        }
    }

    public void selectMonthOfBirth(String monthOfBirth, String currentMonth) {
        By monthTitle = objectmap.getLocatorAndFormats("personalDetailPage.calendarMonthYear", currentMonth);
        clickElement(monthTitle);
        By monthOption = objectmap.getLocator("personalDetailPage.calendar.monthOption");
        List<WebElement> elements = waitElementsToBeExist(monthOption);
        for (WebElement elm : elements) {
            if (elm.getText().equals(monthOfBirth)) {
                clickElement(elm);
                waitElementToBeDisappear(monthOption);
                break;
            }
        }
    }

    public void selectDayOfBirth(String dayOfBirth) {
        By dayOption = objectmap.getLocator("personalDetailPage.calendar.dayOption");
        List<WebElement> elements = waitElementsToBeExist(dayOption);
        for (WebElement elm : elements) {
            if (elm.getText().equals(dayOfBirth)) {
                clickElement(elm);
                break;
            }
        }
    }

    public void chooseDateOfBirth(String year, String month, String day) {
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM");

        String currentYear = String.valueOf(localDate.getYear());
        String currentMonth = formatter.format(localDate.getMonthValue());

        selectDateOfBirth();
        selectYearOfBirth(year, currentYear);
        selectMonthOfBirth(month, currentMonth);
        selectDayOfBirth(day);
    }
}