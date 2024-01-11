package com.br.transactionperformanceapi.service;

import com.br.transactionperformanceapi.domain.Transaction;
import com.br.transactionperformanceapi.port.TransactionRepositoryPort;
import com.br.transactionperformanceapi.util.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public void createTransaction(TransactionRequest transactionRequest) {
        int batchSize = generateBatchSize(transactionRequest.amountOfTransactions());
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < transactionRequest.amountOfTransactions(); i += batchSize) {
            if (i + batchSize > transactionRequest.amountOfTransactions()) {
                int finalI = i;
                Thread thread = new Thread(() -> generateAndSaveTransactions(transactionRequest.stockCode(), (transactionRequest.amountOfTransactions() - finalI)));
                threads.add(thread);
                thread.start();
                continue;
            }
            Thread thread = new Thread(() -> generateAndSaveTransactions(transactionRequest.stockCode(), batchSize));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void generateAndSaveTransactions(List<String> stockCode, Integer batchSize){
        ((Runnable) () -> {
            List<Transaction> transactions = new ArrayList<>();
            for (int i = 0; i < batchSize; i++) {
                Transaction transaction = new Transaction();
                transaction.setStockCode(stockCode.get(new Random().nextInt(stockCode.size())));
                transactions.add(transaction);
            }
            transactionRepositoryPort.saveAll(transactions);
        }).run();
    }

    private int generateBatchSize(int amountOfTransactions){
        if (amountOfTransactions >= 1000000){
            return 50000;
        }
        return 10000;
    }

}
