package uk.md.MaternityAPI.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.md.MaternityAPI.Controllers.AdmissionsController;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
class AdmissionsControllerIntTest {
    @Autowired
    MockMvc mvc;

    // Checks GET request is working
    @Test
    void getAdmissionsJSON() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/Admissions/DischargedQuick");
        MvcResult res = mvc.perform(req).andReturn();
        assertEquals("JSON of Discharged Patients", res.getResponse().getContentAsString() );
    }
}