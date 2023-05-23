package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Lots of good for edge cases of dates
public class AvgDurationByStaffLogic {

    public Duration meanDuration(Map<Integer,Duration> patientCumulativeTime) {
        long totTime = 0;

        for (Duration duration : patientCumulativeTime.values()) {
            totTime += duration.getSeconds();
        }

        long mean = totTime / patientCumulativeTime.size();
        return Duration.ofSeconds(mean);
    }

    // Test this function Heavily
    // Assuming Date Order is Already Checked
    // Use Edge Cases: 1. max distance in time. 2 min distance in time
    public Duration caclulateSingleDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    public Duration add2Durations(Duration item1, Duration item2) {
        return item1.plus(item2);
    }

    // Duration: Final Duration Calculated
    // Uses all other functions in class
    // Will be Displayed as a String with Days, Hours, Minutes in Controller
    public Duration calculateDurationByStaffID(int EmployeeID, List<Allocation> allocations) throws IOException, InterruptedException {
        // Duration is the cumulative
        Map<Integer, Duration> PatientDurationMap = new HashMap<>();
        // Blocks Duplicate Allocation for this Employee
        List<Integer> visitedAllocations = new ArrayList<>();

        GetApiEntities httpEntityObj  = new GetApiEntities();

        for ( Allocation currentShift: allocations ) {
            if (currentShift.getEmployeeID() == EmployeeID && !visitedAllocations.contains(currentShift.getAdmissionID())) {
                // Get this Allocation
                Integer AdmissionID = currentShift.getAdmissionID();

                HttpResponse<String> res = httpEntityObj.getAdmissionById(AdmissionID);
                visitedAllocations.add(AdmissionID);

                if (res.statusCode() == 200) {
                    Admission currentIncident = httpEntityObj.parseSingleAdmission(res);
                    Integer PatientID = currentIncident.getPatientID();

                    Duration currentDuration = caclulateSingleDuration(currentIncident.admissionDate, currentIncident.dischargeDate);

                    if (PatientDurationMap.containsKey(PatientID)) {
                        // Calculate new duration
                        Duration prevSumDuration = PatientDurationMap.get(PatientID);

                        // Update the Map
                        PatientDurationMap.put( PatientID , add2Durations(prevSumDuration, currentDuration) );
                    }
                    else {
                        PatientDurationMap.put(PatientID, currentDuration);
                    }
                }
            }
        }

        return meanDuration(PatientDurationMap);
    }
}
