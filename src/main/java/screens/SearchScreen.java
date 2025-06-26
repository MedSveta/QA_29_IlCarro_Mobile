package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SearchScreen extends BaseScreen {
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnDots;
    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;
    @FindBy(xpath = "//hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;

    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement btnMyCars;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement btnLogout;


    public void clickBtnMyCars() {
    clickWait(btnMyCars, 3);
    }

    public void clickBtnDots() {
        clickWait(btnDots, 3);
    }

    public void clickBtnRegistration() {
        clickWait(btnRegistration, 3);
    }

    public void clickBtnLogin() {
        clickWait(btnLogin, 3);
    }

    public boolean textInElementPresent_popUpMessageSuccess(String text) {
        return textInElementPresent(popUpMessageSuccess, text, 3);
    }
}
