package com.pt.wm;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestClassics {

    private static String HOST ="localhost";
    private static int PORT = 8001;
    private static WireMockServer wmServer = new WireMockServer(PORT);
    public static String ENDPOINT_BASE = "http://" + HOST + ":" + PORT;


    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test --> Starting WM Server");

        wmServer.start();
        WireMock.configureFor(HOST, PORT);

        /*ResponseDefinitionBuilder mockResponse1 = new ResponseDefinitionBuilder();
        mockResponse1.withStatus(201);
        mockResponse1.withBodyFile("response1.json");
        mockResponse1.withHeader("Content-Type", "application/json");
        WireMock.givenThat(WireMock.get("/users").willReturn(mockResponse1));
        */
        if (wmServer.isRunning())
            System.out.println("Started WireMock");
    }

    @BeforeMethod
    public void beforeTestMethod(Method method){

        Test test = method.getAnnotation(Test.class);
        System.out.println("Test Description --> " + test.description());

    }

    @AfterTest
    public  void afterTest(){
        if (wmServer.isRunning())
        {
            wmServer.stop();
            System.out.println("Stopped WIREMOCK");
        }
    }

    @AfterSuite
    public void afterSuiteRun(ITestContext context){
        System.out.println("*************");
        System.out.println("Tests PASSED  --> " + context.getPassedTests().size());
        System.out.println("Tests FAILED  --> " + context.getFailedTests().size());
        System.out.println("Tests SKIPPED  --> " + context.getSkippedTests().size());
    }
}
