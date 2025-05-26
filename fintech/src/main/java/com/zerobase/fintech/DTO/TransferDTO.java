package com.zerobase.fintech.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransferDTO {

    private String toAccountNumber;

    private Long remittanceAmount;

    private String memo;

}
