package api.rest.controller;

import api.rest.service.TransactionService;
import api.rest.entity.Transaction;
import api.rest.exception.ExpiredorFutureTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
controller to add new transaction
 */

@RestController
public class TransactionController {

    private static final String template = "Hello, %s!";
    @Autowired
    private TransactionService transactionService;


    @PostMapping
    @RequestMapping(value = "/transactions", consumes = "application/json")
    @ResponseBody
    public ResponseEntity postData(@RequestBody @Valid Transaction transaction) {
        try {
            transactionService.addTransaction(transaction);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (final ExpiredorFutureTransactionException e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }
}
