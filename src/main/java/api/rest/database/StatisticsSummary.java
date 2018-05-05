package api.rest.database;

import java.util.DoubleSummaryStatistics;

public interface StatisticsSummary {

    DoubleSummaryStatistics getStatistics();

    void updateStatistics(DoubleSummaryStatistics statistics);

    void clearStatistics();
}
