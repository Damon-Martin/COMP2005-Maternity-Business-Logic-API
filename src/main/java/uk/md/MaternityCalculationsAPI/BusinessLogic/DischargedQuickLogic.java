package uk.md.MaternityCalculationsAPI.BusinessLogic;

import java.util.Date;

public class DischargedQuickLogic {


    // Checks how many days have passed
    public Integer calculateDaysInHospital(Date start, Date end){
        return null;
    }

    // Checks if the Days are Less than or Equal to 3 days
    public Boolean isQuick(Integer daysAdmitted){
        return daysAdmitted <= 3;
    }

    // Checks if the JSON Admission Data is Valid Data
    public Boolean isStartDateBeforeEndDate(Date start, Date end){
        return false;
    }
}
