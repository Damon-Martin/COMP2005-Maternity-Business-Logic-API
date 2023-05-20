package uk.md.MaternityCalculationsAPI.UnitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.md.MaternityCalculationsAPI.BusinessLogic.DischargedQuickLogic;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DischargedQuickLogicTest {
    // Initialising Tests: Reset Each Test to these defaults
    @BeforeEach
    void initRepeat() {
    }

    // Will check the Boolean Function: If it can detect a single instance of Start before End is Valid
    // Note: Will use dummy data of different instances to test this is working
    // Minutes, Hour and seconds will be truncated (Only cares about the days)
    // End date not included (+-1 day)
    @Test
    void test_admitted_before_discharged_checker_acceptable_input() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2023, 5, 2, 12, 50);
        LocalDateTime endDate = LocalDateTime.of(2023, 6, 15, 20, 30);

        int daysAdmitted = logicObj.calculateDaysInHospital(startDate, endDate);

        Assertions.assertEquals(44, daysAdmitted);
    }

    // Testing if it can handle years as 365 correctly
    // Minutes, Hour and seconds will be truncated (Only cares about the days)
    // End date not included (+-1 day)
    @Test
    void test_admitted_before_discharged_checker_acceptable_input_years_passed() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2023, 5, 2, 12, 50);
        LocalDateTime endDate = LocalDateTime.of(2025, 2, 15, 10, 45);

        int daysAdmitted = logicObj.calculateDaysInHospital(startDate, endDate);

        Assertions.assertEquals(807, daysAdmitted);
    }

    // Note: Will use dummy data of different instances to test this is working
    @Test
    void test_if_admitted_before_discharged_is_ignored_in_return_list() {
        fail("Test Not Implemented");
    }
}