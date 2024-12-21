package com.theAPITesting.base;

import com.theAPITesting.asserts.AssertActions;
import com.theAPITesting.endpoints.APIConstants;
import com.theAPITesting.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public String baseUrl;
    @BeforeTest
    public void setup()
    {
        // BASE URL, Content Type JSON
        payloadManager = new PayloadManager();
        assertActions=new AssertActions();

        requestSpecification= RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
       /* baseUrl=APIConstants.BASE_URL;
        System.out.println("BaseUrl is: " +baseUrl);*/
//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();
    }

    public String getToken() {
        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload = payloadManager.setAuthPayload();

        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        // String Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;


    }

}
