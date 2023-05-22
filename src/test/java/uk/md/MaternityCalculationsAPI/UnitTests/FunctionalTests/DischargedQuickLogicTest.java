package uk.md.MaternityCalculationsAPI.UnitTests.FunctionalTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.md.MaternityCalculationsAPI.BusinessLogic.DischargedQuickLogic;
import uk.md.MaternityCalculationsAPI.Models.DischargedQuick;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DischargedQuickLogicTest {
    // Initialising Tests: Reset Each Test to these defaults
    @BeforeEach
    void initRepeat() {
    }

    // Will check the Boolean Function: If it can detect a single instance of Start before End is Valid
    // Note: Will use dummy data of different instances to test this is working
    // Minutes, Hour and seconds will be truncated (Only cares about the days)
    // End date not included (+-1 day)
    @Test
    void test_admitted_before_discharged_checker_acceptable_input() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2023, 5, 2, 12, 50);
        LocalDateTime endDate = LocalDateTime.of(2023, 6, 15, 20, 30);

        int daysAdmitted = logicObj.calculateDaysInHospital(startDate, endDate);

        Assertions.assertEquals(44, daysAdmitted);
    }

    // Testing if it can handle years as 365 correctly
    // Minutes, Hour and seconds will be truncated (Only cares about the days)
    // End date not included (+-1 day)
    @Test
    void test_admitted_before_discharged_checker_acceptable_input_years_passed() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2023, 5, 2, 12, 50);
        LocalDateTime endDate = LocalDateTime.of(2025, 2, 15, 10, 45);

        int daysAdmitted = logicObj.calculateDaysInHospital(startDate, endDate);

        Assertions.assertEquals(655, daysAdmitted);
    }

    // Note: Will use dummy data of different instances to test this is working
    @Test
    void test_if_admitted_before_discharged_is_ignored_in_return_list() {
        fail("Test Not Implemented");
    }

    // Code Needs to be implemented to filter them out by PatientID
    // Make Array of Test Data with Duplicates
    // Basically pass in an array list of models into the function
    // Check if that the return array only has 1 model of each patient
    @Test
    void test_no_duplicate_patients_for_discharged_quickly() {
        // Arrange
        List<DischargedQuick> dummyListData = new ArrayList<DischargedQuick>();

        DischargedQuick patientInstance1 = new DischargedQuick();
        patientInstance1.setPatientID(1);
        patientInstance1.setNhsNumber("1111111");
        patientInstance1.setForename("Dennis");
        patientInstance1.setSurname("Smith");

        // Should be ignored
        DischargedQuick patientInstance2 = new DischargedQuick();
        patientInstance2.setPatientID(1);
        patientInstance2.setNhsNumber("1111111");
        patientInstance2.setForename("Dennis");
        patientInstance2.setSurname("Smith");

        DischargedQuick patientInstance3 = new DischargedQuick();
        patientInstance3.setPatientID(4);
        patientInstance3.setNhsNumber("191525");
        patientInstance3.setForename("David");
        patientInstance3.setSurname("Keene");

        dummyListData.add(patientInstance1);
        dummyListData.add(patientInstance2);
        dummyListData.add(patientInstance3);

        // Act
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        List<DischargedQuick> filtered_patient_li = logicObj.filter_patient_duplicates(dummyListData);

        // Assert no duplicates of Patient ID
        Assertions.assertEquals(2, filtered_patient_li.size());
    }

    @Test
    void test_is_quick_acceptable(){
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        Boolean isFast = logicObj.isQuick(2);
        Assertions.assertTrue(isFast);
    }

    @Test
    void test_is_quick_invalid(){
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        Boolean isFast = logicObj.isQuick(99);
        Assertions.assertFalse(isFast);
    }

    @Test
    void test_check_date_is_before_checker_acceptable() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2023,2,24,12,15);
        LocalDateTime endDate = LocalDateTime.of(2023,2,28,12,15);
        Boolean isBefore = logicObj.isStartDateBeforeEndDate(startDate, endDate);
        Assertions.assertTrue(isBefore);
    }

    @Test
    void test_check_date_is_before_checker_invalid() {
        DischargedQuickLogic logicObj = new DischargedQuickLogic();
        LocalDateTime startDate = LocalDateTime.of(2024,10,24,12,15);
        LocalDateTime endDate = LocalDateTime.of(2023,2,28,12,15);
        Boolean isBefore = logicObj.isStartDateBeforeEndDate(startDate, endDate);
        Assertions.assertFalse(isBefore);
    }

}