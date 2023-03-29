package uk.md.MaternityAPI.UnitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.md.MaternityAPI.Controllers.AdmissionsController;

import static org.junit.jupiter.api.Assertions.*;

class AdmissionsControllerTest {
    // Initialising Tests: Reset Each Test to these defaults
    @BeforeEach
    void initRepeat() {
    }

    // Patient must be admitted before discharge
    // Performs the check if len(returnString) != 0
    @Test
    void admissionBeforeDischarge() {
        fail("Test Not Implemented");
    }

    // Patient must be admitted before discharge
    // Performs the check if len(returnString) != 0
    @Test
    void dischargeBeforeAdmission() {
        fail("Test Not Implemented");
    }
}