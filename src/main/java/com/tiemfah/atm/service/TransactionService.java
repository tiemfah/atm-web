package com.tiemfah.atm.service;

import com.tiemfah.atm.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {
    private RestTemplate restTemplate;

    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void makeTransaction(Transaction transaction) {
        String url = "http://localhost:8091/api/bankaccount/transaction";
        restTemplate.postForObject(url, transaction, Transaction.class);
    }
}
