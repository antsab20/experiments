package com.automation.resource;

import com.automation.flows.trial.CreateUserTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class TrialCreationEndpointTest {
    private TrialCreationEndpoint testSubject;

    @Captor
    private ArgumentCaptor<CreateUserTest> createUserMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        testSubject = new TrialCreationEndpoint();
    }

    @Test
    public void testCreateTrialTenantConnection() {
        given().contentType(MediaType.APPLICATION_JSON).when().post("/create-trial").then().statusCode(200).log().all();
    }

    @Test
    public void testCreateTrialTenantConnectionWithAuth(){
        given().auth().basic("admin","xxx").when().post("/create-trial").then().statusCode(200);
    }
}
