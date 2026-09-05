package com.atlas.bank.atlas_bank.domain.model.transaction.state;

import com.atlas.bank.atlas_bank.domain.model.transaction.TransactionStatus;

public record ExecutedState() implements TransactionState {
    @Override
    public TransactionStatus status() {
        return TransactionStatus.EXECUTED;
    }

    @Override
    public TransactionState reverse() {
        return new ReversedState();
    }
}