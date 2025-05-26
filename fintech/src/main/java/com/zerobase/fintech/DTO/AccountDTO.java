package com.zerobase.fintech.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDTO {

    private Long id;

    @Digits(integer = 15, fraction = 0, message = "최대 보유자산을 넘게 예치할수는 없습니다.")
    private Long initialBalance;
}
