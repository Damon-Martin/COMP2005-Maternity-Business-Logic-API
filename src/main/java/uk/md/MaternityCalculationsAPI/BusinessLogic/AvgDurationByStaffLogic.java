package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public Duration calculateDurationByStaffID(int EmployeeID, List<Allocation> allocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        // Duration is the cumulative
        Map<Integer, Duration> PatientDurationMap = new HashMap<>();

        allocations.forEach( Allocation -> {
            if (Allocation.getEmployeeID() == EmployeeID) {
                // This is our Employee so get the Admission with patient data
                Integer AdmissionID = Allocation.admissionID; // Corresponding Admission

                // Fetching This Admission
                GetApiEntities httpObj = new GetApiEntities();
                try {
                    HttpResponse<String> res = httpObj.getAdmissionById(AdmissionID);
                    if (res.statusCode() == 200) {
                        Admission currentAdmission = httpObj.parseSingleAdmission(res);
                        Integer PatientID = currentAdmission.getPatientID();

                        AvgDurationByStaffLogic logicObj = new AvgDurationByStaffLogic();
                        DischargedQuickLogic otherTimeLogicObj = new DischargedQuickLogic();

                        // Has to be Valid Date Order or Ignored
                        if (otherTimeLogicObj.dateOrderCorrect(currentAdmission.admissionDate, currentAdmission.dischargeDate)) {


                            Duration currentDiff = logicObj.caclulateSingleDuration(currentAdmission.admissionDate, currentAdmission.dischargeDate);


                            // Checking if Patient (Key) Already Exists
                            if (PatientDurationMap.containsKey(PatientID)) {
                                // get Prev Value
                                Duration prevDuration = PatientDurationMap.get(PatientID);
                                // Add the 2 durations and update the value
                                Duration newDuration = add2Durations(prevDuration, currentDiff);

                                // Updating Map
                                PatientDurationMap.put(PatientID, newDuration);
                            }
                            // PatientID not in List: Just pop the Key & Value
                            else {
                                PatientDurationMap.put(PatientID, currentDiff);
                            }
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return meanDuration(PatientDurationMap);
    }
}
