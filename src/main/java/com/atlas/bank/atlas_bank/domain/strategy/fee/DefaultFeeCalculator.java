package com.atlas.bank.atlas_bank.domain.strategy.fee;

import com.atlas.bank.atlas_bank.domain.model.account.AccountType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order()
public class DefaultFeeCalculator implements FeeCalculator{
    @Override
    public boolean supports(AccountType accountType) {
        return true;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO;
    }
}
