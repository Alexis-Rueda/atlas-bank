package com.atlas.bank.atlas_bank.application.port.in;

import com.atlas.bank.atlas_bank.domain.model.account.Account;

import java.util.List;

public interface ListAccountsUseCase {
    List<Account> findAll();
}