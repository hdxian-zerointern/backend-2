package com.zerobase.fintech.repository;

import com.zerobase.fintech.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
