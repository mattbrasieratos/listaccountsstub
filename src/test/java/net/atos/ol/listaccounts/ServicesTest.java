package net.atos.ol.listaccounts;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.net.URL;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ServicesTest {

  private TestData testData = new TestData();

  @Deployment
  public static Archive<?> buildTestArchive() {
    final WebArchive archive = ShrinkWrap.create(WebArchive.class, "listaccounts-test.war");
    archive.addClasses(Services.class, ServicesImpl.class, TestData.class);
    archive.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    return archive;
  }

  @Test
  public void frameworkTest() {
    assertTrue(true);
  }

  @Test
  @RunAsClient
  public void getOneAccount(@ArquillianResource URL url) {
      given().
      when().
        get(url.toExternalForm() + "b2crestapi/1/principaltocashaccountlinks?maxResults=1").
      then().
        assertThat().statusCode(Response.Status.OK.getStatusCode()).
        assertThat().body(equalTo(testData.getData("1")));
  }

  @Test
  @RunAsClient
  public void getTwoAccounts(@ArquillianResource URL url) {
    given().
            when().
            get(url.toExternalForm() + "b2crestapi/2/principaltocashaccountlinks?maxResults=2").
            then().
            assertThat().statusCode(Response.Status.OK.getStatusCode()).
            assertThat().body(equalTo(testData.getData("2")));
  }

  @Test
  @RunAsClient
  public void getNoCustomer(@ArquillianResource URL url) {
    given().
            when().
            get(url.toExternalForm() + "b2crestapi/99/principaltocashaccountlinks?maxResults=1").
            then().
            assertThat().statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }


}
