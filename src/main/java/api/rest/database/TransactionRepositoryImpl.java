package api.rest.database;

import api.rest.entity.Transaction;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * TransactionRepository Holds all the transaction object within 60 seconds duration.
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    private final Queue<Transaction> transactions;

    public TransactionRepositoryImpl() {
        transactions = new PriorityBlockingQueue<Transaction>(100, Comparator.comparing(Transaction::getTimestamp));

    }

    @Override
    public void add(Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public Queue<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void removeExpiredTransactions() {
        while (!this.transactions.isEmpty() && !this.transactions.peek().matchesTimeLimit()) {
            transactions.poll();
        }
    }

}