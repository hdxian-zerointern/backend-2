package com.zerobase.fintech.service;

import com.zerobase.fintech.DTO.TransferDTO;
import com.zerobase.fintech.entity.Account;
import com.zerobase.fintech.entity.Transfer;
import com.zerobase.fintech.exception.CustomException;
import com.zerobase.fintech.repository.AccountRepository;
import com.zerobase.fintech.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private final AccountRepository accountRepository;

    public void transfer(Long accountId,TransferDTO transferDTO) {

        if(!accountRepository.existsByAccountNumber(transferDTO.getToAccountNumber())) {
            throw new CustomException("존재하지 않는 계좌로는 송금이 불가합니다.");
        }

        // 받는 계좌 처리
        Account toAccount = accountRepository.findByAccountNumber(transferDTO.getToAccountNumber());

        long totalAmount = toAccount.getBalance() + transferDTO.getRemittanceAmount();

        toAccount.setBalance(totalAmount);


        // 보내는 계좌 처리
        Account fromAccount = accountRepository.getByAccountId(accountId);

        Long checkBalance = fromAccount.getBalance() - transferDTO.getRemittanceAmount();

        if(checkBalance < 0) {
            throw new CustomException("계좌의 잔액보다 많은 금액은 송금이 불가합니다.");
        }

        fromAccount.setBalance(checkBalance);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);



        Transfer transfer = Transfer.builder()
                .account(Account.builder()
                        .accountId(accountId)
                        .build())
                .toAccountNumber(transferDTO.getToAccountNumber())
                .remittanceAmount(transferDTO.getRemittanceAmount())
                .transferDate(LocalDateTime.now())
                .memo(transferDTO.getMemo())
                .build();

        transferRepository.save(transfer);

    }

}
