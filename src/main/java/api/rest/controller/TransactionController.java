package api.rest.controller;

import api.rest.service.TransactionService;
import api.rest.entity.Transaction;
import api.rest.exception.ExpiredorFutureTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/*
controller to add new transaction
 */

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    @RequestMapping(value = "/transactions", consumes = "application/json")
    @ResponseBody
    public ResponseEntity postData(@RequestBody @Valid @NotNull Transaction transaction) {
        try {
            transactionService.addTransaction(transaction);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (final ExpiredorFutureTransactionException e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }
}
