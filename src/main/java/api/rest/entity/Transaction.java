package api.rest.entity;

import api.rest.utils.Config;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private final Double amount;
    private final Long timestamp;

    @JsonCreator
    public Transaction(@JsonProperty("amount") Double amount, @JsonProperty("timestamp") Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public boolean matchesTimeLimit() {
        long currentMillis = System.currentTimeMillis();
        long timeDiff = currentMillis - this.timestamp;
        return (timeDiff < Config.TIME_PERIOD_TO_KEEP_TRANSACTION )&& timeDiff >= 0 ? true : false;
    }
}