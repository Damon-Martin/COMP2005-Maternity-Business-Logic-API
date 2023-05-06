package uk.md.MaternityCalculationsAPI.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class AdmissionsController {

    @GetMapping("PatientsSeenByStaff")
    public String PatientsSeenByStaff() {
        return null;
    }

    @GetMapping("DischargedQuick")
    @ResponseStatus(HttpStatus.OK)
    public String DischargedQuick() {
        return "test_String";
    }

    @GetMapping("AvgDurationByStaff")
    public String AvgDurationByStaff() {
        return null;
    }

    // Provide Week: Returns the busiest day
    @GetMapping("BusiestDayOfWeek")
    public String BusiestDayOfWeek() {
        return null;
    }
}
