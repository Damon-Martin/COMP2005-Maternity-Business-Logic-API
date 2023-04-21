package uk.md.MaternityCalculationsAPI.IntegrationTests;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
class AdmissionsControllerIntTest {
    @Autowired
    MockMvc mvc;

    // Checks GET request is working
    // If: Empty OR String has an ID (Not Empty) --> Test Pass
    // Else: Test Fail
    @Test
    void getQuickDischargeHasID() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/Admissions/DischargedQuick");
        MvcResult res = mvc.perform(req).andReturn();

        // Response Body to String
        String resBody = res.getResponse().getContentAsString();

        // Checking Response Body has an id or is empty
        boolean isEmpty = resBody.length() == 0;
        boolean containsID = resBody.contains("id");

        boolean isAcceptable = isEmpty || containsID;
        assertTrue(isAcceptable);
    }
}