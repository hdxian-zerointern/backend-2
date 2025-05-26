package com.zerobase.fintech.controller;

import com.zerobase.fintech.DTO.TransactionDTO;
import com.zerobase.fintech.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/amount/deposit")
    public ResponseEntity<?> depositAmount(@RequestBody TransactionDTO transactionDTO) {
        transactionService.deposit(transactionDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/amount/withdraw")
    public ResponseEntity<?> withdrawAmount(@RequestBody TransactionDTO transactionDTO) {
        transactionService.withdraw(transactionDTO);
        return ResponseEntity.ok().build();
    }



}
