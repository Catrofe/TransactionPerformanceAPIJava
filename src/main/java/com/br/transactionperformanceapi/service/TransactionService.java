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
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < transactionRequest.amountOfTransactions(); i++){
            Transaction transaction = new Transaction();
            transaction.setStockCode(transactionRequest.stockCode().get(new Random().nextInt(transactionRequest.stockCode().size())));
            transactions.add(transaction);
        }
        saveTransactions(transactions);

    }



    private void saveTransactions(List<Transaction> transactions) {
        transactionRepositoryPort.saveAll(transactions);
    }
}
