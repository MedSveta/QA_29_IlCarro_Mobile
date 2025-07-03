package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationScreen extends BaseScreen {
    public RegistrationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editRegName']")
    AndroidElement regName;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editRegLastName']")
    AndroidElement regLastName;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editRegEmail']")
    AndroidElement regEmail;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editRegPassword']")
    AndroidElement regPassword;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/checkBoxAgree']")
    AndroidElement checkBox;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/regBtn']")
    AndroidElement btnYalla;


    public void typeRegForm(RegistrationBodyDto user) {
        regName.sendKeys(user.getFirstName());
        regLastName.sendKeys(user.getLastName());
        regEmail.sendKeys(user.getUsername());
        regPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBox(){
        checkBox.click();
    }
    public void clickBtnYalla(){
        btnYalla.click();
    }
}
