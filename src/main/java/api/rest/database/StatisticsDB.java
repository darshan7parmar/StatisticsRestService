package api.rest.database;

import java.util.DoubleSummaryStatistics;

/**
 * StatisticsDB holds statistics object which is holding instance of DoubleSummaryStatistics
 */
public class StatisticsDB {
    private DoubleSummaryStatistics statistics;

    public StatisticsDB() {
        statistics = new DoubleSummaryStatistics();
    }
    public DoubleSummaryStatistics getStatistics() {
        return statistics;
    }

    public void updateStatistics(DoubleSummaryStatistics statistics) {
        this.statistics = statistics;
    }

    public void clearStatistics() {
        this.statistics = new DoubleSummaryStatistics();
    }
}
