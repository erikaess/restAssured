package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreateAccountTest {
    @Test
    public void testMethod(){
        //definim configurariile pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        //definim requestul
        JSONObject createUserBody= new JSONObject();
        createUserBody.put("userName","erika"+System.currentTimeMillis());
        createUserBody.put("password","Y$Cs2AsjST");
        requestSpecification.body(createUserBody.toString());
        //Interactionam cu response-ul
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        ResponseBody responseBody= response.getBody();
        //body() is used to set the request body, while getBody() is used to get the response body after executing a request.
        System.out.println(responseBody.asString());
        //asString() is used to specifically get the response body as a string, while toString() provides a string representation of the entire response object, which includes additional metadata along with the body content. If you only need the body content as a string, asString() is the appropriate choice.

    }


}
