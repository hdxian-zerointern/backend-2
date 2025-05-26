package com.zerobase.fintech.controller;

import com.zerobase.fintech.DTO.AccountDTO;
import com.zerobase.fintech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        accountService.createAccount(accountDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    public Long checkBalance(@PathVariable Long id) {
        return accountService.checkBalance(id);
    }

}
