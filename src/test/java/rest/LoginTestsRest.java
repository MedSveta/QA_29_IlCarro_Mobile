package rest;

import api_rest.AuthenticationController;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    RegistrationBodyDto user;

    @BeforeMethod
    public void registrationUser(){
        int i = new Random().nextInt(1000);
        user = RegistrationBodyDto.builder()
                .username("mango_plumo"+i+"@cmail.com")
                .password("Qwerty123!")
                .firstName("Mango")
                .lastName("Plumo")
                .build();
        System.out.println("result registration --> "+
                registrationLogin(user, REGISTRATION_URL)
                        .getStatusCode());
    }

    @Test
    public void loginPositiveTest(){
        Assert.assertEquals(registrationLogin(user, LOGIN_URL)
                .getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_EmptyEmail(){
        user.setUsername("");
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginNegativeTest_UnregisteredUser_WrongEmail(){
        user.setUsername("pjki123@gmail.com");
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginNegativeTest_EmptyPassword(){
        user.setPassword("");
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(),
                401);
    }

    @Test
    public void loginNegativeTest_WrongPassword(){
        user.setPassword("Qwerty123");
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(),
                401);
    }
}
