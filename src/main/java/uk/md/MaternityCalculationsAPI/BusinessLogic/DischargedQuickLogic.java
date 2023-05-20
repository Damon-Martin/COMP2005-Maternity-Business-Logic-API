package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.DischargedQuick;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DischargedQuickLogic {


    // Checks how many days have passed
    public Integer calculateDaysInHospital(LocalDateTime start, LocalDateTime end){
        return null;
    }

    // Checks if the Days are Less than or Equal to 3 days
    public Boolean isQuick(Integer daysAdmitted){
        return daysAdmitted <= 3;
    }

    // Checks if the JSON Admission Data is Valid Data
    public Boolean isStartDateBeforeEndDate(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.isBefore(endDate);
    }

    public List<DischargedQuick> calculateDischargedQuick(List<Admission> allAdmissions) {
        List<DischargedQuick> dischargedQuickList = new ArrayList<DischargedQuick>();
        allAdmissions.forEach(Admission -> {
            // Perform Calculations & Build Object

            // Check start is before End Date

            //calculateDaysInHospital(Admission.admissionDate, Admission.dischargeDate);

        });
        return null;
    }
}
