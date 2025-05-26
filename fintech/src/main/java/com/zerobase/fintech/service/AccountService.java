package com.zerobase.fintech.service;

import com.zerobase.fintech.DTO.AccountDTO;
import com.zerobase.fintech.entity.Account;
import com.zerobase.fintech.entity.User;
import com.zerobase.fintech.exception.CustomException;
import com.zerobase.fintech.repository.AccountRepository;
import com.zerobase.fintech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public void createAccount(AccountDTO accountDTO) {

        if(!userRepository.existsByUserId(accountDTO.getId())) {
            throw new CustomException("ID에 해당하는 정보가 존재하지 않습니다.");
        }

        String accountNumber = generateUniqueAccountNumber();

        Account account = Account.builder()
                .user(User.builder()
                        .userId(accountDTO.getId())
                        .build())
                .accountNumber(accountNumber)
                .balance(accountDTO.getInitialBalance())
                .build();

        accountRepository.save(account);

    }

    private String generateUniqueAccountNumber() {
        Random random = new Random();
        String accountNumber;
        do {
            accountNumber = String.format("%020d", random.nextLong() & Long.MAX_VALUE);
        } while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    public Long checkBalance(Long id) {

        if(!accountRepository.existsByAccountId(id)) {
            throw new CustomException("ID에 해당하는 정보가 존재하지 않습니다.");
        }

        Account account = accountRepository.getByAccountId(id);
        return account.getBalance();
    }

}
