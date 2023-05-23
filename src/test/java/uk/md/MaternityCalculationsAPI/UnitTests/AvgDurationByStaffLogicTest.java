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
}
