package com.atlas.bank.atlas_bank.infrastructure.adapter.in.rest.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentAccountsValidator implements ConstraintValidator<DifferentAccounts, TransferRequest> {
    @Override
    public boolean isValid(TransferRequest transferRequest, ConstraintValidatorContext constraintValidatorContext) {
        if(transferRequest.getFromAccountId()==null || transferRequest.getToAccountId()==null){
            return true;
        }
        return !transferRequest.getFromAccountId().equals(transferRequest.getToAccountId());
    }
}