package com.zerobase.fintech.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account account;

    @Column(nullable = false, length = 30)
    private String toAccountNumber;

    private Long remittanceAmount;

    private LocalDateTime transferDate;

    private String memo;
}
