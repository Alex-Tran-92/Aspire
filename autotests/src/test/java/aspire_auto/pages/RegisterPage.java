package aspire_auto.pages;

import aspire_auto.utils.ObjectMap;
import aspire_auto.utils.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterPage extends BasePage {
    private ObjectMap objectmap = new ObjectMap("./src/test/resources/locators.properties");

    public void enterName() {
        String name = RandomUtils.getRandomFullName();
        By nameInput = objectmap.getLocator("registerPage.nameInput");
        setValue(nameInput, name);
    }

    public void enterEmail() {
        By emailInput = objectmap.getLocator("registerPage.emailInput");
        String email = RandomUtils.getRandomEmail();
        setValue(emailInput, email);
    }

    public void enterPhoneNumber() {
        By phoneInput = objectmap.getLocator("registerPage.phoneInput");
        String phoneNumber = RandomUtils.getRandomNumber();
        setValue(phoneInput, phoneNumber);
    }

    public void chooseRoleInCompany(String roleInCompany) {
        By roleInCompanyOption = objectmap.getLocatorAndFormats("registerPage.roleInCompanyRadioBtn", roleInCompany);
        clickElement(roleInCompanyOption);
    }

    public void chooseRegisterPersonHeardAboutOption(String registerPersonHeardAbout) {
        By registerPersonHeardAboutInput = objectmap.getLocator("registerPage.hearAboutUsInput");
        clickElement(registerPersonHeardAboutInput);
        By registerPersonHeardAboutDDL = objectmap.getLocator("registerPage.hearAboutUsDDL");
        waitElementToBeAppear(registerPersonHeardAboutDDL);
        By registerPersonHeardAboutOption = objectmap.getLocatorAndFormats("registerPage.hearAboutUsOptions", registerPersonHeardAbout);
        clickElement(registerPersonHeardAboutOption);
    }

    public void enterPromoCode(String promoCode) {
        if (!promoCode.equals("None")) {
            By promoCodeInput = objectmap.getLocator("registerPage.promoCodeInput");
            setValue(promoCodeInput, promoCode);
        }
    }

    public void fillInfoToRegister(String roleInCompany, String registerPersonHeardAbout, String promoCode) {
        enterName();
        enterEmail();
        enterPhoneNumber();
        chooseRoleInCompany(roleInCompany);
        chooseRegisterPersonHeardAboutOption(registerPersonHeardAbout);
        enterPromoCode(promoCode);
    }

    public void selectedPrivacyCheckbox() {
        By privacyCheckbox = objectmap.getLocator("registerPage.registerPrivacyChb");
        clickElement(privacyCheckbox);
    }

    public void clickButton(String buttonName) {
        By button = objectmap.getLocatorAndFormats("registerPage.commonBtn", buttonName);
        clickElement(button);
        waitElementToBeAppear(spinner());
    }

    public By spinner() {
        return objectmap.getLocator("registerPage.spinner");
    }

    public void fillOTP() {
        waitElementToBeDisappear(spinner());
        By otpInput = objectmap.getLocator("registerPage.otpInput");
        List<WebElement> otpList = waitElementsToBeExist(otpInput);
        for (int i = 0; i < otpList.size(); i++) {
            int value = i + 1;
            switch (value){
                case 1: sendKeys(Keys.NUMPAD1);
                case 2: sendKeys(Keys.NUMPAD2);
                case 3: sendKeys(Keys.NUMPAD3);
                case 4: sendKeys(Keys.NUMPAD4);
            }
        }
    }

    public void chooseGetStartedWith(String optionStartedWith) {
        By optionStartedWithBtn = objectmap.getLocatorAndFormats("registerPage.getStartedBtn", optionStartedWith);
        clickElement(optionStartedWithBtn);
    }
}
