package uk.md.MaternityCalculationsAPI.UnitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.md.MaternityCalculationsAPI.BusinessLogic.AvgDurationByStaffLogic;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationCalcWithCornerCasesTest {

    // Assuming Regular Working Day
    @Test
    void test_check_normal_date_duration_hours_minutes_calculator() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 23, 9, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 23, 17, 30, 0);

        // Act
        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration calculatedDuration = logicObj.caclulateSingleDuration(startTime, endTime);

        // Assert: 8hrs exact
        Assertions.assertEquals(8, calculatedDuration.toHours());
    }

    // like 11:30pm to 12:30pm
    // Mild: Corner Case
    @Test
    void test_check_normal_date_short_duration_over_night_calculator() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 7, 23, 23, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 7, 24, 1, 30, 0);

        // Act
        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration calculatedDuration = logicObj.caclulateSingleDuration(startTime, endTime);

        // Asserting 120 minutes exact
        Assertions.assertEquals(120, calculatedDuration.toMinutes());
    }
    
    // Corner Case
    // using 1 Sec diff: more standard corner case
    @Test
    void test_check_min_date_duration_difference_calculator_1() {
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 1);

        // Act
        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration calculatedDuration = logicObj.caclulateSingleDuration(startTime, endTime);

        // Assert: Asserting 1 nano sec diff
        Assertions.assertEquals(1, calculatedDuration.toSeconds());
    }
    
    // Corner Case
    // using 1 nan sec diff
    @Test
    void test_check_min_date_duration_difference_calculator_2() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 23, 18, 30, 0, 1);

        // Act
        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration calculatedDuration = logicObj.caclulateSingleDuration(startTime, endTime);

        // Assert
        Assertions.assertEquals(1, calculatedDuration.toNanos());
    }
}
