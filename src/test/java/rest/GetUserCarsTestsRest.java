package rest;

import api_rest.CarController;
import dto.CarsDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserCarsTestsRest extends CarController {
    CarsDto cars;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void getUserCarsPositiveTest(){
        Response response = getUserCars();
        cars = response.getBody().as(CarsDto.class);
        System.out.println(cars);
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertTrue(cars.getCars()[0].getCarClass()
                .contains("C"), "validate carClass");
        softAssert.assertAll();
    }

    @Test
    public void getUserCarsNegativeTest_WrongUrl(){
       Response response =
               getUserCarsNegative_WrongUrl(LOGIN_URL);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
