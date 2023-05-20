package uk.md.MaternityCalculationsAPI.FunctionalTests;

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
class DischargedQuicklyFunctionalTests {
    @Autowired
    MockMvc mvc;

    // Checks GET request is working
    // If: Empty OR String has an ID (Not Empty) --> Test Pass
    // Else: Test Fail
    // AdmissionID is optional
    // "[{PatientID, AdmissionID, PatientFName, PatientSurname},{}...]"
    @Test
    void test_quick_discharge_is_correct_json_format() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");


        fail("Not Yet Implemented");
    }

    @Test
    void test_quick_discharge_blocks_invalid_date_orders() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");


        fail("Not Yet Implemented");
    }
}