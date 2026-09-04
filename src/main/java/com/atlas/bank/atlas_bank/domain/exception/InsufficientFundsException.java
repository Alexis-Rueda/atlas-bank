package com.atlas.bank.atlas_bank.domain.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Long accountId, BigDecimal balance, BigDecimal amount) {
        super("La cuenta " + accountId + " tiene saldo " + balance
                + " y se intentó transferir " + amount);
    }
}