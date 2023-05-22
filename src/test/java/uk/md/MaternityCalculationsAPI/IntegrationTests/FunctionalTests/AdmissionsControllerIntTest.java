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
import org.springframework.util.Assert;
import uk.md.MaternityCalculationsAPI.Controllers.AdmissionsController;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)

/*
 * These Integration Tests: Check how the communication between the API to the other API and if they integrate
 * For Int Tests: I am not interested if it's doing what's expected. I just want to see if this Micro-Service connects
 *                to the other Micro-Service
 */
class AdmissionsControllerIntTest {
    // Just Checks if there is a body
    // Even an Empty List is "[]" which is not null
    @Test
    void test_api_to_api_admissions_list_body_not_null(){
        fail("Test Not Implemented");
    }
}