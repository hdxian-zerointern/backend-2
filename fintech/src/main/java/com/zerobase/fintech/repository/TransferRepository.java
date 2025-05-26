package com.zerobase.fintech.repository;

import com.zerobase.fintech.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
