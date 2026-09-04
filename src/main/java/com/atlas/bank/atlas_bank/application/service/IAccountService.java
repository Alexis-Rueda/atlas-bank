package com.atlas.bank.atlas_bank.application.service;

import com.atlas.bank.atlas_bank.domain.model.account.Account;

import java.util.List;

public interface IAccountService {
    Account create(Account account);

    List<Account> findAll();

    Account findById(Long id);
}
