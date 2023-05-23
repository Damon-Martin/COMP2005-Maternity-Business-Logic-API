package uk.md.MaternityCalculationsAPI.UnitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AvgDurationByStaffLogicTest {
    @Test
    void test_check_normal_date_duration_hours_minutes_calculator() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);


        Assertions.fail("Normal Date Duration Calc: Not Implemented");
    }

    // like 11:30pm to 12:30pm
    // Corner Case
    @Test
    void test_check_normal_date_short_duration_over_night_calculator() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);


        Assertions.fail("Normal Date Duration Calc: Not Implemented");
    }

    // Extreme Case: Corner Case
    // Asserting Duration is at least greater than a few thousand years
    // Still Gives Validation
    @Test
    void test_check_max_date_duration_difference_calculator() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(-999999999, 1, 1, 0, 0, 1);
        LocalDateTime endTime = LocalDateTime.of(999999999, 12, 31, 23, 59, 59);

        Assertions.fail("Normal Date Duration Calc: Not Implemented");
    }

    // Corner Case
    // using 1 Sec diff: more standard corner case
    @Test
    void test_check_min_date_duration_difference_calculator_1() {
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 1);

        Assertions.fail("Normal Date Duration Calc: Not Implemented");
    }
    
    // Corner Case
    // using 1 nan sec diff
    @Test
    void test_check_min_date_duration_difference_calculator_2() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0, 1);

        Assertions.fail("Normal Date Duration Calc: Not Implemented");
    }
}
