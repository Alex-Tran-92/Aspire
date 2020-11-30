package aspire_auto.pages;

import aspire_auto.utils.ObjectMap;
import aspire_auto.utils.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OnboardingPage extends BasePage {
    private ObjectMap objectmap = new ObjectMap("./src/test/resources/locators.properties");

    public void chooseGetStartedWith(String optionStartedWith) {
        By optionStartedWithBtn = objectmap.getLocatorAndFormats("onBoardingPage.getStartedBtn", optionStartedWith);
        clickElement(optionStartedWithBtn);
    }

    public void selectDateOfBirth() {
        By dateOfBirthInput = objectmap.getLocator("onBoardingPage.personalDetail.dateOfBirthInput");
        clickElement(dateOfBirthInput);
        By calendar = objectmap.getLocator("onBoardingPage.personalDetail.calendar");
        waitElementToBeAppear(calendar);
    }

    public void selectYearOfBirth(String yearOfBirth, String currentYear) {
        By yearTitle = objectmap.getLocatorAndFormats("onBoardingPage.personalDetail.calendarMonthYear", currentYear);
        clickElement(yearTitle);
        By yearOption = objectmap.getLocator("onBoardingPage.personalDetail.calendar.yearOptions");
        By iconLeft = objectmap.getLocator("onBoardingPage.personalDetail.calendarLeftIcon");
        boolean chooseYear = false;
        for (int i = 0; i < 10; i++) {
            if (getTexts(yearOption).contains(yearOfBirth)) {
                List<WebElement> elements = waitElementsToBeExist(yearOption);
                for (WebElement elm : elements) {
                    if (elm.getText().equals(yearOfBirth)) {
                        clickElement(elm);
                        waitElementToBeDisappear(yearOption);
                        chooseYear = true;
                        break;
                    }
                }
                if (chooseYear) {
                    break;
                }
            } else {
                clickElement(iconLeft);
            }
        }
    }

    public void selectMonthOfBirth(String monthOfBirth, String currentMonth) {
        By monthTitle = objectmap.getLocatorAndFormats("onBoardingPage.personalDetail.calendarMonthYear", currentMonth);
        clickElement(monthTitle);
        By monthOption = objectmap.getLocator("onBoardingPage.personalDetail.calendar.monthOption");
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
        By dayOption = objectmap.getLocator("onBoardingPage.personalDetail.calendar.dayOption");
        List<WebElement> elements = waitElementsToBeExist(dayOption);
        for (WebElement elm : elements) {
            if (elm.getText().equals(dayOfBirth)) {
                clickElement(elm);
                waitElementToBeDisappear(dayOption);
                break;
            }
        }
    }

    public void chooseDateOfBirth(String year, String month, String day) {
        LocalDate localDate = LocalDate.now();
        String currentYear = String.valueOf(localDate.getYear());
        String currentMonth = String.valueOf(localDate.getMonth());
        currentMonth = currentMonth.charAt(0) + currentMonth.substring(1).toLowerCase();

        selectDateOfBirth();
        selectYearOfBirth(year, currentYear);
        selectMonthOfBirth(month, currentMonth);
        selectDayOfBirth(day);
    }

    public void chooseValueForField(String title, String option) {
        By titleLbl = objectmap.getLocatorAndFormats("onBoardingPage.titleLbl", title);
        clickElement(titleLbl);
        By dropDownList = objectmap.getLocator("onBoardingPage.dropDownlist");
        waitElementToBeAppear(dropDownList);
        By chooseOption = objectmap.getLocatorAndFormats("onBoardingPage.optionDDL", option);
        clickElement(chooseOption);
    }

    public void inputBusinessName(String businessName) {
        By businessNameInput = objectmap.getLocator("onBoardingPage.businessDetail.businessNameInput");
        setValue(businessNameInput, businessName);
    }

    public void inputBusinessRegistration(){
        By businessRegistrationNoInput = objectmap.getLocator("onBoardingPage.businessDetail.businessRegistrationNoInput");
        String businessRegistrationNo = RandomUtils.getRandomNumber() + 'A';
        setValue(businessRegistrationNoInput, businessRegistrationNo);
    }

    public void fillInfoForBusinessDetail(String businessName, String registrationType, String industry, String subIndustry) {
        inputBusinessName(businessName);
        chooseValueForField("Registration Type", registrationType);
        inputBusinessRegistration();
        chooseValueForField("Industry", industry);
        chooseValueForField("Sub Industry", subIndustry);
    }
}
