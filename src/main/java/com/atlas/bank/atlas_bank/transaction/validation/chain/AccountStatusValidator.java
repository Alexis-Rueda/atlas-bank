package com.atlas.bank.atlas_bank.transaction.validation.chain;

import com.atlas.bank.atlas_bank.domain.model.account.AccountStatus;
import com.atlas.bank.atlas_bank.transaction.exception.AccountNotActiveException;
import com.atlas.bank.atlas_bank.transaction.service.transfer.TransferContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AccountStatusValidator implements TransferValidator{
    @Override
    public void validate(TransferContext ctx) {
        if (ctx.from().getStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException(ctx.from().getId(), ctx.from().getStatus().name());
        }
        if (ctx.to().getStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException(ctx.to().getId(), ctx.to().getStatus().name());
        }
    }
}