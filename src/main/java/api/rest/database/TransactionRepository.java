package api.rest.database;

import api.rest.entity.Transaction;

import java.util.Queue;

public interface TransactionRepository {

    void add(Transaction transaction);

    Queue<Transaction> getTransactions();

    void removeExpiredTransactions();


}
