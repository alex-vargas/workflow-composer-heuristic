package com.utep.ilink.swim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.utep.ilink.swim.config.MainConfig;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AppTest {

    private static final DropwizardAppExtension<MainConfig> EXT = new DropwizardAppExtension<>(
            App.class,
            "settings.yml");

    private final String url = String.format("http://localhost:%d/compose", EXT.getLocalPort());

    private final Client client = EXT.client();

    private String callWebservice(String componentCatalogFilePath, String requestFilePath) {
        String componentCatalog, request;
        String responseBody = "";

        try {
            // Reading component catalog
            componentCatalog = new String(Files.readAllBytes(Paths.get(componentCatalogFilePath)),
                    StandardCharsets.UTF_8);
            // Reading request (contains available input and exected output)
            request = new String(Files.readAllBytes(Paths.get(requestFilePath)), StandardCharsets.UTF_8);

            // Creating request body
            MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
            formData.add("request", request);
            formData.add("modelcatalog", componentCatalog);

            // Calling webservice
            Response response = client.target(url).request().post(Entity.form(formData));

            // Getting response
            String responseHeaders = response.getStringHeaders().toString();
            responseBody = response.readEntity(String.class);

            // System.out.println("Response Headers: " + responseHeaders);
            // System.out.println("Response Body: " + responseBody);

        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }
        return responseBody;
    }

    @Test
    public void eScience2022AbstractTestCase() {
        String testCaseName = "eScience2022";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));
    }

    @Test
    public void noComponentsTestCase() {
        String testCaseName = "noComponents";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));

    }

    @Test
    public void noTargetVariableTestCase() {
        String testCaseName = "noTargetVariables";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));

    }

    /**
     * There is no component that generate target data
     */
    @Test
    public void noComponentTestCase() {
        String testCaseName = "noComponent";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));

    }

    /**
     * There is a component that generates data, however, there is no path to that component
     */
    @Test
    public void noPathTestCase() {
        String testCaseName = "noPath";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));

    }

    /**
     * Most promising path is select following heuristic, however, it is not the shortest (optimal path)
     */
    @Test
    public void mostPromisingNotShortestTestCase() {
        String testCaseName = "mostPromisingNotShortest";
        System.out.println("Running test: " + testCaseName);

        String modelCatalogFilePath = "tests/" + testCaseName + "/component_catalog.json";
        String requestFilePath = "tests/" + testCaseName + "/request.json";
        String workflowPlanFilePath = "tests/" + testCaseName + "/workflow_plan.json";

        String workflowPlan = "";

        // Reading expected output
        try {
            workflowPlan = new String(Files.readAllBytes(Paths.get(workflowPlanFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Couldn't read testing files");
            e.printStackTrace();
        }

        assertEquals(workflowPlan, callWebservice(modelCatalogFilePath, requestFilePath));

    }

}
