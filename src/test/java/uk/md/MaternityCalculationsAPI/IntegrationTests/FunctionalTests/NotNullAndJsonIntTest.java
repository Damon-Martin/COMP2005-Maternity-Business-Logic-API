package uk.md.MaternityCalculationsAPI.IntegrationTests.FunctionalTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.md.MaternityCalculationsAPI.Controllers.AdmissionsController;
import static org.junit.jupiter.api.Assertions.fail;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
class NotNullAndJsonIntTest {
    @Autowired
    MockMvc mvc;

    // Checks GET request is working
    // If: Empty OR String has an ID (Not Empty) --> Test Pass
    // Else: Test Fail
    @Test
    void test_quick_discharge_IsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void test_quick_discharge_is_JSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }


    @Test
    void test_patients_seen_by_staff_IsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/PatientsSeenByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void test_patients_seen_by_staff_is_JSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/PatientsSeenByStaff");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

    @Test
    void test_avg_duration_by_staff_IsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void test_avg_durationByStaff_is_JSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }

    @Test
    void test_busiest_day_of_week_IsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void test_busiest_day_of_week_is_JSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/BusiestDayOfWeek");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json", res.getResponse().getContentType());
    }
}