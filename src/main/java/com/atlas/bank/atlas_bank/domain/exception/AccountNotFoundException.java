package com.atlas.bank.atlas_bank.domain.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("No se encontró la cuenta con ID: " + id);
    }
}