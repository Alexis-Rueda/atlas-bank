package com.atlas.bank.atlas_bank.transaction.service.factory;

import com.atlas.bank.atlas_bank.domain.model.transaction.Transaction;
import com.atlas.bank.atlas_bank.domain.model.transaction.TransactionStatus;
import com.atlas.bank.atlas_bank.domain.model.transaction.TransactionType;
import com.atlas.bank.atlas_bank.domain.model.transaction.state.PendingState;
import com.atlas.bank.atlas_bank.domain.model.transaction.TransferContext;

import java.math.BigDecimal;

public class TransactionFactory {
    private TransactionFactory() {
        /* This utility class should not be instantiated */
    }

    public static Transaction createTransfer(TransferContext ctx, BigDecimal fee){
        Transaction transaction = Transaction.builder()
                .type(TransactionType.TRANSFER)
                .sourceAccountId(ctx.from().getId())
                .targetAccountId(ctx.to().getId())
                .amount(ctx.amount())
                .fee(fee)
                .status(TransactionStatus.PENDING)
                .build();

        transaction.advanceTo(new PendingState());
        return transaction;
    }
}