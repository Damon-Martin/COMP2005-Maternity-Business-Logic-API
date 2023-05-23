package uk.md.MaternityCalculationsAPI.UnitTests;

import java.time.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.md.MaternityCalculationsAPI.BusinessLogic.AvgDurationByStaffLogic;

import java.util.HashMap;
import java.util.Map;

public class AvgDurationByStaffLogicTest {

    @Test
    void test_mean_duration_all_5_mins() {
        // Arrange 3x 5 mins durations
        Map<Integer, Duration> cumulative = new HashMap<Integer, Duration>();
        for (int i = 0; i < 3; i++) {
            Duration temp = Duration.ofMinutes(5);
            cumulative.put(i, temp);
        }

        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration mean = logicObj.meanDuration(cumulative);

        Assertions.assertEquals(5, mean.toMinutes());
    }

    @Test
    void test_mean_duration_5_items_randomly() {
        // Arrange 3x 5 mins durations
        Map<Integer, Duration> cumulative = new HashMap<Integer, Duration>();
        cumulative.put(0 , Duration.ofSeconds(9090));
        cumulative.put(5 , Duration.ofSeconds(500));
        cumulative.put(9 , Duration.ofSeconds(82828));
        cumulative.put(10 , Duration.ofSeconds(4000));

        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration mean = logicObj.meanDuration(cumulative);

        Assertions.assertEquals(24104, mean.toSeconds());
    }

    @Test
    void addingDurations() {
        // Arranging
        Duration num1 = Duration.ofSeconds(120);
        Duration num2 = Duration.ofSeconds(8000);

        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
        Duration expected = logicObj.add2Durations(num1, num2);

        Assertions.assertEquals(8120, expected.toSeconds());
    }

}
