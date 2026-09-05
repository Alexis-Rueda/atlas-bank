package com.atlas.bank.atlas_bank.transaction.service.fraud;

import com.atlas.bank.atlas_bank.domain.model.shared.FraudCheckResult;

import java.math.BigDecimal;

public interface FraudChecker {
    FraudCheckResult check(Long accountId, BigDecimal amount);
}