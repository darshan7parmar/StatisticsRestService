package api.rest.scheduler;

import api.rest.service.StatisticsService;
import api.rest.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * StatisticsUpdateScheduler provides the refreshing of the statistics for the transactions
 */
@Component
public class StatisticsUpdateScheduler {

    @Autowired
    private StatisticsService statisticsService;

    @Scheduled(fixedRate = Config.SCHEDULING_PERIOD)
    public void updateStatistics() {
        System.out.println("Schedulder Started");
        statisticsService.refreshStatistics();
    }
}
