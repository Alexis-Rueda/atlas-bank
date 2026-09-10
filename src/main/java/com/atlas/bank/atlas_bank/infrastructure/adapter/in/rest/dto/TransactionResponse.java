package com.atlas.bank.atlas_bank.infrastructure.adapter.in.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private String type;
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status;
    private LocalDateTime createdAt;
}