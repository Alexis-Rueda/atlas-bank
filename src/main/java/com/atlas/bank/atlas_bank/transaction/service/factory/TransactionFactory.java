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
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TRANSFER);
        transaction.setSourceAccountId(ctx.from().getId());
        transaction.setTargetAccountId(ctx.to().getId());
        transaction.setAmount(ctx.amount());
        transaction.setFee(fee);
        transaction.setStatus(TransactionStatus.PENDING);

        transaction.advanceTo(new PendingState());
        return transaction;
    }
}