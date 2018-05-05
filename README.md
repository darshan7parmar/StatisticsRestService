"# StatisticsRestService" 
Specs
POST /transactions

/tranasction endpoint add the transaction to the tranasction repository
transactionRepository is madeup of in memory PriorityBlockingQueue Java Implementation , which can handle concurrent transaction request in thread safe manner.
Worst case insertion time for transaction add is O(log n) , and in normal case if the timestamp values are always higher than exisiting timestamps then it will insert in O(1) time.

GET /statistics

/statistics endpoint will return the statistics like sum,max,min,count and average. 
statistics endpoint runs on O(1) time. 

Statistics Refresh  
statistics objects are updated every second via scheduler. So new transactions are reflected into statistics only after 1 second(this value can be configured in Config.java).
statistics are calculated based on DoubleSummaryStatistics Java class which calculates the statistics on the Single Pass.





