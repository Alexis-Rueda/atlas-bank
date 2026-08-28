package com.atlas.bank.atlas_bank.transaction.service.factory;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.model.TransactionStatus;
import com.atlas.bank.atlas_bank.transaction.model.TransactionType;
import com.atlas.bank.atlas_bank.transaction.model.state.PendingState;
import com.atlas.bank.atlas_bank.transaction.service.transfer.TransferContext;

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