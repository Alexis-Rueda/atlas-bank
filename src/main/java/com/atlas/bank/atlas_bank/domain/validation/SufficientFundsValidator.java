package com.atlas.bank.atlas_bank.domain.validation;

import com.atlas.bank.atlas_bank.domain.exception.InsufficientFundsException;
import com.atlas.bank.atlas_bank.domain.model.transaction.TransferContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SufficientFundsValidator implements TransferValidator{
    @Override
    public void validate(TransferContext ctx) {
        if (ctx.from().getBalance().getAmount().compareTo(ctx.amount()) < 0) {
            throw new InsufficientFundsException(ctx.from().getId(), ctx.from().getBalance().getAmount(), ctx.amount());
        }
    }
}