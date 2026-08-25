package com.atlas.bank.atlas_bank.transaction.service.exception;

public class FraudCheckException extends RuntimeException {
    public FraudCheckException(String reason) {
        super(reason);
    }
}