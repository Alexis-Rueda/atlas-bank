package com.atlas.bank.atlas_bank.application.port.in;

import com.atlas.bank.atlas_bank.domain.model.account.Account;

public interface CreateAccountUseCase {
    Account execute(Account account);
}
