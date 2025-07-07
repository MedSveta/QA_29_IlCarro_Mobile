package rest;

import api_rest.CarController;
import dto.CarDto;
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
}
