package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Which Day has most Admissions
public class BusiestDayLogic {


    // Testable Calculation Functions Here
    // Pass in
    public Map<String,Integer> calculateFrequencyOfDays(List<Admission> allAllocations) {
        // List of weeks as list of enums
        List<DayOfWeek> allWeekDaysInAllocations = allAllocations.stream()
                .map(myObj -> myObj.admissionDate.getDayOfWeek())
                .collect(Collectors.toList());

        // Day Frequency
        Map<String, Integer> dayFrequency = allWeekDaysInAllocations.stream()
                .map(DayOfWeek::name)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));


        return dayFrequency;
    }


    // Merges all testable functions for ease of use
    public List<String> calculateBusiestDay(List<Admission> allAllocations) {
        List<String> busiestDay = new ArrayList<String>();

        // All Logic here

        return busiestDay;
    }
}
