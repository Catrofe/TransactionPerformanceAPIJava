package com.br.transactionperformanceapi.port;

import com.br.transactionperformanceapi.util.dto.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionControllerPort {
    @PostMapping
    ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest);
}
