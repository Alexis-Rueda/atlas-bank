package com.atlas.bank.atlas_bank.transaction.service.fraud;

import java.math.BigDecimal;

public interface FraudChecker {
    FraudCheckResult check(Long accountId, BigDecimal amount);
}