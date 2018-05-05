package api.rest.database;

import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;

/**
 * StatisticsDB holds statistics object which is holding instance of DoubleSummaryStatistics
 */

@Component
public class StatisticsSummaryImpl implements StatisticsSummary {

    private DoubleSummaryStatistics statistics;

    public StatisticsSummaryImpl() {
        statistics = new DoubleSummaryStatistics();
    }

    @Override
    public DoubleSummaryStatistics getStatistics() {
        return statistics;
    }

    @Override
    public void updateStatistics(DoubleSummaryStatistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void clearStatistics() {
        this.statistics = new DoubleSummaryStatistics();
    }
}
