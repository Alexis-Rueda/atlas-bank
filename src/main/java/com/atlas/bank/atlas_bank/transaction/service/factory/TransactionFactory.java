package com.atlas.bank.atlas_bank.transaction.service.factory;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.service.transfer.TransferContext;

import java.math.BigDecimal;

public class TransactionFactory {
    private TransactionFactory() {
        /* This utility class should not be instantiated */
    }

    public static Transaction createTransfer(TransferContext ctx, BigDecimal fee){
        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(ctx.from().getId());
        transaction.setTargetAccountId(ctx.to().getId());
        transaction.setAmount(ctx.amount());
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");
        return transaction;
    }
}