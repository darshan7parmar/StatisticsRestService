package api.rest;

import api.rest.database.StatisticsDB;
import api.rest.database.TransactionRepository;
import api.rest.database.TransactionRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(value = "singleton")
    public TransactionRepository getTransactionDB() {
        return new TransactionRepositoryImpl();
    }

    @Bean
    @Scope(value = "singleton")
    public StatisticsDB getStatisticsDB(){
        return new StatisticsDB();
    }


}
