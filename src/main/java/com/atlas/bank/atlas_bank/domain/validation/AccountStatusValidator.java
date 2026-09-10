package com.atlas.bank.atlas_bank.domain.validation;

import com.atlas.bank.atlas_bank.domain.model.account.AccountStatus;
import com.atlas.bank.atlas_bank.domain.exception.AccountNotActiveException;
import com.atlas.bank.atlas_bank.domain.model.transaction.TransferContext;

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