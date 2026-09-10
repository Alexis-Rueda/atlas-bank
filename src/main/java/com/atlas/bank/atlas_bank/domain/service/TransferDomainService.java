package com.atlas.bank.atlas_bank.domain.service;

import com.atlas.bank.atlas_bank.domain.model.account.Account;
import com.atlas.bank.atlas_bank.domain.model.shared.Money;

import java.math.BigDecimal;

public class TransferDomainService {
    public void transfer(Account from, Account to, BigDecimal amount, BigDecimal fee){

        Money totalDebit = Money.of(amount.add(fee), from.getBalance().getCurrency());

        Money depositAmount = Money.of(amount, to.getBalance().getCurrency());

        from.withdraw(totalDebit);
        to.deposit(depositAmount);

    }
}