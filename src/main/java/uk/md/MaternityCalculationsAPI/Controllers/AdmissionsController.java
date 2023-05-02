package uk.md.MaternityCalculationsAPI.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmissionsController {

    @GetMapping("api/PatientsSeenByStaff")
    public String PatientsSeenByStaff() {
        return null;
    }

    @GetMapping("api/DischargedQuick")
    public String DischargedQuick() {
        return null;
    }

    @GetMapping("api/AvgDurationByStaff")
    public String AvgDurationByStaff() {
        return null;
    }

    // Provide Week: Returns the busiest day
    @GetMapping("api/BusiestDayOfWeek")
    public String BusiestDayOfWeek() {
        return null;
    }
}
