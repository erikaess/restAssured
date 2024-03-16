package Tests;

import ObjectData.RequestObject.RequestAccount;
import ObjectData.ResponseObject.ResponseAccountGetSuccess;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseTokenSuccess;
import PropertyUtility.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest {
    public String userId;
    public RequestAccount requestAccountBody;
    public String token;
    @Test
    public void testMethod() {
        System.out.println("Step 1 : create new account");
        createAccount();
        System.out.println("Step 2 : Generate new token");
        generateToken();
        System.out.println("Step 3 : Get new account");
        getSpecificAccount();
    }
    public void createAccount(){
        //definim configurariile pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        //definim requestul ~ serializam
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
        requestSpecification.body(requestAccountBody);

        //Interactionam cu response-ul ~ deserializam
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        //validam responseBody-ul
        //va fi un obiect de tipul responseAccountSuccess care va lua forma in functie de cum vine body-ul de la response
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println(responseAccountBody.getUserID());
        userId = responseAccountBody.getUserID();
        Assert.assertEquals(responseAccountBody.getUsername(), requestAccountBody.getUsername());
    }
    public void generateToken(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        requestSpecification.body(requestAccountBody);
        Response response = requestSpecification.post("Account/v1/GenerateToken");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        System.out.println(responseTokenSuccess.getToken());
        token=responseTokenSuccess.getToken();

        //System.out.println(responseTokenSuccess.getStatus());
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");

        //System.out.println(responseTokenSuccess.getResult());
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully");
    }
    public void getSpecificAccount(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        requestSpecification.header("Authorization","Bearer "+token);

        Response response = requestSpecification.get("Account/v1/User/"+userId);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
        Assert.assertEquals(responseAccountGetSuccess.getUsername(), requestAccountBody.getUsername());
    }


}
