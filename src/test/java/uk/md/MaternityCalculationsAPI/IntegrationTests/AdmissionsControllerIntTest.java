package uk.md.MaternityCalculationsAPI.IntegrationTests;

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
import org.springframework.util.Assert;
import uk.md.MaternityCalculationsAPI.Controllers.AdmissionsController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
class AdmissionsControllerIntTest {
    @Autowired
    MockMvc mvc;

    // Checks GET request is working
    // If: Empty OR String has an ID (Not Empty) --> Test Pass
    // Else: Test Fail
    @Test
    void QuickDischargeIsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void QuickDischargeIsJSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json;charset=UTF-8", res.getResponse().getContentType());
    }


    @Test
    void PatientsSeenByStaffIsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/PatientsSeenByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void PatientsSeenByStaffIsJSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/PatientsSeenByStaff");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json;charset=UTF-8", res.getResponse().getContentType());
    }

    @Test
    void AvgDurationByStaffIsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void AvgDurationByStaffIsJSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json;charset=UTF-8", res.getResponse().getContentType());
    }

    @Test
    void BusiestDayOfWeekIsHttpSuccess() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/AvgDurationByStaff");

        MvcResult res = this.mvc.perform(req).andExpect(status().isOk()).andReturn();
    }

    @Test
    void BusiestDayOfWeekIsJSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/BusiestDayOfWeek");

        MvcResult res = this.mvc.perform(req).andReturn();

        Assertions.assertEquals("application/json;charset=UTF-8", res.getResponse().getContentType());
    }
}