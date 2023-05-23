package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

// Lots of good for edge cases of dates
public class AvgDurationByStaffLogic {
    private HashMap<Integer, Duration> patientCumulativeDuration = new HashMap<>();

    // Test this function Heavily
    // Assuming Date Order is Already Checked
    // Use Edge Cases: 1. max distance in time. 2 min distance in time
    public Duration caclulateSingleDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    // Duration: Final Duration Calculated
    // Uses all other functions in class
    // Will be Displayed as a String with Days, Hours, Minutes in Controller
    public Duration calculateDurationByStaffID(int EmployeeID, List<Allocation> allAllocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        return null;
    }
}
