package junit.org.rapidpm.vaadin.jumpstart.charts.microservice.bootstrap.v001;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.optionals.metrics.health.rest.SessionHealth;
import org.rapidpm.microservice.optionals.metrics.health.rest.api.SessionHealthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import junit.org.rapidpm.vaadin.jumpstart.charts.microservice.MicroserviceBaseTest;

/**
 * Created by svenruppert on 04.04.17.
 */
public class StartupActionTest extends MicroserviceBaseTest {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(StartupTestAction.class);

    public static final String START_UP_ACTION = "StartUpAction";

    @Test
    public void test001()
        throws Exception {

        // Testrequest to see if MicroService is up and running
        final String generateBasicReqURL = generateBasicReqURL(
            SessionHealth.class);
        Client client = ClientBuilder.newClient();
        LOGGER.info("generateBasicReqURL = " + generateBasicReqURL);
        final Invocation.Builder authcode = client.target(generateBasicReqURL)
            .request();
        String response = authcode.get(String.class);
        Gson gson = new Gson();
        SessionHealthInfo[] sessionHealthInfo = gson
            .fromJson(response, SessionHealthInfo[].class);

        Assert.assertEquals(0L, sessionHealthInfo[0].activeSessionCount);

        client.close();
    }

    @Test
    public void test002()
        throws Exception {
        final String basicReqURL = generateBasicReqURL(TestRessource.class);
        final Response response = ClientBuilder.newClient().target(basicReqURL)
            .request().get();

        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());

        Assert.assertEquals(StartupTestAction.class.getSimpleName(),
            response.readEntity(String.class));
    }

    @Path("/OverviewTest")
    public static class TestRessource {
        @GET()
        public String doWork() {
            return System.getProperty(START_UP_ACTION);
        }
    }

    public static class StartupTestAction implements Main.MainStartupAction {

        private static final Logger LOGGER = LoggerFactory
            .getLogger(StartupTestAction.class);

        @Override
        public void execute(Optional<String[]> args) {
            // Start here the MicroService bootstraping stuff
            LOGGER.debug("Started with MicroService bootstrapping");

            System
                .setProperty(START_UP_ACTION, this.getClass().getSimpleName());

            LOGGER.debug("Stopped with MicroService bootstrapping");
        }

    }
}
