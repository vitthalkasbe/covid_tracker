package com.example.covidtracker.controllers;

import com.example.covidtracker.models.LocationStatistics;
import com.example.covidtracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model)
    {
        List<LocationStatistics> allStats = coronaVirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getTotalCasesYesterday()).sum();

        model.addAttribute("totalReportedCases",totalCases);
        model.addAttribute("totalReportedNewCases",totalNewCases);
        model.addAttribute("locationStats", allStats);
        model.addAttribute("yesterday",LocalDate.now().minusDays(1));
        return "home";
    }
}
