package com.pt.wm;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Json;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;


public class WMHttpMethodsTest extends TestClassics {

    @Test(description = "SC1 - Get users list")
    public void getUsersList(){
        String request1 = ENDPOINT_BASE + "/api/users";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();

        Response response = request.get(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println("Response --> " + response.jsonPath().get("email"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC2 - Get users by ID - Result Matching")
    public void getUserbById(){
        String request1 = ENDPOINT_BASE + "/api/users/2";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();

        Response response = request.get(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println("Response --> " + response.jsonPath().get("email"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC3 - Get user by Id - Result Not matching")
    public void getUserbByIdWithMatchingSearch(){
        String request1 = ENDPOINT_BASE + "/api/users/345";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();

        Response response = request.get(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println("Response --> " + response.jsonPath().get("error"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC4 - POST User - User is created")
    public void postUser(){

        JSONObject userData = new JSONObject();
        userData.put("accountStatus", "INITIATE");
        userData.put("firstName", "Priya");

        String request1 = ENDPOINT_BASE + "/api/users/initiate";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(userData.toJSONString());
        //request.log().all();

        Response response = request.post(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());
        //System.out.println("Msg " + response.jsonPath().get("msg"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC5 - PUT User - User is updated")
    public void putUserUpdate(){

        JSONObject userData = new JSONObject();
        userData.put("id", "2");
        JSONObject address = new JSONObject();
        address.put("line1" , "shimpoli"); //Matching
        userData.put("address", address);

        String request1 = ENDPOINT_BASE + "/api/users/2";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(userData.toJSONString());
        //request.log().all();

        Response response = request.put(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC6 - PUT User - Resource Not matching")
    public void putUserNotMatching(){

        JSONObject userData = new JSONObject();
        userData.put("id", "2");
        JSONObject address = new JSONObject();
        address.put("line1" , "shimpoli");
        userData.put("address", address);

        String request1 = ENDPOINT_BASE + "/api/users/345";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(userData.toJSONString());
        //request.log().all();

        Response response = request.put(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());
        //System.out.println("Msg " + response.jsonPath().get("msg"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC7 - DELETE user")
    public void deleteResource(){

        String request1 = ENDPOINT_BASE + "/api/users/2";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.delete(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getStatusLine());
        //System.out.println("Msg " + response.jsonPath().get("msg"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC8 - SEARCH USER - Matching results")
    public void searchMatchingRequest(){

        String request1 = ENDPOINT_BASE + "/api/users/search";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("id", "2");
        //request.log().all();

        Response response = request.get(request1);


        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getStatusLine());
        //System.out.println("Msg " + response.jsonPath().get("msg"));
        System.out.println("***********************");
    }

    @Test (enabled = true, description = "SC9 - SEARCH USER - NOT Matching results")
    public void searchNOTMatchingRequest(){

        String request1 = ENDPOINT_BASE + "/api/users/search";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("id", "abcd");
        request.log().all();

        Response response = request.get(request1);


        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println(response.getStatusLine());
        //System.out.println("Msg " + response.jsonPath().get("msg"));
        System.out.println("***********************");
    }
}
