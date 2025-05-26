package com.zerobase.fintech.service;

import com.zerobase.fintech.DTO.TransactionDTO;
import com.zerobase.fintech.entity.Account;
import com.zerobase.fintech.entity.Transaction;
import com.zerobase.fintech.enums.TransactionType;
import com.zerobase.fintech.exception.CustomException;
import com.zerobase.fintech.repository.AccountRepository;
import com.zerobase.fintech.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    public void deposit(TransactionDTO transactionDTO) {

        if(!accountRepository.existsByAccountId(transactionDTO.getAccountId())) {
            throw new CustomException("존재하지 않는 계좌에는 입금이 불가합니다.");
        }

        Optional<Account> account = accountRepository.findById(transactionDTO.getAccountId());
        account.get().setBalance(account.get().getBalance() + transactionDTO.getAmount());
        accountRepository.save(account.get());

        Transaction transaction = Transaction.builder()
                .amount(transactionDTO.getAmount())
                .account(Account.builder()
                        .accountId(transactionDTO.getAccountId())
                        .build())
                .transactionType(TransactionType.DEPOSIT)
                .amount(transactionDTO.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
    }

    public void withdraw(TransactionDTO transactionDTO) {

        if(!accountRepository.existsByAccountId(transactionDTO.getAccountId())) {
            throw new CustomException("존재하지 않는 계좌에는 출금이 불가합니다.");
        }

        Optional<Account> account = accountRepository.findById(transactionDTO.getAccountId());
        account.get().setBalance(account.get().getBalance() - transactionDTO.getAmount());

        if(account.get().getBalance() < 0) {
            throw  new CustomException("계좌의 금액보다 출금액이 많을수 없습니다.");
        }

        accountRepository.save(account.get());

        Transaction transaction = Transaction.builder()
                .amount(transactionDTO.getAmount())
                .account(Account.builder()
                        .accountId(transactionDTO.getAccountId())
                        .build())
                .transactionType(TransactionType.WITHDRAW)
                .amount(transactionDTO.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

    }

}
