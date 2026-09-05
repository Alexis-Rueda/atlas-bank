package com.atlas.bank.atlas_bank.domain.validation;

import com.atlas.bank.atlas_bank.domain.model.transaction.TransferContext;

public interface TransferValidator {
    void validate(TransferContext ctx);
}