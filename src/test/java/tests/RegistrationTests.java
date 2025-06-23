package tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .firstname(i+"Rita")
                .lastname(i+"Fanny")
                .username(i+"rita_family@gmail.com")
                .password("Qwerty123!")
                .build();

        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(searchScreen
                .textInElementPresent_popUpMessageSuccess("Registration success!"));
    }
    @Test
    public void registrationNegativeTest_WOCheckBox(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .firstname(i+"Rita")
                .lastname(i+"Fanny")
                .username(i+"rita_family@gmail.com")
                .password("Qwerty123!")
                .build();

        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegForm(user);
        registrationScreen.clickBtnYalla();

    }
}
