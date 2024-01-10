package com.br.transactionperformanceapi.util.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TransactionRequest(
        @NotNull List<String> stockCode,
        @NotNull int amountOfTransactions
) {
}
