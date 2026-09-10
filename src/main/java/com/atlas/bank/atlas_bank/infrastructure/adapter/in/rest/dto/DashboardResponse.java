package com.atlas.bank.atlas_bank.infrastructure.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private Long accountId;
    private String accountNumber;
    private String ownerName;
    private String type;
    private BigDecimal balance;
    private String status;
    private List<TransactionResponse> recentTransactions;
}