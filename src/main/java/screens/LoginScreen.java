package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends BaseScreen {
    public LoginScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editLoginEmail']")
    AndroidElement loginEmail;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/editLoginPassword']")
    AndroidElement loginPassword;
    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/loginBtn']")
    AndroidElement btnLogin;

    public void typeLoginForm(RegistrationBodyDto user){
        loginEmail.sendKeys(user.getUsername());
        loginPassword.sendKeys(user.getPassword());
    }

    public void clickBtnLogin(){
        clickWait(btnLogin, 3);
    }
}
