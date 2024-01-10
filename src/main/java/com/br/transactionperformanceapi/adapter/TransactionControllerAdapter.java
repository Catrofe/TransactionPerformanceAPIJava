package com.br.transactionperformanceapi.adapter;

import com.br.transactionperformanceapi.port.TransactionControllerPort;
import com.br.transactionperformanceapi.service.TransactionService;
import com.br.transactionperformanceapi.util.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionControllerAdapter implements TransactionControllerPort {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.createTransaction(transactionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
