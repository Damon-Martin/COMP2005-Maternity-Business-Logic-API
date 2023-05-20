package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Models.DischargedQuick;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DischargedQuickLogic {


    // Checks how many days have passed
    public Integer calculateDaysInHospital(LocalDateTime startDate, LocalDateTime endDate){
        long daysPassedLong = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        return (int) daysPassedLong;
    }

    // Checks if the Days are Less than or Equal to 3 days
    public Boolean isQuick(Integer daysAdmitted){
        return daysAdmitted <= 3;
    }

    // Checks if the JSON Admission Data is Valid Data
    public Boolean isStartDateBeforeEndDate(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.isBefore(endDate);
    }

    // We can mock allAdmissions for Testing
    // This is a Component: It combines multiple Units together
    public List<DischargedQuick> calculateDischargedQuick(List<Admission> allAdmissions)  {
        List<DischargedQuick> dischargedQuickList = new ArrayList<DischargedQuick>();
        allAdmissions.forEach(Admission -> {
            LocalDateTime currentStartDate = Admission.getAdmissionDate();
            LocalDateTime currentEndDate = Admission.getDischargeDate();

            Integer daysAdmitted = calculateDaysInHospital(currentStartDate, currentEndDate);

            // Check start is before End Date
            if (isQuick(daysAdmitted) && isStartDateBeforeEndDate(currentStartDate, currentEndDate)) {
                // Perform Calculations & Build Object
                DischargedQuick fastPatientCase = new DischargedQuick();
                fastPatientCase.setPatientID(Admission.getPatientID());
                fastPatientCase.setAdmissionID(Admission.getId());

                // Fetch Patient Name
                GetApiEntities EntityHandler = new GetApiEntities();
                try {
                    HttpResponse<String> res = EntityHandler.getPatientById(fastPatientCase.getPatientID());
                    Patient currentPatient = EntityHandler.parsePatientById(res);

                    fastPatientCase.setForename(currentPatient.getForename());
                    fastPatientCase.setSurname(currentPatient.getSurname());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Adding To List
                dischargedQuickList.add(fastPatientCase);
            }
        });
        return dischargedQuickList;
    }
}
