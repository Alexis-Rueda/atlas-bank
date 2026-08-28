package com.atlas.bank.atlas_bank.transaction.validation.chain;

import com.atlas.bank.atlas_bank.transaction.service.exception.FraudCheckException;
import com.atlas.bank.atlas_bank.transaction.service.fraud.FraudCheckResult;
import com.atlas.bank.atlas_bank.transaction.service.fraud.FraudChecker;
import com.atlas.bank.atlas_bank.transaction.service.transfer.TransferContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
public class FraudValidator implements TransferValidator{

    private final FraudChecker fraudChecker;

    @Override
    public void validate(TransferContext ctx) {
        FraudCheckResult fraudCheckResult = fraudChecker.check(ctx.from().getId(), ctx.amount());
        if(fraudCheckResult.blocked()){
            throw new FraudCheckException(fraudCheckResult.reason());
        }
    }
}