package com.pt.wm;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.client.WireMock.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test2 {

    private String ENDPOINT_BASE = "http://localhost:8001";

    @Test (enabled = false)
    public void demo1() throws InterruptedException {
        //Thread.sleep(5000);
        String request1 = ENDPOINT_BASE + "/users";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();

        Response response = request.get(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println("Msg --> " + response.getContentType());
        System.out.println("Response --> " + response.body().toString());

        ResponseBody body = response.getBody();

        System.out.println(response.jsonPath().get("firstname").toString());

    }



    @Test(enabled = false)
    public void demo2(){

        String request1 = ENDPOINT_BASE + "/get/this";
        System.out.println("End point to be hit --> " + request1);
        RequestSpecification request = RestAssured.given();

        Response response = request.get(request1);

        System.out.println("Status code --> " + response.getStatusCode());
        System.out.println("Response --> " + response.body().toString());
    }
}
