package uk.md.MaternityCalculationsAPI.UnitTests.FunctionalTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.md.MaternityCalculationsAPI.BusinessLogic.PatientsSeenLogic;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiLists;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;
import uk.md.MaternityCalculationsAPI.Models.PatientCustom;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientsSeenLogicTest {

    // Dummy Instance: Default
    List<Allocation> dummyDataAllocations = new ArrayList<Allocation>();
    List<Admission> dummyDataAdmissions = new ArrayList<Admission>();
    List<PatientCustom> dummyDataPatients = new ArrayList<PatientCustom>();

    // Mocking Responses & Parsing into Lists of Obj
    void PatientsSeenLogicTest() {

        // 3 Allocations total
        // Emp1: P1 admissions 2x
        // Emp2: P1 admissions: 1x, P2 admissions 1x, P3 admissions 1x

        // 3 Patients total (P1: 3 Admissions, P2: 1 Admission, P3: 1 Admission)
        String rawPatientsJson = "[ { \"id\": 1, \"surname\": \"Smith\", \"forename\": \"Jack\", \"nhsNumber\": \"999234\" }, [ { \"id\": 2, \"surname\": \"Thomas\", \"forename\": \"Martin\", \"nhsNumber\": \"892134\" } , [ { \"id\": 3, \"surname\": \"Chadwick\", \"forename\": \"Alex\", \"nhsNumber\": \"829321\" } ]] ]";

        // 2 Employees total
        String rawEmployeeJson = "[ { \"id\": 1, \"surname\": \"Finley\", \"forename\": \"Sarah\" }, { \"id\": 2, \"surname\": \"Jackson\", \"forename\": \"Robert\" }]";

        // 5 Admissions total (5 Allocations to 2 Employees)
        // Emp1: P1 admissions 2x
        // Emp2: P1 admissions: 1x, P2 admissions 1x, P3 admissions 1x
        String rawAdmissionJson = "[ { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 1 }, { \"id\": 2, \"admissionDate\": \"2020-12-07T22:14:00\", \"dischargeDate\": \"2021-01-05T19:15:05\", \"patientID\": 1 }, { \"id\": 3, \"admissionDate\": \"2020-09-23T21:50:00\", \"dischargeDate\": \"2020-09-27T09:56:00\", \"patientID\": 2 }, { \"id\": 4, \"admissionDate\": \"2021-09-23T21:50:00\", \"dischargeDate\": \"2021-09-27T09:56:00\", \"patientID\": 1 }, { \"id\": 5, \"admissionDate\": \"2023-09-23T21:50:00\", \"dischargeDate\": \"2023-09-27T09:56:00\", \"patientID\": 3 } ]";

        // Allocations: Employees to Admissions
        String rawAllocationsJson = "[ { \"id\": 1, \"admissionID\": 1, \"employeeID\": 1, \"startTime\": \"2020-11-28T16:45:00\", \"endTime\": \"2020-11-28T23:56:00\"} ,  { \"id\": 2, \"admissionID\": 2, \"employeeID\": 1, \"startTime\": \"2020-12-07T22:14:00\", \"endTime\": \"2021-01-05T19:15:05\"}, { \"id\": 3, \"admissionID\": 3, \"employeeID\": 2, \"startTime\": \"2020-09-23T21:50:00\", \"endTime\": \"2020-09-27T09:56:00\"} , { \"id\": 5, \"admissionID\": 5, \"employeeID\": 2, \"startTime\": \"2023-09-23T21:50:00\", \"endTime\": \"2023-09-27T09:56:00\"} ]";

        // Parsing & Saving using mock HttpRequests
        

    }

    // Mock HttpResponse returns dummy data when called. There are duplicate Patients for Allocation in this example.
    // the unfiltered list creator is not responsible for removing bad values
    @Test
    void unfiltered_list_test(){
        //Arrange (Prepped in Constructor)

        // Info(Not Essential): Allocations has 5 items
        // Employee 2 attends Allocations 1 & 2 & 3
        // Allocations 1 & 3 are the same patient. However, the rest are diff patients (3 other)


        // Act


        Assertions.fail("Fail: Not Yet Implemented" + dummyDataPatients.get(0).getForename());
    }
}
