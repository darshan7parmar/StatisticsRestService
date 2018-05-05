package api.rest.controller;

import api.rest.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

/*
controller to handle statistics query
 */

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    @RequestMapping("/statistics")
    public DoubleSummaryStatistics getStatistics() {
        return statisticsService.getStatistics();
    }
}
