package com.project.fintech.application;

import com.project.fintech.model.dto.ResponseDto;
import com.project.fintech.model.dto.domain.AccountDto;
import com.project.fintech.model.dto.domain.AccountMapper;
import com.project.fintech.model.type.Message;
import com.project.fintech.persistence.entity.Account;
import com.project.fintech.persistence.entity.User;
import com.project.fintech.service.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountApplication {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Transactional
    public ResponseDto<AccountDto> executeCreateAccount() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getUserByEmail(userEmail);

        Account account = accountService.createAccount(user);

        return ResponseDto.<AccountDto>builder().code(HttpServletResponse.SC_OK)
            .message(Message.COMPLETE_CREAT_ACCOUNT).data(accountMapper.toAccountDto(account))
            .build();
    }

    @Transactional
    public ResponseDto<AccountDto> executeDeleteAccount(Long accountId) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getUserByEmail(userEmail);
        Account account = accountService.getAccountById(accountId);

        accountService.deleteAccount(user, account);

        return ResponseDto.<AccountDto>builder().code(HttpServletResponse.SC_OK)
            .message(Message.COMPLETE_DELETE_ACCOUNT).data(accountMapper.toAccountDto(account))
            .build();
    }

    @Transactional
    public ResponseDto<List<AccountDto>> executeRetrieveUserAccounts() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getUserByEmail(userEmail);
        List<AccountDto> accountDtos = accountService.getUserAccounts(user).stream()
            .map(accountMapper::toAccountDto).toList();

        return ResponseDto.<List<AccountDto>>builder().code(HttpServletResponse.SC_OK)
            .message(Message.COMPLETE_RETRIEVE_ACCOUNT).data(accountDtos).build();
    }

    @Transactional
    public ResponseDto<AccountDto> executeRetrieveSingleAccount(Long accountId) {
        Account account = accountService.getAccountById(accountId);

        return ResponseDto.<AccountDto>builder().code(HttpServletResponse.SC_OK)
            .message(Message.COMPLETE_RETRIEVE_ACCOUNT).data(accountMapper.toAccountDto(account)).build();
    }
}
