package com.br.transactionperformanceapi.service;

import com.br.transactionperformanceapi.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GenerationTransactionService implements Runnable{

    private final List<Transaction> transactions;
    private final List<String> stockCodes;
    private Integer batchSize = 10000;

    public GenerationTransactionService(List<Transaction> transactions, List<String> stockCodes) {
        this.transactions = transactions;
        this.stockCodes = stockCodes;
    }


    @Override
    public void run() {
        for (int i = 0; i < batchSize; i++) {
            Transaction transaction = new Transaction();
            transaction.setStockCode(stockCodes.get(new Random().nextInt(stockCodes.size())));
            transactions.add(transaction);
        }
        synchronized (this) {
            this.notify();
        }
    }
}
