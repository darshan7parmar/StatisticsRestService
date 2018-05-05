package api.rest.service;

import api.rest.database.StatisticsDB;
import api.rest.database.TransactionRepository;
import api.rest.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * StatisticsService provide the realtime statistics in O(1) time duration.
 */
@Service
public class StatisticsService {
    @Autowired
    private StatisticsDB statisticsDB;
    @Autowired
    private TransactionRepository transactionRepository;

    public DoubleSummaryStatistics getStatistics() {

        return statisticsDB.getStatistics();
    }

    public void refreshStatistics() {
        transactionRepository.removeExpiredTransactions();
        final List<Double> last60SecondsTransaction = transactionRepository.getTransactions().stream().map(Transaction::getAmount).collect(toList());
        if (last60SecondsTransaction.size() > 0) {
            DoubleSummaryStatistics stats = last60SecondsTransaction.stream()
                    .collect(Collectors.summarizingDouble(Double::doubleValue));
            statisticsDB.updateStatistics(stats);
        } else {
            if (statisticsDB.getStatistics().getCount() > 0) {
                statisticsDB.clearStatistics();
            }
        }
    }
}



