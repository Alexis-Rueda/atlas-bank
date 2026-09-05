package com.atlas.bank.atlas_bank.application.port.out;


import com.atlas.bank.atlas_bank.domain.model.shared.FraudCheckResult;

import java.math.BigDecimal;

public interface FraudCheckPort {
    FraudCheckResult check(Long accountId, BigDecimal amount);
}