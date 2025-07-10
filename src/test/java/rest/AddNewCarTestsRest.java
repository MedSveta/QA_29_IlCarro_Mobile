package rest;

import api_rest.CarController;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import enums.Fuel;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("987-" + i)
                .manufacture("Mazda")
                .model("X3")
                .year("2023")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("B")
                .pricePerDay(57.9)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().print());
        softAssert.assertTrue(response.getBody().print().contains("Car added"));
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WrongAuthorization() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("987-" + i)
                .manufacture("Mazda")
                .model("X3")
                .year("2023")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("B")
                .pricePerDay(57.9)
                .city("Haifa")
                .build();
        Response response = addNewCarWrongToken(car, "qwer4567");
        System.out.println(response.print());
        softAssert.assertEquals(response.getStatusCode(), 401, "validate status code");
        softAssert.assertTrue(response.getBody().print().contains("strings must contain exactly 2"), "validate message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WOSerialNumber() {

        CarDto car = CarDto.builder()
                .manufacture("Mazda")
                .model("X3")
                .year("2023")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("B")
                .pricePerDay(57.9)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        System.out.println(response.print());
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        softAssert.assertTrue(response.getBody().print().contains("must not be blank"), "validate message");
        softAssert.assertTrue(errorMessageDtoString.getError().equals("Bad Request"), "validate error");
        softAssert.assertAll();
    }
}
