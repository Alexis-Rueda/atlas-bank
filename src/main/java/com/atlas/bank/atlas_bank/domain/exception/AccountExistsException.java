package com.atlas.bank.atlas_bank.domain.exception;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException(String accountNumber) {
        super("Ya existe una cuenta con el número: " + accountNumber);
    }
}