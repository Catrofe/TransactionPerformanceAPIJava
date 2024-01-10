package com.br.transactionperformanceapi.port;

import com.br.transactionperformanceapi.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryPort extends JpaRepository<Transaction, Long> {
}
