package uk.md.MaternityCalculationsAPI.IntegrationTests.NonFunctionalTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.md.MaternityCalculationsAPI.Controllers.AdmissionsController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
public class PerformanceTimeIntTest {
    @Autowired
    MockMvc mvc;

    // Testing the time taken of a Successful request
    // We wouldn't know it's Json unless it was successful
    // Checking if request time is below 1 second
    @Test
    void test_dischargedQuickly_below_1000ms() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");

        // Using System Clock for calculation
        long startTime = System.currentTimeMillis();
        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
        long endTime = System.currentTimeMillis();

        // Calculating time executed
        long timeExecuted = endTime - startTime;

        Assertions.assertTrue(timeExecuted < 1000);
        Assertions.assertEquals(200, res.getResponse().getStatus());
        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

    @Test
    void test_patientsSeen_below_1000ms() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("http://localhost:8080/api/PatientsSeen/%7Bid%7D?id=4");

        // Using System Clock for calculation
        long startTime = System.currentTimeMillis();
        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
        long endTime = System.currentTimeMillis();

        // Calculating time executed
        long timeExecuted = endTime - startTime;

        Assertions.assertTrue(timeExecuted < 1000);
        Assertions.assertEquals(200, res.getResponse().getStatus());
        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

    @Test
    void test_avgDurationByStaff_below_1000ms() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        // Using System Clock for calculation
        long startTime = System.currentTimeMillis();
        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
        long endTime = System.currentTimeMillis();

        // Calculating time executed
        long timeExecuted = endTime - startTime;

        Assertions.assertTrue(timeExecuted < 1000);
        Assertions.assertEquals(200, res.getResponse().getStatus());
        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

    
    @Test
    void test_busiestDayOfWeek_below_1000ms() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/BusiestDayOfWeek");

        // Using System Clock for calculation
        long startTime = System.currentTimeMillis();
        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
        long endTime = System.currentTimeMillis();

        // Calculating time executed
        long timeExecuted = endTime - startTime;

        Assertions.assertTrue(timeExecuted < 1000);
        Assertions.assertEquals(200, res.getResponse().getStatus());
        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

}
