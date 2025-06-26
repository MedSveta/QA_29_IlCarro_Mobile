package tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {
    @BeforeMethod
    public void openLoginScreen(){
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
    }

    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("sveta123656@gmail.com")
                .password("AAaa1234!").build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new SearchScreen(driver)
                .textInElementPresent_popUpMessageSuccess("Login success!"));
    }

    @Test
    public void loginNegativeTest_WrongEmail(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("sveta123656@gmai.com")
                .password("AAaa1234!").build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmptyFieldPassword(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("sveta123656@gmai.com")
                .password("").build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("All fields must be filled and agree terms"));
    }
}
