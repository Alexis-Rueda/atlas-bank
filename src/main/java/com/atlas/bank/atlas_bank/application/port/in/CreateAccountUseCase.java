package com.atlas.bank.atlas_bank.application.port.in;

import com.atlas.bank.atlas_bank.account.model.Account;

public interface CreateAccountUseCase {
    Account execute(Account account);
}
