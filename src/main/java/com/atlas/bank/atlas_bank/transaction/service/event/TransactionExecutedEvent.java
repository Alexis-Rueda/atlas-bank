package com.atlas.bank.atlas_bank.transaction.service.event;

import java.math.BigDecimal;

public record TransactionExecutedEvent(
        Long transactionId,
        String type,
        Long sourceAccountId,
        Long targetAccountId,
        BigDecimal amount,
        BigDecimal fee
) {

}