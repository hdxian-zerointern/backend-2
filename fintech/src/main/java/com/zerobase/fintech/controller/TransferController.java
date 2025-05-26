package com.zerobase.fintech.controller;

import com.zerobase.fintech.DTO.TransferDTO;
import com.zerobase.fintech.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/transfers")
@RestController
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/{accountId}")
    public ResponseEntity<?> transfer(@PathVariable Long accountId, @RequestBody TransferDTO transferDTO) {
        transferService.transfer(accountId,transferDTO);
        return ResponseEntity.ok().build();
    }

}
