package rest;

import api_rest.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
    int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("mango_plumo"+i+"@cmail.com")
                .password("Qwerty123!")
                .firstName("Mango")
                .lastName("Plumo")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL)
                .getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("mango_plumo"+i+"cmail.com")
                .password("Qwerty123!")
                .firstName("Mango")
                .lastName("Plumo")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(),
                400, "validate status code");
        ErrorMessageDtoString errorMessage = response.getBody()
                .as(ErrorMessageDtoString.class);
        System.out.println(errorMessage);
        softAssert.assertEquals(errorMessage.getError(), "Bad Request", "validate error");
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("well-formed email address"),
                "validate message");
        softAssert.assertAll();
    }
    @Test
    public void registrationNegativeTest_EmptyFieldFirstName(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("mango_plumo"+i+"@cmail.com")
                .password("Qwerty123!")
                .firstName("")
                .lastName("Plumo")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(),
                400, "validate status code");
        ErrorMessageDtoString errorMessage = response.getBody()
                .as(ErrorMessageDtoString.class);
        System.out.println(errorMessage);
        softAssert.assertEquals(errorMessage.getError(), "Bad Request", "validate error");
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("must not be blank"),
                "validate message");
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_protocolNotSecure_Http() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("mango_plumo"+i+"@cmail.com")
                .password("Qwerty123!")
                .firstName("Mango")
                .lastName("Plumo")
                .build();
        Assert.assertEquals(registrationLoginHTTP(user, REGISTRATION_URL)
                .getStatusCode(), 200);
    }
}
