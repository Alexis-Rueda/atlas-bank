package com.atlas.bank.atlas_bank.application.port.in;

import com.atlas.bank.atlas_bank.domain.model.transaction.Transaction;

import java.util.List;

public interface GetTransactionsByAccountUseCase {
    List<Transaction> getByAccountId(Long accountId);
}