package com.atlas.bank.atlas_bank.account.dto;

import com.atlas.bank.atlas_bank.transaction.dto.TransactionResponse;
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