package com.atlas.bank.atlas_bank.application.port.in;

import com.atlas.bank.atlas_bank.domain.model.account.Account;

public interface GetAccountUseCase {
    Account findById(Long id);
}
