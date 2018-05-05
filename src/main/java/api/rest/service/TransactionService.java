package api.rest.service;

import api.rest.database.TransactionRepository;
import api.rest.entity.Transaction;
import api.rest.exception.ExpiredorFutureTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransactionService add the transaction to the repository it is having timecomplexity of O(logn).
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addTransaction(Transaction transaction) throws ExpiredorFutureTransactionException {
        if (!transaction.matchesTimeLimit()) {
            throw new ExpiredorFutureTransactionException();
        } else {
            this.transactionRepository.add(transaction);
        }
    }
}
