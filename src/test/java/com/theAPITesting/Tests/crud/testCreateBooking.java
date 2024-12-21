package com.theAPITesting.Tests.crud;

import com.theAPITesting.base.BaseTest;
import com.theAPITesting.endpoints.APIConstants;
import com.theAPITesting.pojos.BookingResponse;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

@Owner("Tester")
@TmsLink("https://google.com")
@Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
@Issue("JIRA_RBT-4")
//@Description("Verify that POST request is working fine.")
@Test(groups = "qa")
public class testCreateBooking extends BaseTest {
    public void testVerifyCreateBookingPOST01() {
        String basePathGet=APIConstants.CREATE_UPDATE_BOOKING_URL;
        System.out.println("BaseUrl is: " +baseUrl);
        System.out.println("BasePath is: " +basePathGet);

        requestSpecification.basePath(basePathGet);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Garima");
    }
}
