package api.rest.entity;

import api.rest.utils.Config;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class Transaction {
    @NotNull
    private final Double amount;
    @NotNull
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